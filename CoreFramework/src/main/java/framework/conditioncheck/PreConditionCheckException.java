package framework.conditioncheck;

/**
 * Any rule that violates while engaging a PreConditionException object.
 */

public class PreConditionCheckException extends RuntimeException {

    public PreConditionCheckException(String errorMessage) {
        super(errorMessage);
    }

    public PreConditionCheckException(Throwable error) {
        super(error);
    }

    public PreConditionCheckException(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }

}
