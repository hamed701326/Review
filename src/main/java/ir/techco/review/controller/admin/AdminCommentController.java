package ir.techco.review.controller.admin;

import ir.techco.review.controller.admin.request.CommentValidateRequest;
import ir.techco.review.controller.admin.response.AdminCommentResponse;
import ir.techco.review.helper.PermissionHelper;
import ir.techco.review.repo.document.Comment;
import ir.techco.review.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/comment")
public class AdminCommentController {
    private final CommentService commentService;
    private final ModelMapper modelMapper;

    public AdminCommentController(CommentService commentService, ModelMapper modelMapper) {
        this.commentService = commentService;
        this.modelMapper = modelMapper;
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

    private AdminCommentResponse toAdminCommentResponse(Comment comment) {
        return modelMapper.map(comment,AdminCommentResponse.class);
    }

}
