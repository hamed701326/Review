package ir.techco.review.controller.user;

import ir.techco.review.controller.user.request.CommentAddRequest;
import ir.techco.review.controller.user.response.UserCommentAccessResponse;
import ir.techco.review.controller.user.response.UserCommentResponse;
import ir.techco.review.exception.NotAllowedException;
import ir.techco.review.helper.PermissionHelper;
import ir.techco.review.repo.document.Comment;
import ir.techco.review.service.CommentService;
import ir.techco.review.utils.PageResponse;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user/comment")
public class UserCommentController {
    private final CommentService commentService;
    private final PermissionHelper permissionHelper;
    private final ModelMapper modelMapper;

    public UserCommentController(CommentService commentService, PermissionHelper permissionHelper, ModelMapper modelMapper) {
        this.commentService = commentService;
        this.permissionHelper = permissionHelper;
        this.modelMapper = modelMapper;
    }
    @GetMapping("/hasAccess/{productId}/{userId}")
    public UserCommentAccessResponse hasAccess(@PathVariable String productId, @PathVariable String userId) {
        return new UserCommentAccessResponse(permissionHelper.isAllowedForComment(userId,productId));
    }
    @PostMapping("/add/{productId}")
    public UserCommentResponse addComment(@PathVariable String productId,
                                          @RequestBody @Valid CommentAddRequest request) {
        if (!permissionHelper.isAllowedForComment(request.getUserId(), productId))
            throw new NotAllowedException("CommentNotAllowed");
        return toUserCommentResponse(commentService.createComment(productId, request));
    }

    @GetMapping("/list/{productId}")
    public PageResponse<UserCommentResponse> getComments(@PathVariable String productId,
                                                 @RequestParam int skip,
                                                 @RequestParam int limit) {
        Page<UserCommentResponse> page = commentService.getComments(productId, skip, limit)
                .map(this::toUserCommentResponse);
        return new PageResponse<>(page);
    }

    public UserCommentResponse toUserCommentResponse(Comment comment) {
        return modelMapper.map(comment,UserCommentResponse.class);
    }


}
