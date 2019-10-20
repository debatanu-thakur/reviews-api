package com.udacity.course3.reviews;

import com.udacity.course3.reviews.entity.Product.ProductRepository;
import com.udacity.course3.reviews.entity.Review.ReviewMongoDB;
import com.udacity.course3.reviews.entity.Review.ReviewMongoRepository;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ReviewsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReviewsApplication.class, args);
	}

	@Autowired
	ProductRepository productRepository;
	@Autowired
	ReviewMongoRepository reviewMongoRepository;

	@Bean
	CommandLineRunner initDatabase(
			@Value("${spring.datasource.url}") String url,
			@Value("${spring.datasource.username}") String username,
			@Value("${spring.datasource.password}") String password
	) {
		return args -> {
			if(!url.equals(null) && !url.equals("")) {
				Flyway flyway = Flyway.configure().dataSource(url, username, password).load();
				flyway.migrate();
				try {
					productRepository.findAll().forEach(prod -> {
						ReviewMongoDB rev = new ReviewMongoDB();
						prod.getReviews().forEach(r -> {
							rev.setComments(r.getComments());
							rev.setCreatedAt(r.getCreatedAt());
							rev.setProductId(prod.getId());
							rev.setTitle(r.getTitle());
							reviewMongoRepository.save(rev);
						});
					});
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		};
	}

}