package framework.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface BugInfo {
    enum Priority {
        HIGH, MEDIUM, LOW;
    }

    String bugID();

    String bugDescription();

    Priority priority();

    int linkedUserStory();
}
