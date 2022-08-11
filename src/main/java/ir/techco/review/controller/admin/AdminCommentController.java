package ir.techco.review.controller.admin;

import ir.techco.review.controller.admin.request.CommentValidateRequest;
import ir.techco.review.controller.admin.response.AdminCommentResponse;
import ir.techco.review.enums.AccessLevel;
import ir.techco.review.helper.PermissionHelper;
import ir.techco.review.repo.document.Comment;
import ir.techco.review.service.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/comment")
public class AdminCommentController {
    private final CommentService commentService;
    private final PermissionHelper permissionHelper;

    public AdminCommentController(CommentService commentService, PermissionHelper permissionHelper) {
        this.commentService = commentService;
        this.permissionHelper = permissionHelper;
    }

    @GetMapping("/validation-needed/{skip}/{limit}")
    public Page<AdminCommentResponse> getValidationNeededComments(@RequestParam(required = false) String productId,
                                                                  @PathVariable int skip,
                                                                  @PathVariable int limit) {
        return commentService.getCommentsNeedValidation(productId, skip, limit)
                .map(this::toAdminCommentResponse);
    }

    @GetMapping("/all/{skip}/{limit}")
    public Page<AdminCommentResponse> getComments(@RequestParam(required = false) String productId,
                                                  @PathVariable int skip,
                                                  @PathVariable int limit) {
        Page<Comment> comments = commentService.getComments(productId, skip, limit);
        return comments.map(this::toAdminCommentResponse);
    }

    @PutMapping("/validate/{commentId}")
    public void validateComment(@PathVariable String commentId, CommentValidateRequest request) {
        commentService.validateComment(commentId, request);
    }

    @PutMapping("/access")
    public void changeAccess(@RequestParam AccessLevel accessLevel) {
        permissionHelper.setCommentAccessLevel(accessLevel);
    }

    @PutMapping("/access/{productId}/")
    public void changeAccess(@PathVariable String productId,
                             @RequestParam AccessLevel accessLevel) {
        permissionHelper.setCommentAccessLevel(accessLevel, productId);
    }

    private AdminCommentResponse toAdminCommentResponse(Comment comment) {
        return new AdminCommentResponse(
                comment.getId(),
                comment.getText(),
                comment.getProductId(),
                comment.getUserId(),
                comment.isValid(),
                comment.isValidationNeeded(),
                comment.getInvalidReason(),
                comment.getCreateDate(),
                comment.getLastUpdate());
    }

}
