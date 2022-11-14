package share.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import share.shop.models.SubCategory;
import share.shop.repositories.SubCategoryRepository;

@Service
public class SubCategoryService {

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    public SubCategory save(SubCategory subCategory){
        return  subCategoryRepository.save(subCategory);
    }
    public boolean existsByName(String name){return subCategoryRepository.existsByName(name);}
}
