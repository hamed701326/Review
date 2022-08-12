package ir.techco.review.repo.impl;

import ir.techco.review.repo.document.Comment;
import ir.techco.review.repo.extra.CommentRepoExtra;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public class CommentRepoImpl implements CommentRepoExtra {
    public static final String AVG_POINT = "avgPoint";
    public static final String TotalVote = "totalVote";
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

    @Override
    public List<ProductPoint> aggregateOnRate(List<String> productIds) {
        var criteria = Criteria.where(Comment.ProductIdCol).is(productIds)
                .and(Comment.ValidCol).is(true);
        var matchOperation = Aggregation.match(criteria);
        var countOperation = Aggregation.count().as(TotalVote);
        var groupOperation = Aggregation.group(Comment.ProductIdCol).avg(Comment.PointCol).as(AVG_POINT);
        var aggregation = Aggregation.newAggregation(matchOperation,countOperation,groupOperation);
        AggregationResults<ProductPoint> results = mongoOperations.aggregate(aggregation, Comment.Name, ProductPoint.class);
        return results.getMappedResults();
    }


    public static class ProductPoint {
        @Id
        private String productId;
        private Integer avgPoint;
        private Long totalVote;

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public Integer getAvgPoint() {
            return avgPoint;
        }

        public void setAvgPoint(Integer avgPoint) {
            this.avgPoint = avgPoint;
        }

        public Long getTotalVote() {
            return totalVote;
        }

        public void setTotalVote(Long totalVote) {
            this.totalVote = totalVote;
        }

        public static ProductPoint emptyProductPoint(){
            return new ProductPoint();
        }

    }
}
