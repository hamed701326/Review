package ir.techco.review.repo.impl;

import ir.techco.review.repo.document.BlockUser;
import ir.techco.review.repo.extra.BlockUserRepoExtra;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Date;

public class BlockUserRepoImpl implements BlockUserRepoExtra {
    private final MongoOperations mongoOperations;

    public BlockUserRepoImpl(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    @Override
    public void upsert(BlockUser blockUser) {
        Criteria criteria = Criteria.where(BlockUser.UserIdCol).is(blockUser.getUserId());
        var query = new Query(criteria);
        var update = new Update().set(BlockUser.BlockedCol,blockUser.isBlocked())
                .setOnInsert(BlockUser.CreateDateCol,new Date());
        mongoOperations.upsert(query,update,BlockUser.Name  );
    }
}
