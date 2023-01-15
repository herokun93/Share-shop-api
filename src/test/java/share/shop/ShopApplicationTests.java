package share.shop;




import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import share.shop.repositories.ProductRepository;


@SpringBootTest
@ContextConfiguration(locations = "/test-context.xml")
@DataJpaTest
class ShopApplicationTests {

	@Autowired
	ProductRepository productRepository;

	@Test
	void Test(){

		long id = 1;
		productRepository.findById(id);
		System.out.println("thang");
	}

}
