package com.rrtyui.interceptor;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

@RequiredArgsConstructor
public class TransactionInterceptor {

    private final SessionFactory sessionFactory;

    @RuntimeType
    public Object intercept(@SuperCall Callable<Object> call, @Origin Method method) throws Exception {
        System.out.println("Перехват метода: " + method.getName());
        Transaction transaction = null;
        boolean transactionStarted = false;
        if (method.isAnnotationPresent(Transactional.class)) {
            System.out.println("Метод аннотирован @Transactional");
            transaction = sessionFactory.getCurrentSession().getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
                transactionStarted = true;
                System.out.println("Транзакция начата для метода: " + method.getName());
            }
        }

        Object result = null;
        try {
            result = call.call();
            if (transactionStarted) {
                transaction.commit();
                System.out.println("Транзакция зафиксирована для метода: " + method.getName());
            }
        } catch (Exception exception) {
            if (transactionStarted) {
                transaction.rollback();
                System.out.println("Транзакция откачена для метода: " + method.getName());
            }
            throw exception;
        }
        return result;
    }
}
