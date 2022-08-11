package ir.techco.review.repo.impl;

import ir.techco.review.repo.document.Comment;
import ir.techco.review.repo.extra.CommentRepoExtra;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class CommentRepoImpl implements CommentRepoExtra {
    @Override
    public List<Comment> getCommentsByProductId(String productId, Pageable pageable) {
        return null;
    }
}
