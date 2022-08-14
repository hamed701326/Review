package ir.techco.review.controller.admin;

import ir.techco.review.controller.admin.request.CommentValidateRequest;
import ir.techco.review.controller.admin.response.AdminCommentResponse;
import ir.techco.review.helper.PermissionHelper;
import ir.techco.review.repo.document.Comment;
import ir.techco.review.service.CommentService;
import ir.techco.review.utils.PageResponse;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin/comment")
public class AdminCommentController {
    private final CommentService commentService;
    private final ModelMapper modelMapper;

    public AdminCommentController(CommentService commentService, ModelMapper modelMapper) {
        this.commentService = commentService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/validation-needed")
    public PageResponse<AdminCommentResponse> getValidationNeededComments(@RequestParam(required = false) String productId,
                                                                          @RequestParam(defaultValue = "0") int skip,
                                                                          @RequestParam(defaultValue = "0") int limit) {
        Page<AdminCommentResponse> result = commentService.getCommentsNeedValidation(productId, skip, limit)
                .map(this::toAdminCommentResponse);
        return new PageResponse<>(result);
    }

    @GetMapping("/all")
    public PageResponse<AdminCommentResponse> getComments(@RequestParam(required = false) String productId,
                                                  @RequestParam(defaultValue = "0") int skip,
                                                  @RequestParam(defaultValue = "10") int limit) {
        Page<Comment> comments = commentService.getComments(productId, skip, limit);
        Page<AdminCommentResponse> result = comments.map(this::toAdminCommentResponse);
        return new PageResponse(result);
    }

    @PutMapping("/validate/{commentId}")
    public void validateComment(@PathVariable String commentId,@RequestBody @Valid CommentValidateRequest request) {
        commentService.validateComment(commentId, request);
    }

    private AdminCommentResponse toAdminCommentResponse(Comment comment) {
        return modelMapper.map(comment,AdminCommentResponse.class);
    }

}
