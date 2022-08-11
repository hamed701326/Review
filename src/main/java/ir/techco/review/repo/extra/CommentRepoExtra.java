package ir.techco.review.repo.extra;

import ir.techco.review.repo.document.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;

public interface CommentRepoExtra {
    Page<Comment> getComments(Criteria criteria, Pageable pageable);

    void validateComment(String commentId, boolean isValid, String invalidReason);
}
