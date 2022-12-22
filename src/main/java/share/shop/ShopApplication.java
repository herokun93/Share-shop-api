package share.shop;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.github.javafaker.Faker;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;



@SpringBootApplication()
public class ShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);

//		try {
//			TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
//			botsApi.registerBot(new MyBotTelegram());
//		} catch (TelegramApiException e) {
//			e.printStackTrace();
//		}
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


	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
