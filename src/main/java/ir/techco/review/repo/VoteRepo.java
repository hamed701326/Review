package ir.techco.review.repo;

import ir.techco.review.repo.document.Vote;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VoteRepo extends MongoRepository<Vote,String> {
}
