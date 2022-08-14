package ir.techco.review.controller.admin.response;

public class AdminAccessChangeResponse {
    private long modifiedCount;

    public AdminAccessChangeResponse(long modifiedCount) {
        this.modifiedCount = modifiedCount;
    }

    public long getModifiedCount() {
        return modifiedCount;
    }
}
