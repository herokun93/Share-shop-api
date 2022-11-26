package share.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import share.shop.exceptions.ResourceNotFoundException;
import share.shop.models.Comment;
import share.shop.models.Product;
import share.shop.models.User;
import share.shop.payloads.CommentNewRequest;
import share.shop.securities.UserLogged;
import share.shop.services.CommentService;
import share.shop.services.ProductService;
import share.shop.services.UserService;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @PostMapping(value = "/comments")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> postComment(@Valid @RequestBody CommentNewRequest commentNewRequest){

        UserLogged userLogged = new UserLogged();
        User user = userService.findByEmail(userLogged.getEmail()).orElseThrow(
                ()->new ResourceNotFoundException("User","email",userLogged.getEmail())
        );
        Product product = productService.findById(commentNewRequest.getProductId()).orElseThrow(
                ()-> new ResourceNotFoundException("Product","id",commentNewRequest.getProductId())
        );


        Comment comment = new Comment();
        comment.setProduct(product);
        comment.setUser(user);

        comment = commentService.save(comment);
        if(Objects.isNull(comment)) return ResponseEntity.badRequest().build();

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/comments/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteComment(
            @PathVariable("id") Long commentId){

        Comment comment = commentService.findById(commentId).orElseThrow(
                ()->new ResourceNotFoundException("Comment","id",commentId)
        );

        commentService.deleteById(commentId);

        return ResponseEntity.ok().build();
    }


}
