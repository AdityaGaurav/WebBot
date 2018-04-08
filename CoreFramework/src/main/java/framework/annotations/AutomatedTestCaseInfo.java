package framework.annotations;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AutomatedTestCaseInfo {
    String createdBy();

    String lastModified();
}
