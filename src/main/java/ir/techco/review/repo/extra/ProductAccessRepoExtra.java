package ir.techco.review.repo.extra;

import ir.techco.review.controller.admin.request.AccessChangeRequest;
import ir.techco.review.repo.document.ProductAccess;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;

public interface ProductAccessRepoExtra {
    long upsert(ProductAccess productAccess);

    Page<ProductAccess> getProductAccesses(Criteria criteria, Pageable pageable);

    long changeAccessByProviderId(String providerId, ProductAccess productAccess);
}
