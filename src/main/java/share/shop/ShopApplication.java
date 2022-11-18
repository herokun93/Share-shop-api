package share.shop;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.github.javafaker.Faker;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);
	}

	@Bean
	public Hibernate5Module jsonHibernate5Module() {
		return new Hibernate5Module();
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	Faker faker(){
		return new Faker();
	}

}
