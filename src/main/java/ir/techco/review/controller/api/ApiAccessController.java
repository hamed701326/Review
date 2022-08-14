package ir.techco.review.controller.api;

import ir.techco.review.controller.api.request.ProductVisibilityCheckRequest;
import ir.techco.review.controller.api.response.VisibleProductResponse;
import ir.techco.review.service.ProductAccessService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/access")
public class ApiAccessController {
    private final ProductAccessService productAccessService;

    public ApiAccessController(ProductAccessService productAccessService) {
        this.productAccessService = productAccessService;
    }

    @GetMapping("/visible")
    public VisibleProductResponse getVisibleProduct(ProductVisibilityCheckRequest request){
        Set<String> visibleProductIds = productAccessService.getVisibleProductIds(request);
        return new VisibleProductResponse(visibleProductIds);
    }
}
