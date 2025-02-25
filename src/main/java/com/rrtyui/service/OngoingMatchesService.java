package com.rrtyui.service;

import com.rrtyui.dto.MatchModel;
import com.rrtyui.dto.MatchScoreModel;
import com.rrtyui.dto.PlayerCreateDto;
import com.rrtyui.entity.Player;
import com.rrtyui.interceptor.TransactionInterceptor;
import com.rrtyui.mapper.PlayerCreateMapper;
import com.rrtyui.repository.PlayerRepository;
import com.rrtyui.util.MatchStorage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

@RequiredArgsConstructor
public class OngoingMatchesService {
    private final PlayerRepository playerRepository;
    private final PlayerCreateMapper playerCreateMapper;

    @Transactional
    public UUID createMatch(PlayerCreateDto player1Dto, PlayerCreateDto player2Dto) {
        Player player1 = getOrAddPlayer(player1Dto);
        Player player2 = getOrAddPlayer(player2Dto);

        MatchScoreModel matchScoreModel = new MatchScoreModel(player1, player2);
        MatchModel matchModel = new MatchModel(matchScoreModel);

        UUID id = matchModel.getId();

        MatchStorage.addMatch(id, matchScoreModel);

        return id;
    }

    @Transactional
    private Player getOrAddPlayer(PlayerCreateDto playerDto) {
        Player player = playerCreateMapper.mapFrom(playerDto);

        return playerRepository.findByName(player.getName())
                .orElseGet(() -> playerRepository.update(player));
    }

    public static OngoingMatchesService getInstance(Session session) {

        SessionFactory sessionFactory = session.getSessionFactory();

        var playerRepository = new PlayerRepository(session);
        var playerCreateMapper = new PlayerCreateMapper();
        var transactionInterceptor = new TransactionInterceptor(sessionFactory);
        OngoingMatchesService ongoingMatchesService;
        try {
            ongoingMatchesService = new ByteBuddy()
                    .subclass(OngoingMatchesService.class)
                    .method(ElementMatchers.isAnnotatedWith(Transactional.class))
                    .intercept(MethodDelegation.to(transactionInterceptor))
                    .make()
                    .load(OngoingMatchesService.class.getClassLoader())
                    .getLoaded()
                    .getDeclaredConstructor(PlayerRepository.class, PlayerCreateMapper.class)
                    .newInstance(playerRepository, playerCreateMapper);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return ongoingMatchesService;
    }
}
