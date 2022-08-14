package ir.techco.review.repo.impl;

import ir.techco.review.enums.AccessLevel;
import ir.techco.review.repo.document.GeneralAccess;
import ir.techco.review.repo.extra.GeneralAccessRepoExtra;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public class GeneralAccessRepoImpl implements GeneralAccessRepoExtra {
    private final MongoOperations mongoOperations;

    public GeneralAccessRepoImpl(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    @Override
    public void updateCommentAccess(String profile, AccessLevel accessLevel) {
        var criteria = Criteria.where(GeneralAccess.ProfileCol).is(profile);
        var update = new Update().set(GeneralAccess.CommentAccessLevelCol,accessLevel);
        mongoOperations.updateFirst(Query.query(criteria),update,GeneralAccess.Name);
    }
}
