package share.shop.payloads;

import lombok.*;
import org.modelmapper.ModelMapper;
import share.shop.models.Comment;
import share.shop.payloads.audit.DateAuditResponse;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse extends DateAuditResponse {

    private Long id;
    private String comment;
    private String userName;

    public CommentResponse commentConvert(Comment comment){
        ModelMapper modelMapper = new ModelMapper();
        CommentResponse commentResponse = modelMapper.map(comment, CommentResponse.class);
        return  commentResponse;
    }

    public List<CommentResponse> commentsConvert(List<Comment> comments){
        ModelMapper modelMapper = new ModelMapper();
        List<CommentResponse> commentResponseList = new ArrayList<>();
        comments.forEach(comment -> {
            commentResponseList.add(modelMapper.map(comment, CommentResponse.class));
        });
        return  commentResponseList;
    }
}
