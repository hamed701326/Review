package ir.techco.review.repo.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
@Document(collection = Vote.Name)
public class Vote {
    public static final String Name = "Vote";
    public static final String ID = "_id";
    public static final String ProductIdCol = "productId";
    public static final String UserIdCol = "userId";
    public static final String PointCol = "point";
    public static final String CreateDateCol = "createDate";
    @Id
    private String id;
    @Field(ProductIdCol)
    @Indexed
    private String productId;
    @Field(UserIdCol)
    private String userId;
    @Field(PointCol)
    private int point;
    @Field(CreateDateCol)
    private Date createDate;
}
