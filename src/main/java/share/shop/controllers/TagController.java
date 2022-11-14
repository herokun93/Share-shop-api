package share.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import share.shop.models.Category;
import share.shop.models.Tag;
import share.shop.payloads.CategoryRequest;
import share.shop.payloads.TagRequest;
import share.shop.payloads.TagResponse;
import share.shop.services.TagService;

import javax.validation.Valid;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TagController {

    @Autowired
    private TagService tagService;

    @PostMapping("/tags")
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
}
