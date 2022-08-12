package ir.techco.review.exception;

public class CommentNotAllowedException extends RuntimeException {
    public static final String DefaultMessage = "CommentNotAllowed";

    public CommentNotAllowedException() {
        super(DefaultMessage);
    }

    public CommentNotAllowedException(String message) {
        super(message);
    }

    public CommentNotAllowedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommentNotAllowedException(Throwable cause) {
        super(cause);
    }

    protected CommentNotAllowedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
