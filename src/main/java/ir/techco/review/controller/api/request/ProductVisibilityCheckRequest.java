package ir.techco.review.controller.api.request;

import java.util.Set;

public class ProductVisibilityCheckRequest {
    private Set<String> productIds;

    public Set<String> getProductIds() {
        return productIds;
    }

    public void setProductIds(Set<String> productIds) {
        this.productIds = productIds;
    }
}
