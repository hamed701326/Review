package ir.techco.review.client.request;

public class BuyValidationRequest {
    private String userId;
    private String productId;

    public BuyValidationRequest(String userId, String productId) {
        this.userId = userId;
        this.productId = productId;
    }

    public String getUserId() {
        return userId;
    }

    public String getProductId() {
        return productId;
    }
}
