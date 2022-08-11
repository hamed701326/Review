package ir.techco.review.service;

import ir.techco.review.controller.admin.request.CommentValidateRequest;
import ir.techco.review.controller.user.request.CommentAddRequest;
import ir.techco.review.repo.CommentRepo;
import ir.techco.review.repo.document.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final CommentRepo commentRepo;

    public CommentService(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }

    public Page<Comment> getComments(int skip, int limit) {
        Sort sort = Sort.by(Comment.CreateDateCol).descending();
        Pageable pageable = PageRequest.of(skip, limit, sort);
        return commentRepo.findAll(pageable);
    }

    public Page<Comment> getComments(String productId, int skip, int limit) {
        Criteria criteria = Criteria.where(Comment.ProductIdCol).is(productId)
                .and(Comment.ValidCol).is(true);
        Sort sort = Sort.by(Comment.CreateDateCol).descending();
        Pageable pageable = PageRequest.of(skip, limit, sort);
        return commentRepo.getComments(criteria, pageable);
    }

    public Page<Comment> getCommentsNeedValidation(String productId, int skip, int limit) {
        Criteria criteria = Criteria.where(Comment.ValidationNeededCol).is(true);
        if(productId!=null) criteria.and(Comment.ProductIdCol).in(productId);
        Sort sort = Sort.by(Comment.CreateDateCol).descending();
        Pageable pageable = PageRequest.of(skip, limit, sort);
        return commentRepo.getComments(criteria, pageable);
    }

    public Comment createComment(String productId, CommentAddRequest request) {
        Comment comment = new Comment();
        comment.setProductId(productId);
        comment.setText(request.getText());
        comment.setUserId(request.getUserId());
         return commentRepo.save(comment);
    }

    public void validateComment(String commentId, CommentValidateRequest request) {
        commentRepo.validateComment(commentId, request.isValid(), request.getReason());
    }
}
