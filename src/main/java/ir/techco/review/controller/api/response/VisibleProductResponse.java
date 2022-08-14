package ir.techco.review.controller.api.response;

import java.util.Set;

public class VisibleProductResponse {
    private Set<String> productIds;

    public VisibleProductResponse(Set<String> productIds) {
        this.productIds = productIds;
    }

    public Set<String> getProductIds() {
        return productIds;
    }
}
