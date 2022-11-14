package share.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import share.shop.models.Product;
import share.shop.models.SubCategory;
import share.shop.payloads.PagedResponse;
import share.shop.payloads.ProductCard;
import share.shop.payloads.SubCategoryResponse;
import share.shop.repositories.ProductRepository;
import share.shop.repositories.SubCategoryRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class SubCategoryService {

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public SubCategory save(SubCategory subCategory){
        return  subCategoryRepository.save(subCategory);
    }
    public boolean existsByName(String name){return subCategoryRepository.existsByName(name);}
    public Optional<SubCategory> findById(Long id){return  subCategoryRepository.findById(id);};

    public PagedResponse getAllSubCategories(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
        Page<SubCategory> subCategories = subCategoryRepository.findAll(pageable);

        if(subCategories.getNumberOfElements() ==0){
            return new PagedResponse(Collections.emptyList(),subCategories.getNumber(),subCategories.getSize(),
                    subCategories.getTotalElements(),subCategories.getTotalPages(),subCategories.isLast());
        }

        List<SubCategoryResponse> categoryResponsesPage = subCategories.map(subCategory -> {
            SubCategoryResponse subCategoryResponse = new SubCategoryResponse();
            return subCategoryResponse.subCategoryConvert(subCategory);
        }).getContent();

        return new PagedResponse<>(categoryResponsesPage,subCategories.getNumber(),subCategories.getSize(),subCategories.getTotalElements(),
                subCategories.getTotalPages(),subCategories.isLast());
    }

    public PagedResponse getAllProductForSubCategory(int subCategoryId,int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
        Page<Product> products = productRepository.findAllBySubCategoryId(subCategoryId,pageable);

        if(products.getNumberOfElements() ==0){
            return new PagedResponse(Collections.emptyList(),products.getNumber(),products.getSize(),
                    products.getTotalElements(),products.getTotalPages(),products.isLast());
        }

        List<ProductCard> productCardList = products.map(product -> {
            ProductCard productCard = new ProductCard();
            return productCard.productCardConvert(product);
        }).getContent();

        return new PagedResponse<>(productCardList,products.getNumber(),products.getSize(),products.getTotalElements(),
                products.getTotalPages(),products.isLast());
    }


}
