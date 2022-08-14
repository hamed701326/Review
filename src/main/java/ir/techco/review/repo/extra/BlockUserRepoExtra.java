package ir.techco.review.repo.extra;

import ir.techco.review.repo.document.BlockUser;

public interface BlockUserRepoExtra {
    void upsert(BlockUser blockUser);
}
