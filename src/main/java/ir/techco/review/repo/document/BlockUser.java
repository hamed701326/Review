package ir.techco.review.repo.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(BlockUser.Name)
public class BlockUser {
    public static final String Name = "BlockUser";
    public static final String ID = "_id";
    public static final String UserIdCol = "userId";
    public static final String BlockedCol = "blocked";
    public static final String CreateDateCol = "createDate";

    @Id
    private String id;
    @Field(UserIdCol)
    @Indexed(unique = true)
    private String userId;
    @Field(BlockedCol)
    private boolean blocked;
    @Field(CreateDateCol)
    private Date createDate;

    public BlockUser(String userId, boolean blocked) {
        this.userId = userId;
        this.blocked = blocked;
        this.createDate = new Date();
    }

    public BlockUser() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
