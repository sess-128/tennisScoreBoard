package com.rrtyui.service;

import com.rrtyui.dto.MatchModel;
import com.rrtyui.dto.MatchScoreModel;
import com.rrtyui.dto.PlayerCreateDto;
import com.rrtyui.dto.PlayerDto;
import com.rrtyui.entity.Player;
import com.rrtyui.interceptor.TransactionInterceptor;
import com.rrtyui.mapper.PlayerCreateMapper;
import com.rrtyui.mapper.PlayerReadMapper;
import com.rrtyui.repository.PlayerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
public class OngoingMatchesService {
    private final PlayerRepository playerRepository;
    private final PlayerReadMapper playerReadMapper;
    private final PlayerCreateMapper playerCreateMapper;

    private static final Map<UUID, MatchScoreModel> ongoingMatches = new ConcurrentHashMap<>();

    @Transactional
    public UUID createMatch(PlayerCreateDto player1, PlayerCreateDto player2) {
        var player1Entity = playerCreateMapper.mapFrom(player1);
        var player2Entity = playerCreateMapper.mapFrom(player2);

        if (player1Entity == null || player2Entity == null) {
            throw new RuntimeException("Ошибка при преобразовании PlayerCreateDto");
        }

        Player player1id = playerRepository.save(player1Entity);
        Player player2id = playerRepository.save(player2Entity);


        if (player1id == null || player2id == null) {
            throw new RuntimeException("Ошибка при сохранении игроков");
        }

        MatchScoreModel matchScoreModel = new MatchScoreModel(player1id, player2id);
        MatchModel matchModel = new MatchModel(matchScoreModel);

        UUID id = matchModel.getId();

        ongoingMatches.put(id, matchScoreModel);

        UUID get = UUID.fromString("9191a495-f051-4ce4-be56-980c0f43df35");

        ongoingMatches.get(get);

        if (id == null) {
            throw new RuntimeException("UUID нету");
        }
        System.out.println("Created Match player1: " + player1id);
        System.out.println("Created Match player2: " + player2id);
        System.out.println("Created Match UUID: " + id);
        return id;
    }

    @Transactional
    public Optional<PlayerDto> findById(Long id) {
        return playerRepository.findById(id)
                .map(playerReadMapper::mapFrom);
    }

    @Transactional
    public boolean delete(Long id) {
        var maybePlayer = playerRepository.findById(id);
        maybePlayer.ifPresent(player -> playerRepository.delete(player.getId()));
        return maybePlayer.isPresent();
    }

    public static OngoingMatchesService getInstance(Session session) {

        SessionFactory sessionFactory = session.getSessionFactory();

        var playerRepository = new PlayerRepository(session);
        var playerReadMapper = new PlayerReadMapper();
        var playerCreateMapper = new PlayerCreateMapper();
        var transactionInterceptor = new TransactionInterceptor(sessionFactory);
        OngoingMatchesService ongoingMatchesService;
        try {
            ongoingMatchesService = new ByteBuddy()
                    .subclass(OngoingMatchesService.class)
                    .method(ElementMatchers.any())
                    .intercept(MethodDelegation.to(transactionInterceptor))
                    .make()
                    .load(OngoingMatchesService.class.getClassLoader())
                    .getLoaded()
                    .getDeclaredConstructor(PlayerRepository.class, PlayerReadMapper.class, PlayerCreateMapper.class)
                    .newInstance(playerRepository, playerReadMapper, playerCreateMapper);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return ongoingMatchesService;
    }
}
