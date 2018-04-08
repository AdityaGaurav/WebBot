package framework.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.CLASS)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface UnderDevelopment {
    boolean isUnderDevelopment();
}
