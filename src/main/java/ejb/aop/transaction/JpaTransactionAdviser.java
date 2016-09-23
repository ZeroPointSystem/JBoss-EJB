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

            Transactional transactionalAnnotation = invocationContext
                    .getMethod()
                    .getAnnotation(Transactional.class);

            Isolation isolation = transactionalAnnotation.isolation();

            Propagation propagation = transactionalAnnotation.propagation();

            switch (propagation) {
                case REQUIRES:
                    if (!transaction.isActive()) {
                        transaction
                                .begin();
                    }
                    break;
                case REQUIRES_NEW:
                    transaction
                            .begin();
                    break;
                default:
                    break;
            }

            result = invocationContext
                    .proceed();
            transaction
                    .commit();

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
