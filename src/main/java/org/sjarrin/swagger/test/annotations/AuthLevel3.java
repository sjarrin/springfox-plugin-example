package org.sjarrin.swagger.test.annotations;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthLevel3 {
    String[] issuers() default {};
}
