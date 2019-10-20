package com.udacity.course3.reviews.changelog;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import com.udacity.course3.reviews.entity.Product.ProductRepository;
import com.udacity.course3.reviews.entity.Review.ReviewMongoDB;
import com.udacity.course3.reviews.entity.Review.ReviewMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages="com.udacity.course3.reviews.entity.Review")
@EnableJpaRepositories(basePackages="com.udacity.course3.reviews.entity.Product")
@ChangeLog(order = "001")
public class ChangeLogs {
    @Qualifier("productRepo")
    ProductRepository productRepository;

    @Qualifier("reviewMongoRepo")
    ReviewMongoRepository reviewMongoRepository;

    @ChangeSet(order = "001", id = "001", author = "root")
    public void createCollection(MongoDatabase database) {
        database.createCollection("review");
    }
    @ChangeSet(order = "002", id = "002", author = "root")
    public void initMongoCollection() {
        System.out.println("I am the change!");
        try {
            productRepository.findAll().forEach(prod -> {
                System.out.println("Inside products");
                prod.getReviews().forEach(r -> {
                    System.out.println("reviews " + r.getId());
                    ReviewMongoDB rev = new ReviewMongoDB();
                    rev.setComments(r.getComments());
                    rev.setCreatedAt(r.getCreatedAt());
                    rev.setProductId(prod.getId());
                    rev.setTitle(r.getTitle());
                    reviewMongoRepository.save(rev);
                });
            });
        } catch (Exception ex) {
            System.out.println("Error");
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}
