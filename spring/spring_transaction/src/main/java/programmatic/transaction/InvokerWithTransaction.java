package programmatic.transaction;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import programmatic.support.ApplicationContextHelper;

import java.util.concurrent.Callable;

public abstract class InvokerWithTransaction<V> implements Runnable, Callable<V> {

    @Autowired
    private PlatformTransactionManager txManager;

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(InvokerWithTransaction.class);

    public InvokerWithTransaction() {
        ApplicationContextHelper.autowiredBeanPropertyValues(this);
    }

    public final void run() {
        invoke();
    }

    @Override
    public final V call() throws Exception {
        return invoke();
    }

    public final V invoke() {
        TransactionTemplate transactionTemplate = new TransactionTemplate(txManager);
        return transactionTemplate.execute((status) -> invokeInTransaction());
    }

    protected V invokeInTransaction() {
        return null;
    }

    public static Runnable wrappWithTransaction(Runnable r) {
        return new InvokerWithTransaction() {
            @Override
            protected Object invokeInTransaction() {
                r.run();
                return null;
            }
        };
    }

    public static <R> Callable<R> wrappWithTransaction(Callable<R> r) {
        return new InvokerWithTransaction<R>() {
            @Override
            protected R invokeInTransaction() {
                try {
                    return r.call();
                } catch (Exception e) {
                    LOGGER.error("InvokerWithTransaction.error.when.call", e);
                    throw new RuntimeException(e);
                }
            }
        };
    }

    public static void invokeRunnableWithTransaction(Runnable r) {
        new InvokerWithTransaction() {
            @Override
            protected Object invokeInTransaction() {
                r.run();
                return null;
            }
        }.invoke();
    }

    public static <R> R invokeCallableWithTransaction(Callable<R> r) {
        return new InvokerWithTransaction<R>() {
            @Override
            protected R invokeInTransaction() {
                try {
                    return r.call();
                } catch (Exception e) {
                    LOGGER.error("InvokerWithTransaction.error.when.call", e);
                    throw new RuntimeException(e);
                }
            }
        }.invoke();
    }
}
