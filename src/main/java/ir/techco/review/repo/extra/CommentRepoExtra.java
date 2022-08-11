package ir.techco.review.repo.extra;

import ir.techco.review.repo.document.Comment;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentRepoExtra {
    List<Comment> getCommentsByProductId(String productId, Pageable pageable);
}
