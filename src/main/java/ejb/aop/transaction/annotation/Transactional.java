package ejb.aop.transaction.annotation;

import ejb.aop.transaction.Isolation;
import ejb.aop.transaction.Propagation;

import javax.interceptor.InterceptorBinding;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@InterceptorBinding
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Transactional {
    Isolation isolation() default Isolation.DEFAULT;
    Propagation propagation() default Propagation.REQUIRES;
}
