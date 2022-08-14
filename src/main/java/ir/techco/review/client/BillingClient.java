package ir.techco.review.client;

import ir.techco.review.client.request.BuyValidationRequest;
import ir.techco.review.client.response.BuyValidationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "BillingClient",
        url = "${services.billing}",
        fallback = BillingClientFallback.class
)
public interface BillingClient {
    @PostMapping("/api/validate/buy")
    BuyValidationResponse validate(@RequestBody BuyValidationRequest request);
}
