package ir.techco.review.controller.admin.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class AdminCommentResponse {
    private final String id;
    private final String text;
    private final String productId;
    private final String userId;
    private final boolean valid;
    private final boolean needValidation;
    private final String invalidReason;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private final Date createDate;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private final Date lastUpdate;

    public AdminCommentResponse(String id, String text, String productId,
                                String userId, boolean valid, boolean needValidation,
                                String invalidReason, Date createDate, Date lastUpdate) {
        this.id = id;
        this.text = text;
        this.productId = productId;
        this.userId = userId;
        this.valid = valid;
        this.needValidation = needValidation;
        this.invalidReason = invalidReason;
        this.createDate = createDate;
        this.lastUpdate = lastUpdate;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getProductId() {
        return productId;
    }

    public String getUserId() {
        return userId;
    }
    public boolean isValid() {
        return valid;
    }

    public boolean isNeedValidation() {
        return needValidation;
    }

    public String getInvalidReason() {
        return invalidReason;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

}
