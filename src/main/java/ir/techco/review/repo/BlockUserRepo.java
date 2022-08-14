package ir.techco.review.repo;

import ir.techco.review.repo.document.BlockUser;
import ir.techco.review.repo.extra.BlockUserRepoExtra;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BlockUserRepo extends MongoRepository<BlockUser,String>, BlockUserRepoExtra{
    boolean existsBlockUserByUserIdAndBlocked(String userId,boolean isBlocked);
}
