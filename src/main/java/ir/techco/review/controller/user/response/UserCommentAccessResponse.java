package ir.techco.review.controller.user.response;

public class UserCommentAccessResponse {
    private boolean hasAccess;

    public UserCommentAccessResponse(boolean hasAccess) {
        this.hasAccess = hasAccess;
    }

    public boolean isHasAccess() {
        return hasAccess;
    }
}
