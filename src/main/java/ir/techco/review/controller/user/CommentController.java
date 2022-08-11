package ir.techco.review.controller.user;

import ir.techco.review.controller.user.request.CommentAddRequest;
import ir.techco.review.controller.user.response.UserCommentResponse;
import ir.techco.review.exception.CommentNotAllowedException;
import ir.techco.review.helper.PermissionHelper;
import ir.techco.review.repo.document.Comment;
import ir.techco.review.service.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/comment")
public class CommentController {
    private final CommentService commentService;
    private final PermissionHelper permissionHelper;

    public CommentController(CommentService commentService, PermissionHelper permissionHelper) {
        this.commentService = commentService;
        this.permissionHelper = permissionHelper;
    }

    @GetMapping("/list/{productId}/{skip}/{limit}")
    public Page<UserCommentResponse> getComments(@PathVariable String productId,
                                                 @PathVariable int skip,
                                                 @PathVariable int limit) {
        return commentService.getComments(productId, skip, limit)
                .map(this::toUserCommentResponse);
    }

    @PostMapping("/add/{productId}")
    public UserCommentResponse addComment(@PathVariable String productId,
                                          @RequestBody CommentAddRequest request) {
        if (!permissionHelper.isAllowedForComment(request.getUserId(), productId))
            throw new CommentNotAllowedException();
        return toUserCommentResponse(commentService.createComment(productId, request));
    }

    public UserCommentResponse toUserCommentResponse(Comment comment) {
        return new UserCommentResponse(comment.getId(), comment.getText(), comment.getUserId(), comment.getCreateDate());
    }


}
