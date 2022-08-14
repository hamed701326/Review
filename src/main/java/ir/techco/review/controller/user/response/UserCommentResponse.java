package ir.techco.review.controller.user.response;

import java.util.*;

public class UserCommentResponse {
    private String id;
    private String text;
    private int point;
    private String userId;
    private Date createDate;

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public int getPoint() {
        return point;
    }

    public String getUserId() {
        return userId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
