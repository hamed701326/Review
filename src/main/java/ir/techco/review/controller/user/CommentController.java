package ir.techco.review.controller.user;

import ir.techco.review.repo.CommentRepo;
import ir.techco.review.repo.document.Comment;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/comment")
public class CommentController {
    private final CommentRepo commentRepo;

    public CommentController(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }

    @GetMapping("/list/{productId}/{skip}/{limit}")
    public List<Comment> getComments(@PathVariable String productId,
                                     @PathVariable int skip,
                                     @PathVariable int limit) {
        Sort sort = Sort.by(Comment.CreateDateCol).descending();
        Pageable pageable = PageRequest.of(skip,limit, sort);
        return commentRepo.getCommentsByProductId(productId,pageable);
    }
}
