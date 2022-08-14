package ir.techco.review.client.response;

public class BuyValidationResponse {
    private boolean valid;

    public BuyValidationResponse() {
    }

    public BuyValidationResponse(boolean valid) {
        this.valid = valid;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
