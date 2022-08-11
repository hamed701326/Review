package ir.techco.review.repo.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.Objects;

@Document(collection = Comment.Name)
public class Comment {
    public static final String Name = "Comment";
    public static final String ID = "_id";
    public static final String TextCol = "text";
    public static final String UserIdCol = "userId";
    public static final String ProductIdCol = "productId";
    public static final String ValidCol = "valid";
    public static final String ValidationNeededCol = "validationNeeded";
    public static final String InvalidReasonCol = "invalidReason";
    public static final String CreateDateCol = "createDate";
    public static final String LastUpdateCol = "lastUpdate";

    @Id
    private String id;
    @Field(TextCol)
    private String text;
    @Field(UserIdCol)
    private String userId;
    @Field(ProductIdCol)
    @Indexed
    private String productId;
    @Field(ValidCol)
    private boolean valid;
    @Field(InvalidReasonCol)
    private String invalidReason;
    @Field(ValidationNeededCol)
    private boolean validationNeeded = true;
    @Field(CreateDateCol)
    private Date createDate = new Date();
    @Field(LastUpdateCol)
    private Date lastUpdate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

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

    public boolean isValidationNeeded() {
        return validationNeeded;
    }

    public void setValidationNeeded(boolean validationNeeded) {
        this.validationNeeded = validationNeeded;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, userId, productId, createDate);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Comment other))
            return false;
        if (this.hashCode() != other.hashCode())
            return false;
        return Objects.equals(this.text, other.text) &&
                Objects.equals(this.userId, other.userId) &&
                Objects.equals(this.productId, other.productId) &&
                Objects.equals(this.createDate, other.createDate);
    }
}
