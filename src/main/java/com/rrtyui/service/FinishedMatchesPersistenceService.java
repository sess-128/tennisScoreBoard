package com.rrtyui.service;

import com.rrtyui.dto.MatchFilter;
import com.rrtyui.dto.MatchPageResponseDto;
import com.rrtyui.dto.MatchScoreModel;
import com.rrtyui.interceptor.TransactionInterceptor;
import com.rrtyui.mapper.FinishedMatchCreateMapper;
import com.rrtyui.repository.MatchRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.lang.reflect.InvocationTargetException;

@RequiredArgsConstructor
public class FinishedMatchesPersistenceService {
    private final MatchRepository matchRepository;
    private final FinishedMatchCreateMapper finishedMatchCreateMapper;

    @Transactional
    public void saveMatch(MatchScoreModel matchScoreModel) {
        var match = finishedMatchCreateMapper.mapFrom(matchScoreModel);
        matchRepository.save(match);
    }

    @Transactional
    public MatchPageResponseDto getMatchesPagination(MatchFilter matchFilter) {
        return matchRepository.findAllWithPagination(matchFilter);
    }

    public static FinishedMatchesPersistenceService getInstance(Session session) {
        SessionFactory sessionFactory = session.getSessionFactory();

        var matchRepository = new MatchRepository(session);
        var finishedMatchCreateMapper = new FinishedMatchCreateMapper();
        var transactionInterceptor = new TransactionInterceptor(sessionFactory);
        FinishedMatchesPersistenceService finishedMatchesPersistenceService;
        try {
            finishedMatchesPersistenceService = new ByteBuddy()
                    .subclass(FinishedMatchesPersistenceService.class)
                    .method(ElementMatchers.isAnnotatedWith(Transactional.class)) // Перехватываем только методы с @Transactional
                    .intercept(MethodDelegation.to(transactionInterceptor))
                    .make()
                    .load(FinishedMatchesPersistenceService.class.getClassLoader())
                    .getLoaded()
                    .getDeclaredConstructor(MatchRepository.class, FinishedMatchCreateMapper.class)
                    .newInstance(matchRepository, finishedMatchCreateMapper);
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return finishedMatchesPersistenceService;
    }
}
