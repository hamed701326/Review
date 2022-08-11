package ir.techco.review.repo;

import ir.techco.review.repo.document.Comment;
import ir.techco.review.repo.extra.CommentRepoExtra;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepo extends MongoRepository<Comment, String>, CommentRepoExtra {

}
