package share.shop.services;

import org.hibernate.jpa.QueryHints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import share.shop.dto.ProductCardDto;
import share.shop.mapper.ProductCardMapper;
import share.shop.mapper.TagsMapper;
import share.shop.models.Product;
import share.shop.models.Tag;
import share.shop.payloads.response.PagedResponse;
import share.shop.payloads.response.TagResponse;
import share.shop.repositories.ProductRepository;
import share.shop.repositories.TagRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private ProductRepository productRepository;

    @PersistenceContext
    private EntityManager entityManager;

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
        return new PagedResponse<>(TagsMapper.listTags(tags.stream().toList()),tags.getNumber(),tags.getSize(),tags.getTotalElements(),
                tags.getTotalPages(),tags.isLast());
    }

    public List<ProductCardDto> getProductByTagId(Long id){
        Optional<Tag> tag = tagRepository.findById(id);

        return ProductCardMapper.listConvert(tag.get().getProducts().stream().toList());

    }

    public List<Product> findAllPagedByTagId(int page, int size, long tagId){
        String sql = "SELECT p FROM Product p JOIN FETCH p.images JOIN FETCH p.tags t WHERE t.id = :tagId";
        Query query = entityManager.createQuery(sql, Product.class);
        query.setParameter("tagId", tagId);
        query.setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false);

        List<Product> productList = query.getResultList();

        return productList;
    }

    public void addTagForProduct(Long tagId,Long productId){
        tagRepository.addTagForProduct(tagId,productId);
    }
}
