package share.shop.data;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import share.shop.models.*;
import share.shop.repositories.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

@Component
@Slf4j
public class SampleDataLoader implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PrefectureRepository prefectureRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CountryRepository countryRepository;



    private final Faker faker;

    public SampleDataLoader() {
        this.faker = new Faker();
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Start add sample data");

//        createCountries();
//        createPrefectures();
//        createRoles();
//        setAdmin();
//        createUsers();
//        createCategories();
//        createSubCategory();
//        createProducts();
        log.info("Finish add sample data");



    }

    public void createCountries(){
        List<Country> countries = IntStream.range(1,100)
                .mapToObj(i->new Country(faker.address().country(),true)).toList();
        countryRepository.saveAll(countries);
    }

    public void createRoles(){
        List<Role> roles = new ArrayList<>();
        roles.add(new Role(RoleName.ROLE_ADMIN));
        roles.add(new Role(RoleName.ROLE_PARTNER));
        roles.add(new Role(RoleName.ROLE_STAFF));
        roles.add(new Role(RoleName.ROLE_USER));


        roleRepository.saveAll(roles);
    }

    public void createPrefectures(){
        List<Prefecture> prefectures = IntStream.range(1,64)
                .mapToObj(i->new Prefecture(faker.address().cityName())).toList();

        prefectureRepository.saveAll(prefectures);
    }

    public void setAdmin(){
        String password = passwordEncoder.encode("20222023");

        Set<Role> roles = new HashSet<>();

        Role roleAdmin = roleRepository.findByName(RoleName.ROLE_ADMIN).get();
        roles.add(roleAdmin);

        Role roleUser = roleRepository.findByName(RoleName.ROLE_ADMIN).get();
        roles.add(roleUser);

        Prefecture prefecture = prefectureRepository.getReferenceById(Long.valueOf(1));

        User userAdmin = new User("anhxthangdang@gmail.com",password);
        userAdmin.setRoles(roles);
        userAdmin.setPrefecture(prefecture);

        userRepository.save(userAdmin);
    }

    public void createUsers(){
        String password = passwordEncoder.encode("20222023");

        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.findByName(RoleName.ROLE_USER).get();
        roles.add(role);

        Prefecture prefecture = prefectureRepository.getReferenceById(Long.valueOf(1));

        List<User> users = IntStream.range(1,5)
                .mapToObj(u->new User(faker.internet().emailAddress(),password)).toList();

        users.forEach(u->{
            u.setRoles(roles);
            u.setPrefecture(prefecture);
            userRepository.save(u);
        });
    }

    public void createCategories(){

        List<Category> categories = IntStream.range(1,50)
                .mapToObj(i->new Category("Category " +i,true)).toList();

        categoryRepository.saveAll(categories);
    }

    public void createSubCategory(){
        List<Category> categories = categoryRepository.findAll();
        categories.forEach(category -> {
            List<SubCategory> subCategories = IntStream.range(1,30)
                    .mapToObj(s->new SubCategory("SubCategory "+s,true)).toList();

            subCategories.forEach(su->{
                su.setCategory(category);
            });
            subCategoryRepository.saveAll(subCategories);
        });
    }

    public void createProducts(){
        List<SubCategory> subCategories = subCategoryRepository.findAll();
        subCategories.forEach(subCategory -> {
            List<Product> products = IntStream.range(1,30)
                    .mapToObj(p->new Product("Product "+p,true,3,"Product description ","Product descriptionSort","Tiktok",true)).toList();

            products.forEach(p->{
                p.setSubCategory(subCategory);
            });
            productRepository.saveAll(products);
        });
    }



}
