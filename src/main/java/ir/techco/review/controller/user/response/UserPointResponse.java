package ir.techco.review.controller.user.response;

public class UserPointResponse {
    private String productId;
    private Integer point;
    private Long totalRate;
    private boolean hasVote;

    public UserPointResponse(String productId, Integer point, Long totalRate, boolean hasVote) {
        this.productId = productId;
        this.point = point;
        this.totalRate = totalRate;
        this.hasVote = hasVote;
    }

    public static UserPointResponse emptyResponse(String productId) {

        return new UserPointResponse(productId, null, null, false);
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Long getTotalRate() {
        return totalRate;
    }

    public void setTotalRate(Long totalRate) {
        this.totalRate = totalRate;
    }

    public boolean isHasVote() {
        return hasVote;
    }

    public void setHasVote(boolean hasVote) {
        this.hasVote = hasVote;
    }
}
