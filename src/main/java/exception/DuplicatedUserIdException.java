package exception;

public class DuplicatedUserIdException extends RuntimeException {
    public DuplicatedUserIdException(String e) {
        super(e);
    }
}
