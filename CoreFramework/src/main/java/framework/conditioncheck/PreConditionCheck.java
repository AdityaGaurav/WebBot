package framework.conditioncheck;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.UrlValidator;

public final class PreConditionCheck {

    private PreConditionCheck() {
    }

    /**
     *
     * @param reference Object instance to check
     * @param errorMessage Error message
     * @param <T> Return Type
     * @return
     */
    public static <T> T checkNotNull(T reference, Object errorMessage) {
        if (reference == null) {
            throw new PreConditionCheckException(new NullPointerException(String.valueOf(errorMessage)));
        }
        return reference;
    }

    /**
     *
     * @param s
     * @param message
     * @return
     */
    public static String checkNotNullNotBlankOrEmpty(String s, String message) {
        checkNotNull(s, message);
        if (s.isEmpty()) {
            throw new PreConditionCheckException(new IllegalArgumentException(message));
        }
        if (StringUtils.isBlank(s)) {
            throw new PreConditionCheckException(new IllegalArgumentException(message));
        }

        return s;
    }
    //todo: method documentation
    public static boolean isValidURL(String URL){
        checkNotNullNotBlankOrEmpty(URL,"Not a valid url.");
        UrlValidator urlValidator = new UrlValidator();
        return urlValidator.isValid(URL);
    }

    //todo: method documentation
    public static boolean isNull(String value){
        return value == null;
    }
}
