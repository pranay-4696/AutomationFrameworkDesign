package web.exceptions;

/**
 * @author pranaym
 * @implNote Throw the exception when you wanted to notify the problem occurred while accessing
 * Finance Center Pages and its corresponding operations.
 */
public class OrangeHrmWebException extends RuntimeException {

    public OrangeHrmWebException(String errorMessage) {
        super(errorMessage);
    }
}