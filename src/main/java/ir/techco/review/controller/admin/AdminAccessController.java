package ir.techco.review.controller.admin;

import ir.techco.review.controller.admin.request.AccessChangeRequest;
import ir.techco.review.controller.admin.request.LimitAccessRequest;
import ir.techco.review.controller.admin.response.AdminAccessChangeResponse;
import ir.techco.review.controller.admin.response.AdminAccessResponse;
import ir.techco.review.enums.AccessLevel;
import ir.techco.review.helper.PermissionHelper;
import ir.techco.review.repo.document.ProductAccess;
import ir.techco.review.service.ProductAccessService;
import ir.techco.review.utils.PageResponse;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/access")
public class AdminAccessController {
    private final ModelMapper modelMapper;
    private final ProductAccessService productAccessService;
    private final PermissionHelper permissionHelper;

    public AdminAccessController(ModelMapper modelMapper, ProductAccessService productAccessService, PermissionHelper permissionHelper) {
        this.modelMapper = modelMapper;
        this.productAccessService = productAccessService;
        this.permissionHelper = permissionHelper;
    }

    @PostMapping("/limit")
    public void limitProductAccess(LimitAccessRequest request){
       productAccessService.limitProduct(request);
    }

    @GetMapping("/product/{productId}")
    public AdminAccessResponse getProductAccess(@PathVariable String productId){
      ProductAccess productAccess =  productAccessService.getProductAccess(productId);
      return modelMapper.map(productAccess,AdminAccessResponse.class);
    }

    @GetMapping("/all/{providerId}")
    public PageResponse<AdminAccessResponse> getAllAccessByProviderId(@PathVariable String providerId,
                                                                      @RequestParam int skip,
                                                                      @RequestParam int size){
        Page<ProductAccess> page = productAccessService.getAllByProviderId(providerId,skip,size);
        Page<AdminAccessResponse> result = page.map(productAccess -> modelMapper.map(productAccess, AdminAccessResponse.class));
        return new PageResponse<>(result);
    }

    @PutMapping("/product/{productId}")
    public AdminAccessChangeResponse changeAccess(@PathVariable String productId,
                                                  @RequestBody AccessChangeRequest request){
       long modifiedCount = productAccessService.changeAccess(productId,request);
       return new AdminAccessChangeResponse(modifiedCount);
    }

    @PutMapping("/all/{providerId}")
    public AdminAccessChangeResponse changeAccessByProvider(@PathVariable String providerId,
                                                  @RequestBody AccessChangeRequest request){
        long modifiedCount = productAccessService.changeAccessByProvider(providerId,request);
        return new AdminAccessChangeResponse(modifiedCount);
    }

    @PutMapping("/general/comment")
    public void changeAccess(@RequestParam AccessLevel accessLevel) {
        permissionHelper.setCommentAccessLevel(accessLevel);
    }

    @PutMapping("/block/{userId}")
    public void blockUser(@PathVariable String userId){
        permissionHelper.changeBlockUser(userId,true);
    }

    @PutMapping("/unblock/{userId}")
    public void unblockUser(@PathVariable String userId){
        permissionHelper.changeBlockUser(userId,false);
    }






}
