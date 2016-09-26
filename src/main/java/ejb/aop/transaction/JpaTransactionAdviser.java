package ejb.aop.transaction;

import ejb.aop.transaction.annotation.Transactional;
import ejb.persistence.store.EntityManagerStore;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

@Interceptor
@Transactional
public class JpaTransactionAdviser {

    @Inject
    private EntityManagerStore entityManagerStore;

    @AroundInvoke
    public Object advice(InvocationContext invocationContext) throws Throwable {

        EntityManager entityManager = entityManagerStore.createAndRegister();

        Object result = null;

        EntityTransaction transaction = entityManager.getTransaction();

        try {

            transaction.begin();
            result = invocationContext.proceed();
            transaction.commit();

        } catch (Throwable throwable) {
            if (transaction.isActive()) {
                transaction
                        .rollback();
            }
            throw throwable;
        } finally {
            entityManagerStore
                    .unregister(entityManager);
            entityManager
                    .close();
        }
        return result;
    }

}
