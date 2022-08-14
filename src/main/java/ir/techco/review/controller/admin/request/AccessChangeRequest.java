package ir.techco.review.controller.admin.request;

public class AccessChangeRequest {
    private Boolean visible;
    private Boolean commentable;

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
