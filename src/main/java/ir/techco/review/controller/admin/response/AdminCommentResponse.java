package ir.techco.review.controller.admin.response;

import java.util.Date;

public class AdminCommentResponse {
    public String id;
    public String text;
    public String productId;
    public String userId;
    public boolean valid;
    public boolean needValidation;
    public String invalidReason;
    public Date createDate;
    public Date lastUpdate;
}
