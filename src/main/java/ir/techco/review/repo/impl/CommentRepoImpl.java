package ir.techco.review.repo.impl;

import ir.techco.review.repo.document.Comment;
import ir.techco.review.repo.extra.CommentRepoExtra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public class CommentRepoImpl implements CommentRepoExtra {
    private final MongoOperations mongoOperations;

    public CommentRepoImpl(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    @Override
    public Page<Comment> getComments(Criteria criteria, Pageable pageable) {
        var query = new Query(criteria);
        var total = mongoOperations.count(query, Comment.Name);
        var result = mongoOperations.find(query, Comment.class);
        return new PageImpl(result, pageable, total);
    }


    @Override
    public void validateComment(String commentId, boolean isValid, String invalidReason) {
        var criteria = Criteria.where(Comment.ID).is(commentId);
        var query = new Query(criteria);
        var update = new Update().currentDate(Comment.LastUpdateCol)
                .set(Comment.ValidCol, isValid)
                .set(Comment.ValidationNeededCol, false);
        if (!isValid) update.set(Comment.InvalidReasonCol, invalidReason);
        mongoOperations.updateFirst(query, update, Comment.Name);
    }
}
