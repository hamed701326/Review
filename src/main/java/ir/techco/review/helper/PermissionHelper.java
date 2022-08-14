package ir.techco.review.helper;

import ir.techco.review.client.BillingClient;
import ir.techco.review.client.request.BuyValidationRequest;
import ir.techco.review.client.response.BuyValidationResponse;
import ir.techco.review.enums.AccessLevel;
import ir.techco.review.repo.BlockUserRepo;
import ir.techco.review.repo.GeneralAccessRepo;
import ir.techco.review.repo.ProductAccessRepo;
import ir.techco.review.repo.document.BlockUser;
import ir.techco.review.repo.document.GeneralAccess;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PermissionHelper {
    @Value("${access.comment.level}")
    private AccessLevel commentAccessLevel;
    @Value("${spring.profiles.active:default}")
    private String profile;

    private final BlockUserRepo blockUserRepo;
    private final GeneralAccessRepo generalAccessRepo;
    private final ProductAccessRepo productAccessRepo;
    private final BillingClient billingClient;

    public PermissionHelper(BlockUserRepo blockUserRepo, GeneralAccessRepo generalAccessRepo, ProductAccessRepo productAccessRepo, BillingClient billingClient) {
        this.blockUserRepo = blockUserRepo;
        this.generalAccessRepo = generalAccessRepo;
        this.productAccessRepo = productAccessRepo;
        this.billingClient = billingClient;
    }

    public boolean isAllowedForComment(String userId, String productId) {

        if (isBlockUser(userId) || !isProductCommentable(productId))
            return false;

        return switch (getCommentAccessLevel()) {
            case ALL -> true;
            case SHOPPER -> hasBoughtProduct(userId, productId);
            default -> false;
        };
    }

    private boolean isProductCommentable(String productId) {
        return productAccessRepo.existsProductAccessByProductIdAndCommentable(productId, true);
    }

    private boolean isBlockUser(String userId) {
        return blockUserRepo.existsBlockUserByUserIdAndBlocked(userId, true);
    }


    private boolean hasBoughtProduct(String userId, String productId) {
        BuyValidationRequest request = new BuyValidationRequest(userId, productId);
        BuyValidationResponse validate = billingClient.validate(request);
        return validate.isValid();
    }

    public void setCommentAccessLevel(AccessLevel accessLevel) {
        generalAccessRepo.updateCommentAccess(profile,accessLevel);
    }

    public void changeBlockUser(String userId,boolean isBlock){
        BlockUser blockUser = new BlockUser(userId,isBlock);
        blockUserRepo.upsert(blockUser);
    }


    private AccessLevel getCommentAccessLevel() {
        GeneralAccess generalAccess = generalAccessRepo.findGeneralAccessByProfile(profile);
        if (generalAccess != null)
            return generalAccess.getCommentAccessLevel();
        generalAccessRepo.save(new GeneralAccess(profile, commentAccessLevel));
        return commentAccessLevel;
    }
}
