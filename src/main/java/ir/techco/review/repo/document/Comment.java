package ir.techco.review.repo.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.Objects;

@Document
public class Comment {
    public static final String ID = "_id";
    public static final String TextCol = "text";
    public static final String UserIdCol = "userId";
    public static final String ProductIdCol = "productId";
    public static final String ValidCol = "valid";
    public static final String CreateDateCol = "createDate";

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
    private boolean valid = false;
    @Field(CreateDateCol)
    private Date createDate;

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

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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
