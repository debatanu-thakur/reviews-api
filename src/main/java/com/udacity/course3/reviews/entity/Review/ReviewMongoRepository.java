package com.udacity.course3.reviews.entity.Review;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReviewMongoRepository extends MongoRepository<ReviewMongoDB, String> {
    public List<ReviewMongoDB> findByProductId(int Id);
}
