package ir.techco.review.repo.impl;

import com.mongodb.client.result.UpdateResult;
import ir.techco.review.controller.admin.request.AccessChangeRequest;
import ir.techco.review.repo.document.ProductAccess;
import ir.techco.review.repo.extra.ProductAccessRepoExtra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Date;

public class ProductAccessRepoImpl implements ProductAccessRepoExtra {
    private final MongoOperations mongoOperations;

    public ProductAccessRepoImpl(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    @Override
    public long upsert(ProductAccess productAccess) {
        var criteria = Criteria.where(ProductAccess.ProductIdCol).is(productAccess.getProductId());
        var query = new Query(criteria);
        var update = new Update().set(ProductAccess.LastUpdateCol, new Date())
                .setOnInsert(ProductAccess.CreateDateCol, productAccess.getCreateDate())
                .setOnInsert(ProductAccess.ProviderIdCol, productAccess.getProviderId())
                .set(ProductAccess.VisibleCol, productAccess.isVisible())
                .set(ProductAccess.CommentableCol, productAccess.isCommentable());
        UpdateResult result = mongoOperations.upsert(query, update, ProductAccess.Name);
        return result.getModifiedCount();
    }

    @Override
    public Page<ProductAccess> getProductAccesses(Criteria criteria, Pageable pageable) {
        var query = new Query(criteria);
        var total = mongoOperations.count(query, ProductAccess.Name);
        var result = mongoOperations.find(query, ProductAccess.class);
        return new PageImpl(result, pageable, total);
    }

    @Override
    public long changeAccessByProviderId(String providerId, ProductAccess productAccess) {
        var criteria = Criteria.where(ProductAccess.ProviderIdCol).is(providerId);
        var query = new Query(criteria);
        var update = new Update().set(ProductAccess.LastUpdateCol, new Date());
        if (productAccess.isVisible() != null)
            update.set(ProductAccess.VisibleCol, productAccess.isVisible());
        if (productAccess.isCommentable() != null)
            update.set(ProductAccess.CommentableCol, productAccess.isCommentable());
        UpdateResult result = mongoOperations.updateMulti(query, update, ProductAccess.Name);
        return result.getModifiedCount();
    }
}
