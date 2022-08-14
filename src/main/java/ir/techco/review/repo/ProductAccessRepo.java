package ir.techco.review.repo;

import ir.techco.review.repo.document.ProductAccess;
import ir.techco.review.repo.extra.ProductAccessRepoExtra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ProductAccessRepo extends MongoRepository<ProductAccess,String>,
        ProductAccessRepoExtra {

    boolean existsProductAccessByProductIdAndCommentable(String productId, boolean commentable);

    Optional<ProductAccess> findProductAccessByProductId(String productId);

    List<ProductAccess> findAllByProductIdInAndVisible(Set<String> productIds,boolean visible);

}
