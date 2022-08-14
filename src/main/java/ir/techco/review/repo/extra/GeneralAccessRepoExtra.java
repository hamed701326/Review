package ir.techco.review.repo.extra;

import ir.techco.review.enums.AccessLevel;

public interface GeneralAccessRepoExtra {
    void updateCommentAccess(String profile, AccessLevel accessLevel);
}
