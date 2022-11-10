package com.codebyte.productservice;

import com.codebyte.productservice.entity.Product;
import com.codebyte.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient
@RequiredArgsConstructor
@EnableSwagger2
public class ProductServiceApplication implements CommandLineRunner {

	private final ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

	@Override
	public void run(String... args)   {
		{
			if (productRepository.count() < 1) {
				Product product1 = new Product();
				product1.setUlid("01GGWM8SNJ94HTBXSK8BFT2VA6");
				product1.setName("User Service");
				productRepository.save(product1);

				Product product2 = new Product();
				product2.setUlid("01GGWM93GG13NCD5BY4JF9159A");
				product2.setName("Product Service");
				productRepository.save(product2);

			}
		}
	}
}
