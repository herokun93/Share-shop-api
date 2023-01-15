package share.shop.mapper;

import org.modelmapper.ModelMapper;
import share.shop.dto.comment.CommentProductDto;
import share.shop.models.Comment;

import java.util.ArrayList;
import java.util.List;

public class CommentMapper {
    public static CommentProductDto commentConvert(Comment comment){

        ModelMapper modelMapper = new ModelMapper();

        CommentProductDto commentProductDto = modelMapper.map(comment, CommentProductDto.class);
        return  commentProductDto;
    }

    public static List<CommentProductDto> toListComments(List<Comment> commentList){

        List<CommentProductDto> productComments = new ArrayList<>();
        commentList.forEach(comment -> {
            productComments.add(commentConvert(comment));
        });

        return  productComments;
    }
}
