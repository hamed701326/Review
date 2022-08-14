package ir.techco.review.service;

import ir.techco.review.controller.admin.request.AccessChangeRequest;
import ir.techco.review.controller.admin.request.LimitAccessRequest;
import ir.techco.review.controller.api.request.ProductVisibilityCheckRequest;
import ir.techco.review.repo.ProductAccessRepo;
import ir.techco.review.repo.document.ProductAccess;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductAccessService {
    private final ProductAccessRepo productAccessRepo;

    public ProductAccessService(ProductAccessRepo productAccessRepo) {
        this.productAccessRepo = productAccessRepo;
    }

    public Set<String> getVisibleProductIds(ProductVisibilityCheckRequest request){
        Set<String> productIds = request.getProductIds();
        List<ProductAccess> invisibleProduct = productAccessRepo.findAllByProductIdInAndVisible(productIds, false);
        Set<String> invisibleProductIds = invisibleProduct.stream().map(ProductAccess::getProductId).collect(Collectors.toSet());

        return productIds.stream().filter(productId-> !invisibleProductIds.contains(productId)).collect(Collectors.toSet());
    }

    public void limitProduct(LimitAccessRequest request) {
        ProductAccess productAccess = new ProductAccess();
        productAccess.setProductId(request.getProductId());
        productAccess.setProviderId(request.getProviderId());
        productAccess.setVisible(request.isVisible());
        productAccess.setCommentable(request.isCommentable());
        productAccessRepo.upsert(productAccess);
    }

    public ProductAccess getProductAccess(String productId) {
        return productAccessRepo.findProductAccessByProductId(productId).orElseThrow(

        );
    }

    public Page<ProductAccess> getAllByProviderId(String providerId, int skip, int size) {
        Sort sort = Sort.by(ProductAccess.CreateDateCol).descending();
        Pageable pageable = PageRequest.of(skip,size,sort);
        Criteria criteria = Criteria.where(ProductAccess.ProviderIdCol).is(providerId);
        return productAccessRepo.getProductAccesses(criteria,pageable);
    }

    public long changeAccess(String productId, AccessChangeRequest request) {
        ProductAccess productAccess = new ProductAccess();
        productAccess.setProductId(productId);
        productAccess.setVisible(request.isVisible());
        productAccess.setCommentable(request.isCommentable());
        return productAccessRepo.upsert(productAccess);

    }

    public long changeAccessByProvider(String providerId, AccessChangeRequest request) {
        ProductAccess productAccess = new ProductAccess();
        productAccess.setVisible(request.isVisible());
        productAccess.setCommentable(request.isCommentable());
        return productAccessRepo.changeAccessByProviderId(providerId,productAccess);
    }
}
