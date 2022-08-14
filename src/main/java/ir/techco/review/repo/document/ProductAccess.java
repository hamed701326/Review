package ir.techco.review.repo.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.Objects;

@Document(collection = ProductAccess.Name)
public class ProductAccess {
    public static final String Name = "ProductAccess";
    public static final String ID = "_id";
    public static final String ProductIdCol = "productId";
    public static final String ProviderIdCol = "providerId";
    public static final String VisibleCol = "visible";
    public static final String CommentableCol = "commentable";
    public static final String CreateDateCol = "createDate";
    public static final String LastUpdateCol = "lastUpdate";

    @Id
    private String id;
    @Field(ProductIdCol)
    @Indexed(unique = true)
    private String productId;
    @Field(ProviderIdCol)
    @Indexed
    private String providerId;
    @Field(VisibleCol)
    private Boolean visible;
    @Field(CommentableCol)
    private Boolean commentable;
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

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public Boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Boolean isCommentable() {
        return commentable;
    }

    public void setCommentable(Boolean commentable) {
        this.commentable = commentable;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.productId, this.providerId);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ProductAccess other))
            return false;
        if (this.hashCode() != other.hashCode())
            return false;
        return Objects.equals(this.productId, other.productId) &&
                Objects.equals(this.providerId, other.providerId) &&
                Objects.equals(this.commentable, other.commentable) &&
                Objects.equals(this.visible, other.visible);
    }
}
