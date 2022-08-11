package ir.techco.review.controller.user.response;

import java.util.*;

public class UserCommentResponse {
    private final String id;
    private final String text;
    private final String userId;
    private final Date createDate;

    public UserCommentResponse(String id, String text, String userId, Date createDate) {
        this.id = id;
        this.text = text;
        this.userId = userId;
        this.createDate = createDate;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getUserId() {
        return userId;
    }

    public Date getCreateDate() {
        return createDate;
    }
}
