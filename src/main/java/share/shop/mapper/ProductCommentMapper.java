package share.shop.mapper;

import org.modelmapper.ModelMapper;
import share.shop.dto.comment.CommentProductDto;
import share.shop.models.Comment;

import java.util.ArrayList;
import java.util.List;

public class ProductCommentMapper {

    public static CommentProductDto convert(Comment comment){

        ModelMapper modelMapper = new ModelMapper();

        CommentProductDto commentProductDto = modelMapper.map(comment, CommentProductDto.class);
        return  commentProductDto;
    }

    public static List<CommentProductDto> listsConvert(List<Comment> commentList){

        List<CommentProductDto> productComments = new ArrayList<>();
        commentList.forEach(comment -> {
            productComments.add(convert(comment));
        });

        return  productComments;
    }
}
