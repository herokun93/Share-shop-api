package share.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import share.shop.models.Tag;
import share.shop.repositories.TagRepository;

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
}
