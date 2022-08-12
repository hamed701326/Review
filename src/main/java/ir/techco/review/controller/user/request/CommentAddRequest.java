package ir.techco.review.controller.user.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class CommentAddRequest {
    private String userId;
    private String text;
    @Min(value = 0, message = "Point should not be less than 0")
    @Max(value = 10, message = "Point should not be greater than 10")
    private Integer point;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }
}
