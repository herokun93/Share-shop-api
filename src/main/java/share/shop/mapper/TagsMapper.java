package share.shop.mapper;

import org.modelmapper.ModelMapper;
import share.shop.dto.TagDto;
import share.shop.dto.TagViewDto;
import share.shop.models.Tag;

import java.util.ArrayList;
import java.util.List;

public class TagsMapper {
    public static TagViewDto convert(Tag tag){
        ModelMapper modelMapper = new ModelMapper();
        TagViewDto tagViewDto = modelMapper.map(tag,TagViewDto.class);
        return tagViewDto;
    }

    public static List<TagViewDto> listTags(List<Tag> tags){
        List<TagViewDto> tagViewDtoList = new ArrayList<>();
        tags.forEach(tag -> {
            tagViewDtoList.add(convert(tag));
        });
        return  tagViewDtoList;
    }
}
