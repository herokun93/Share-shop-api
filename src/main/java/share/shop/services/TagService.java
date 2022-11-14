package share.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import share.shop.models.Category;
import share.shop.models.Tag;
import share.shop.payloads.CategoryResponse;
import share.shop.payloads.PagedResponse;
import share.shop.payloads.TagResponse;
import share.shop.repositories.TagRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    public Tag save(Tag tag){
        return tagRepository.save(tag);
    }

    public Optional<Tag> finById(Long id){
        return tagRepository.findById(id);
    }
    public Optional<Tag> findByName(String name){return tagRepository.findByName(name);};

    public boolean existsByName(String name){return  tagRepository.existsByName(name);}

    public PagedResponse getAllTags(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Tag> tags = tagRepository.findAll(pageable);

        if(tags.getNumberOfElements() ==0){
            return new PagedResponse(Collections.emptyList(),tags.getNumber(),tags.getSize(),
                    tags.getTotalElements(),tags.getTotalPages(),tags.isLast());
        }

        List<TagResponse> tagResponses = tags.map(tag -> {
            TagResponse tagResponse = new TagResponse();
            return tagResponse.tagConvert(tag);
        }).getContent();

        return new PagedResponse<>(tagResponses,tags.getNumber(),tags.getSize(),tags.getTotalElements(),
                tags.getTotalPages(),tags.isLast());
    }

    public void addTagForProduct(Long tagId,Long productId){
        tagRepository.addTagForProduct(tagId,productId);
    }
}
