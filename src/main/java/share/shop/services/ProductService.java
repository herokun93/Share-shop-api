package share.shop.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import share.shop.dto.ProductCardDto;
import share.shop.mapper.ProductCardMapper;
import share.shop.models.Country;
import share.shop.models.Product;
import share.shop.payloads.response.PagedResponse;
import share.shop.payloads.response.ProductCardResponse;
import share.shop.payloads.response.custom.ProductCard;
import share.shop.payloads.response.custom.ProductDetails;
import share.shop.payloads.response.custom.ProductsCard;
import share.shop.payloads.response.icustom.ITProduct;
import share.shop.repositories.ProductRepository;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.Instant;
import java.util.*;

@Service
@Slf4j
public class ProductService {


    @PersistenceContext
    private EntityManager entityManager;



    @Autowired
    private ProductRepository productRepository;

    public ProductsCard getPageProductCardByShopId(long shopId,long page){


        long size = page*5;



//        List<ITProduct> itProducts = productRepository.getProductCardByShopId(Instant.now(),shopId,size,page);
//
//
//        ProductsCard productsCard = new ProductsCard(itProducts);

        return null;
    }

    public ProductsCard getProductCardByShopId(long shopId,long page, long size){

//        List<ITProduct> itProducts = productRepository.getProductCardByShopId(Instant.now(),shopId,size,page);


//        ProductsCard productsCard = new ProductsCard(itProducts);

       return null;
    }

    public ProductDetails getProductDetailsById(long productId ){
//        List<ITProduct> itProducts = productRepository.getProductDetailsById(Instant.now(),productId);
//        ProductDetails productDetails = new ProductDetails( itProducts);
       // log.info("productDetails {}",productDetails.toString());
        return  null;
    }

    public Optional<Product> findById(Long id){
        return productRepository.findById(id);
    }
    public Product findProductById(Long id){

        EntityGraph entityGraph = entityManager.getEntityGraph("product.detail");
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.fetchgraph", entityGraph);
        Product product = entityManager.find(Product.class, id, properties);

        return product;
    }
    public Optional<Product>findByShopIdAndId(long shopId,long productId){return  productRepository.findByShopIdAndId(shopId, productId);}
    public Product save(Product product){return productRepository.save(product);};

    public List<Product> findByModeLessThanAndEnable(int mode,boolean enable){
        return productRepository.findByModeLessThanAndEnable(mode,enable);
    }


    public Product saveAndFlush(Product product){return productRepository.saveAndFlush(product);};


    public PagedResponse getProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
        Page<Product> products = productRepository.findAll(pageable);

        if(products.getNumberOfElements()==0){
            return new PagedResponse<>(Collections.emptyList(), products.getNumber(), products.getSize(),
                    products.getTotalElements(), products.getTotalPages(), products.isLast());
        }

        return new PagedResponse<>(ProductCardMapper.listConvert(products.stream().toList()), products.getNumber(), products.getSize(), products.getTotalElements(),
                products.getTotalPages(), products.isLast());
    }

    public PagedResponse getAllProductsModeAndEnable(int feature,int page, int size,boolean active) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findAllByModeAndEnable(feature,pageable,true);

        if(products.getNumberOfElements()==0){
            return new PagedResponse<>(Collections.emptyList(), products.getNumber(), products.getSize(),
                    products.getTotalElements(), products.getTotalPages(), products.isLast());
        }

        List<ProductCardResponse> productCardResponseListPage = products.map(product -> {
            ProductCardResponse productCardResponse = new ProductCardResponse();
            return  productCardResponse.productCardConvert(product);
        }).getContent();

        return new PagedResponse<>(productCardResponseListPage, products.getNumber(), products.getSize(), products.getTotalElements(),
                products.getTotalPages(), products.isLast());
    }
    public PagedResponse getAllProductsByProductModeAndEnable(long mode,int page, int size,boolean active) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findAllByProductModeIdAndEnable(mode,pageable,active);

        if(products.getNumberOfElements()==0){
            return new PagedResponse<>(Collections.emptyList(), products.getNumber(), products.getSize(),
                    products.getTotalElements(), products.getTotalPages(), products.isLast());
        }

        List<ProductCardDto> cardDtoList = ProductCardMapper.listConvert(products.stream().toList());

        return new PagedResponse<>(cardDtoList, products.getNumber(), products.getSize(), products.getTotalElements(),
                products.getTotalPages(), products.isLast());
    }

    public PagedResponse getProductsByProductModeAndEnable(long mode,int page, int size,boolean active) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findAllByProductModeIdAndEnable(mode,pageable,active);

        if(products.getNumberOfElements()==0){
            return new PagedResponse<>(Collections.emptyList(), products.getNumber(), products.getSize(),
                    products.getTotalElements(), products.getTotalPages(), products.isLast());
        }

        List<ProductCardDto> cardDtoList = ProductCardMapper.listConvert(products.stream().toList());

        return new PagedResponse<>(cardDtoList, products.getNumber(), products.getSize(), products.getTotalElements(),
                products.getTotalPages(), products.isLast());
    }

    public PagedResponse getAllProductsForCreated(Long createdId,int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
        Page<Product> products = productRepository.findAllByCreatedBy(createdId,pageable);

        if(products.getNumberOfElements()==0){
            return new PagedResponse<>(Collections.emptyList(), products.getNumber(), products.getSize(),
                    products.getTotalElements(), products.getTotalPages(), products.isLast());
        }



        List<ProductCardResponse> productCardResponseListPage = products.map(product -> {
            ProductCardResponse productCardResponse = new ProductCardResponse();
            return  productCardResponse.productCardConvert(product);
        }).getContent();

        return new PagedResponse<>(productCardResponseListPage, products.getNumber(), products.getSize(), products.getTotalElements(),
                products.getTotalPages(), products.isLast());
    }

    public PagedResponse getAllProductsForShop(Long shopId,int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Product> products = productRepository.findAllByShopId(shopId,pageable);

        if(products.getNumberOfElements()==0){
            return new PagedResponse<>(Collections.emptyList(), products.getNumber(), products.getSize(),
                    products.getTotalElements(), products.getTotalPages(), products.isLast());
        }

        List<ProductCardDto> cardDtoList = ProductCardMapper.listConvert(products.stream().toList());

        return new PagedResponse<>(cardDtoList, products.getNumber(), products.getSize(), products.getTotalElements(),
                products.getTotalPages(), products.isLast());
    }




}
