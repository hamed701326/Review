package ir.techco.review.client;

import ir.techco.review.client.request.BuyValidationRequest;
import ir.techco.review.client.response.BuyValidationResponse;
import org.springframework.stereotype.Component;

@Component
public class BillingClientFallback implements BillingClient {
    @Override
    public BuyValidationResponse validate(BuyValidationRequest request) {
        return new BuyValidationResponse(false);
    }
}
