package exception;

public class NoSuchUserException extends RuntimeException{
    public NoSuchUserException(String e) {
        super(e);
    }
}
