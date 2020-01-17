package com.webcourse.curso.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.webcourse.curso.entities.Category;
import com.webcourse.curso.entities.Order;
import com.webcourse.curso.entities.OrderItem;
import com.webcourse.curso.entities.Payment;
import com.webcourse.curso.entities.Product;
import com.webcourse.curso.entities.User;
import com.webcourse.curso.entities.enums.OrderStatus;
import com.webcourse.curso.repositories.CategoryRepositories;
import com.webcourse.curso.repositories.OrderItemRepositories;
import com.webcourse.curso.repositories.OrderRepositories;
import com.webcourse.curso.repositories.ProductRepositories;
import com.webcourse.curso.repositories.UserRepositories;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	
	@Autowired
	private UserRepositories userRepositories;
	
	@Autowired
	private OrderRepositories orderRepositories;
	
	@Autowired
	private CategoryRepositories categoryRepositories;
	
	@Autowired
	private ProductRepositories productRepositories;
	
	@Autowired
	private OrderItemRepositories orderItemRepositories;

	@Override
	public void run(String... args) throws Exception {
		
		
		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers");

		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.DELIVERED, u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"),  OrderStatus.SHIPPED, u1);
		
		Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
		Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
		Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
		Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");
		
		OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
		OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
		OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
		OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice());
		
		productRepositories.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
		categoryRepositories.saveAll(Arrays.asList(cat1, cat2, cat3));
		userRepositories.saveAll(Arrays.asList(u1,u2)); // salva no banco de dados uma lista
		orderRepositories.saveAll(Arrays.asList(o1, o2, o3));
		
		
		p1.getCategories().add(cat2);
		p2.getCategories().add(cat1);
		p2.getCategories().add(cat3);
		p3.getCategories().add(cat3);
		p4.getCategories().add(cat3);
		p5.getCategories().add(cat2);
		
		
		productRepositories.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
		
		
		
		orderItemRepositories.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));
		
		//instancia um obj Paymente passa como parametro Id = null, Data(moment), e o obj O1 que é a o pedido que veio a ser pago
		Payment pay1 = new Payment(null, Instant.parse("2019-06-20T21:53:07Z"), o1); 
		//Como a classe payment é um objeto que só existe após o pedido usa-se o repository do pedido para salvar no banco de dados;
		o1.setPayment(pay1);
		orderRepositories.save(o1);
		
		
	}

}
