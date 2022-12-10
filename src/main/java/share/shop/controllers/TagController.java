package share.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import share.shop.exceptions.ResourceNotFoundException;
import share.shop.models.Tag;
import share.shop.payloads.request.ProductIdRequest;
import share.shop.payloads.request.TagRequest;
import share.shop.payloads.response.PagedResponse;
import share.shop.payloads.response.TagResponse;
import share.shop.services.TagService;
import share.shop.utils.AppConstants;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TagController {

    @Autowired
    private TagService tagService;

    @PostMapping(value = "/tags")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> postTag(@Valid @RequestBody TagRequest tagRequest) {

        String name = tagRequest.getName();
        Optional<Tag> tagGet = tagService.findByName(name);
        if(!tagGet.isEmpty()) return new ResponseEntity(null, HttpStatus.BAD_REQUEST);

        Tag tagNew = Tag.builder().name(tagRequest.getName()).build();
        Tag tag = tagService.save(tagNew);

        if(Objects.isNull(tag))return new ResponseEntity(null, HttpStatus.BAD_REQUEST);


        TagResponse tagResponse = new TagResponse();
        return new ResponseEntity(tagResponse.tagConvert(tag), HttpStatus.OK);
    }

    @PutMapping(value = "/tags/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> putTag(
            @Valid  @PathVariable("id") @Min(0) Long tagId,
            @Valid @RequestBody TagRequest tagRequest) {

        String name = tagRequest.getName();


        Tag tag = tagService.finById(tagId).orElseThrow(()->{
            throw new ResourceNotFoundException("Tag","id",tagId);
        });

        Optional<Tag> tagGet =tagService.findByName(name);

        if(!tagGet.isEmpty())  return new ResponseEntity(null, HttpStatus.BAD_REQUEST);


        tag.setName(name);
        tag = tagService.save(tag);

        if(Objects.isNull(tag))  return new ResponseEntity(null, HttpStatus.BAD_REQUEST);

        TagResponse tagResponse = new TagResponse();


        return new ResponseEntity(tagResponse.tagConvert(tag), HttpStatus.OK);

    }

    @GetMapping(value="/tags")
    public PagedResponse getAllTags(
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return tagService.getAllTags(page,size);
    }

    @GetMapping(value="/tags/{id}/products")
    public PagedResponse getAllProductsOfTag(
            @Valid  @PathVariable("id") @Min(0) Long tagId,
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return tagService.getAllTags(page,size);
    }

    @PostMapping(value="/tags/{id}/addProduct")
    public PagedResponse postTagForProduct(
            @Valid  @PathVariable("id") @Min(0) Long tagId,
            @Valid @RequestBody ProductIdRequest productIdRequest) {

        Long productId = productIdRequest.getId();

        tagService.addTagForProduct(tagId,productId);
        return null;
    }
}
