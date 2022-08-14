package ir.techco.review.controller.admin.request;

import javax.validation.constraints.NotNull;

public class CommentValidateRequest {
    @NotNull
    private boolean valid;
    private String invalidReason;

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getInvalidReason() {
        return invalidReason;
    }

    public void setInvalidReason(String invalidReason) {
        this.invalidReason = invalidReason;
    }
}
