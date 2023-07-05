package common.exceptions;

public class BrowserNotSupportException extends Exception {
    public BrowserNotSupportException(String browserName) {
        super(String.format("Browser \"%s\" not supported", browserName));
    }
}
