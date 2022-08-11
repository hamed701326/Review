package ir.techco.review.helper;

import ir.techco.review.enums.AccessLevel;
import org.springframework.stereotype.Component;

@Component
public class PermissionHelper {

    public boolean isAllowedForComment(String userId, String productId) {
        if (isAllowedForAll())
            return true;
        return false;
    }

    private boolean isAllowedForAll() {
        //todo: should implement
        return false;
    }

    public void setCommentAccessLevel(AccessLevel accessLevel) {

    }

    public void setCommentAccessLevel(AccessLevel accessLevel, String productId) {

    }
}
