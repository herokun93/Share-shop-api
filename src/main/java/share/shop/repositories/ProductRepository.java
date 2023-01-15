package share.shop.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import share.shop.models.Product;
import share.shop.payloads.response.icustom.ITProduct;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {



    Optional<Product> findById(Long id);



//    @Modifying
//    @Query(nativeQuery = true,
//            value = "SELECT " +
//                    "pro.id as productId," +
//                    "pro.name as productName,  " +
//                    "pro.hot as productHot,  " +
//                    "pro.rating as productRating,  " +
//                    "pro.description_sort as productDescriptionSort,  " +
//                    "pro.tiktok as productTiktok,  " +
//                    "pro.enable as productEnable,  " +
//                    "pro.until as productUntil,  " +
//                    "img.url_small as imageUrlSmall,  " +
//                    "img.id as imageId,  " +
//                    "tag.name as tagName,  " +
//                    "tag.id as tagId, " +
//                    "subca.id as subCategoryId, " +
//                    "subca.name as subCategoryName, " +
//                    "cou.id as countryId, " +
//                    "cou.name as countryName, " +
//                    "pri.id as priceId, " +
//                    "pri.name as priceName, " +
//                    "pri.start_at as priceStartAt, " +
//                    "pri.finish_at as priceFinishAt, " +
//                    "pri.price as pricePrice, " +
//                    "pri.sale_price as priceSalePrice, " +
//                    "pri.sale as priceSale " +
//                    "FROM product AS pro, image as img ,product_tags as ptag ,tag as tag, sub_category as subca, country as cou, price as pri  "+
//                    "WHERE " +
//                    "pro.id = img.product_id " +
//                    "AND pro.id = ptag.products_id " +
//                    "AND img.priority = 1 " +
//                    "AND ptag.tags_id = tag.id " +
//                    "AND pro.sub_category_id = subca.id " +
//                    "AND pro.id = pri.product_id " +
//                    "AND ?1 BETWEEN pri.start_at AND pri.finish_at " +
//                    "AND pro.shop_id = ?2 ")
//    List<ITProduct> getProductCardByShopId(Instant now,long shopId ,long size, long page);
//
//    @Modifying
//    @Query(nativeQuery = true,
//            value = "SELECT " +
//                    "pro.id as productId," +
//                    "pro.name as productName,  " +
//                    "pro.hot as productHot,  " +
//                    "pro.rating as productRating,  " +
//                    "pro.description as productDescription,  " +
//                    "pro.description_sort as productDescriptionSort,  " +
//                    "pro.tiktok as productTiktok,  " +
//                    "pro.enable as productEnable,  " +
//                    "pro.until as productUntil,  " +
//                    "img.url_small as imageUrlSmall,  " +
//                    "img.id as imageId,  " +
//                    "tag.name as tagName,  " +
//                    "tag.id as tagId, " +
//                    "subca.id as subCategoryId, " +
//                    "subca.name as subCategoryName, " +
//                    "cou.id as countryId, " +
//                    "cou.name as countryName, " +
//                    "pri.id as priceId, " +
//                    "pri.name as priceName, " +
//                    "pri.start_at as priceStartAt, " +
//                    "pri.finish_at as priceFinishAt, " +
//                    "pri.price as pricePrice, " +
//                    "pri.sale_price as priceSalePrice, " +
//                    "pri.sale as priceSale " +
//                    "FROM product AS pro, image as img ,product_tags as ptag ,tag as tag, sub_category as subca, country as cou, price as pri  "+
//                    "WHERE " +
//                    "pro.id = img.product_id " +
//                    "AND pro.id = ptag.products_id " +
//                    "AND ptag.tags_id = tag.id " +
//                    "AND pro.sub_category_id = subca.id " +
//                    "AND pro.id = pri.product_id " +
//                    "AND ?1 BETWEEN pri.start_at AND pri.finish_at " +
//                    "AND pro.id =?2 ")
//    List<ITProduct> getProductDetailsById(Instant now,long productId);
    Optional<Product>findByShopIdAndId(long shopId,long productId);
    Product save(Product product);
    Product saveAndFlush(Product product);

    Page<Product> findAllBySubCategoryId(long id, Pageable pageable);

    Page<Product> findAllByCountryId(Long id, Pageable pageable);
    Page<Product> findAllByCreatedBy(long id, Pageable pageable);
    Page<Product> findAllByShopId(long id, Pageable pageable);

    Page<Product> findAllByModeAndEnable(int id, Pageable pageable,boolean enable);
    Page<Product> findAllByProductModeIdAndEnable(long id, Pageable pageable,boolean enable);
    List<Product> findByModeLessThanAndEnable(int mode,boolean enable);



}
