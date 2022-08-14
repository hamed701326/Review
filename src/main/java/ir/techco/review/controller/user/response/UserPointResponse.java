package ir.techco.review.controller.user.response;

public class UserPointResponse {
    private String productId;
    private Float point;
    private Long totalRate;
    private boolean hasVote;

    public UserPointResponse(String productId, Float point, Long totalRate, boolean hasVote) {
        this.productId = productId;
        this.point = point;
        this.totalRate = totalRate;
        this.hasVote = hasVote;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Float getPoint() {
        return point;
    }

    public void setPoint(Float point) {
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
