package ir.techco.review.repo.document;

import ir.techco.review.enums.AccessLevel;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = GeneralAccess.Name)
public class GeneralAccess {
    public static final String Name = "GeneralAccess";
    public static final String ID ="_id";
    public static final String ProfileCol = "profile";
    public static final String CommentAccessLevelCol = "commentAccessLevel";
    public static final String CreateDateCol = "createDate";
    public static final String LastUpdateCol = "lastUpdate";

    @Id
    private String id;
    @Field(ProfileCol)
    private String profile;
    @Field(CommentAccessLevelCol)
    private AccessLevel commentAccessLevel;
    @Field(CreateDateCol)
    private Date createDate;
    @Field(LastUpdateCol)
    private Date lastUpdate;

    public GeneralAccess(String profile, AccessLevel commentAccessLevel) {
        this.profile = profile;
        this.commentAccessLevel = commentAccessLevel;
        this.createDate = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public AccessLevel getCommentAccessLevel() {
        return commentAccessLevel;
    }

    public void setCommentAccessLevel(AccessLevel commentAccessLevel) {
        this.commentAccessLevel = commentAccessLevel;
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
}
