package exception;

public class BadCredentialsException extends RuntimeException {
    public BadCredentialsException(String e) {
        super(e);
    }
}
