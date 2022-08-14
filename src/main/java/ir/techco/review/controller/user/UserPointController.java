package ir.techco.review.controller.user;

import ir.techco.review.controller.user.response.UserPointResponse;
import ir.techco.review.repo.impl.CommentRepoImpl;
import ir.techco.review.service.CommentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/point")
public class UserPointController {
    private final CommentService commentService;

    public UserPointController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{productId}")
    public UserPointResponse getPointByProduct(@PathVariable String productId) {
        CommentRepoImpl.ProductPoint rateByProduct = commentService.getPointByProduct(productId);
        if (rateByProduct != null)
            return new UserPointResponse(productId,rateByProduct.getAvgPoint(), rateByProduct.getTotalVote(), true);
        return new UserPointResponse(productId,null,null,false);

    }

}
