package share.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import share.shop.models.Comment;
import share.shop.models.Product;
import share.shop.payloads.CommentResponse;
import share.shop.payloads.PagedResponse;
import share.shop.payloads.ProductCard;
import share.shop.repositories.CommentRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public Comment save(Comment comment){return commentRepository.save(comment);}
    public Optional<Comment> findById(Long id){return commentRepository.findById(id);};
    public void deleteById(long id){  commentRepository.deleteById(id);};

    public PagedResponse getAllCommentsByProductId(Long productId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Comment> comments = commentRepository.findAllByProductId(productId,pageable);

        if(comments.getNumberOfElements()==0){
            return new PagedResponse<>(Collections.emptyList(), comments.getNumber(), comments.getSize(),
                    comments.getTotalElements(), comments.getTotalPages(), comments.isLast());
        }



        List<CommentResponse> commentResponseList = comments.map(comment -> {
            CommentResponse commentResponse = new CommentResponse();
            return  commentResponse.commentConvert(comment);
        }).getContent();

        return new PagedResponse<>(commentResponseList, comments.getNumber(), comments.getSize(), comments.getTotalElements(),
                comments.getTotalPages(), comments.isLast());
    }

    public PagedResponse findAllCommentsByUserId(Long userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
        Page<Comment> comments = commentRepository.findAllByUserId(userId,pageable);

        if(comments.getNumberOfElements()==0){
            return new PagedResponse<>(Collections.emptyList(), comments.getNumber(), comments.getSize(),
                    comments.getTotalElements(), comments.getTotalPages(), comments.isLast());
        }



        List<CommentResponse> commentResponseList = comments.map(comment -> {
            CommentResponse commentResponse = new CommentResponse();
            return  commentResponse.commentConvert(comment);
        }).getContent();

        return new PagedResponse<>(commentResponseList, comments.getNumber(), comments.getSize(), comments.getTotalElements(),
                comments.getTotalPages(), comments.isLast());
    }

}
