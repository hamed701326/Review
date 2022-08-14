package ir.techco.review.controller.admin.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class AdminCommentResponse {
    private String id;
    private String text;
    private Integer point;
    private String productId;
    private String userId;
    private boolean valid;
    private String invalidReason;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date createDate;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date lastUpdate;

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

    public String getInvalidReason() {
        return invalidReason;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public void setInvalidReason(String invalidReason) {
        this.invalidReason = invalidReason;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
