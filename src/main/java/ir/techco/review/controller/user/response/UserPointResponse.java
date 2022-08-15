package ir.techco.review.controller.user.response;

public class UserPointResponse {
    private String productId;
    private Float point;
    private Long totalVote;
    private boolean hasVote;

    public UserPointResponse(String productId, Float point, Long totalVote, boolean hasVote) {
        this.productId = productId;
        this.point = point;
        this.totalVote = totalVote;
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

    public Long getTotalVote() {
        return totalVote;
    }

    public void setTotalVote(Long totalVote) {
        this.totalVote = totalVote;
    }

    public boolean isHasVote() {
        return hasVote;
    }

    public void setHasVote(boolean hasVote) {
        this.hasVote = hasVote;
    }
}
