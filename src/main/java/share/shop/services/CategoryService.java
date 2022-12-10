package share.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import share.shop.models.Category;
import share.shop.models.SubCategory;
import share.shop.payloads.response.CategoryResponse;
import share.shop.payloads.response.PagedResponse;
import share.shop.payloads.response.SubCategoryResponse;
import share.shop.repositories.CategoryRepository;
import share.shop.repositories.SubCategoryRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    public  Category save(Category category){
        return categoryRepository.save(category);
    }

    public Optional<Category> findById(Long id){
        return categoryRepository.findById(id);
    }
    public Optional<Category> findByName(String name){return categoryRepository.findByName(name);}
    public boolean existsByName(String name){return categoryRepository.existsByName(name);}

    public PagedResponse getAllCategories(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Category> categories = categoryRepository.findAll(pageable);

        if(categories.getNumberOfElements() ==0){
            return new PagedResponse(Collections.emptyList(),categories.getNumber(),categories.getSize(),
                    categories.getTotalElements(),categories.getTotalPages(),categories.isLast());
        }

        List<CategoryResponse> categoryResponsesPage = categories.map(category -> {
            CategoryResponse categoryResponse = new CategoryResponse();
            return categoryResponse.categoryResponseConvert(category);
        }).getContent();

        return new PagedResponse<>(categoryResponsesPage,categories.getNumber(),categories.getSize(),categories.getTotalElements(),
                categories.getTotalPages(),categories.isLast());
    }

    public PagedResponse getAllSubCategoryOfCategory(long categoryId,int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<SubCategory> subCategories = subCategoryRepository.findAllByCategoryId(categoryId,pageable);

        if(subCategories.getNumberOfElements() ==0){
            return new PagedResponse(Collections.emptyList(),subCategories.getNumber(),subCategories.getSize(),
                    subCategories.getTotalElements(),subCategories.getTotalPages(),subCategories.isLast());
        }

        List<SubCategoryResponse> subCategoryResponses = subCategories.map(subCategory -> {
            SubCategoryResponse subCategoryResponse = new SubCategoryResponse();
            return subCategoryResponse.subCategoryConvert(subCategory);
        }).getContent();

        return new PagedResponse<>(subCategoryResponses,subCategories.getNumber(),subCategories.getSize(),subCategories.getTotalElements(),
                subCategories.getTotalPages(),subCategories.isLast());
    }









}
