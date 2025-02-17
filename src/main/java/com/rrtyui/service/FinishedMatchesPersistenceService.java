package com.rrtyui.service;

import com.rrtyui.dto.MatchResponseDto;
import com.rrtyui.dto.MatchScoreModel;
import com.rrtyui.entity.Match;
import com.rrtyui.interceptor.TransactionInterceptor;
import com.rrtyui.mapper.FinishedMatchCreateMapper;
import com.rrtyui.repository.MatchRepository;
import com.rrtyui.repository.PlayerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RequiredArgsConstructor
public class FinishedMatchesPersistenceService {
    //TODO тут будет работа с матч-репозиторием - хранить, получать и добавлять матчи
    private final MatchRepository matchRepository;
    private final FinishedMatchCreateMapper finishedMatchCreateMapper;
    private final PlayerRepository playerRepository;


    @Transactional
    public void saveMatch(MatchScoreModel matchScoreModel) {
        var match = finishedMatchCreateMapper.mapFrom(matchScoreModel);

        match.setPlayer1(playerRepository.findById(match.getPlayer1().getId()).get());
        match.setPlayer2(playerRepository.findById(match.getPlayer2().getId()).get());
        match.setWinner(playerRepository.findById(match.getWinner().getId()).get());

        matchRepository.save(match);
    }

    @Transactional
    public List<Match> getAll () {
        return matchRepository.findAll();
    }

    public static FinishedMatchesPersistenceService getInstance (Session session) {

        SessionFactory sessionFactory = session.getSessionFactory();

        var matchRepository = new MatchRepository(session);
        var finishedMatchCreateMapper = new FinishedMatchCreateMapper();
        var transactionInterceptor = new TransactionInterceptor(sessionFactory);
        FinishedMatchesPersistenceService finishedMatchesPersistenceService;
        try {
            finishedMatchesPersistenceService = new ByteBuddy()
                    .subclass(FinishedMatchesPersistenceService.class)
                    .method(ElementMatchers.any())
                    .intercept(MethodDelegation.to(transactionInterceptor))
                    .make()
                    .load(FinishedMatchesPersistenceService.class.getClassLoader())
                    .getLoaded()
                    .getDeclaredConstructor(MatchRepository.class, FinishedMatchCreateMapper.class)
                    .newInstance(matchRepository, finishedMatchCreateMapper);
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        return finishedMatchesPersistenceService;
    }
}
