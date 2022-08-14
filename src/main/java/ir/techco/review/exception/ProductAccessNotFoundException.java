package ir.techco.review.exception;

public class ProductAccessNotFoundException extends RuntimeException{
    public static final String DefaultMessage = "ProductAccessNotFound";
    public ProductAccessNotFoundException() {
        super(DefaultMessage);
    }

    public ProductAccessNotFoundException(String message) {
        super(message);
    }

    public ProductAccessNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
