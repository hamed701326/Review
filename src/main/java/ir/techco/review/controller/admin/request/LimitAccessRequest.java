package ir.techco.review.controller.admin.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class LimitAccessRequest {
    @NotNull @NotEmpty
    private String providerId;
    @NotNull @NotEmpty
    private String productId;
    private Boolean visible;
    private Boolean commentable;

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Boolean isCommentable() {
        return commentable;
    }

    public void setCommentable(Boolean commentable) {
        this.commentable = commentable;
    }
}
