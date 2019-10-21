package com.udacity.course3.reviews;

import com.udacity.course3.reviews.entity.Comment.Comment;
import com.udacity.course3.reviews.entity.Review.ReviewMongoDB;
import com.udacity.course3.reviews.entity.Review.ReviewMongoRepository;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataMongoTest
@TestPropertySource(properties = {
        "spring.datasource.url=",
        "spring.data.mongodb.port=37017",
        "spring.data.mongodb.uri=mongodb://localhost:37017/"
})
@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class ReviewsMongoAppTest {
    @Autowired ReviewMongoRepository reviewMongoRepository;

    @Test
    public void initCheck() {

        assertThat(reviewMongoRepository).isNotNull();
    }

    @Test
    public void createReviews() {
        ReviewMongoDB rev = createTestReview();
        assertThat(rev.getId()).isNull();
        reviewMongoRepository.save(rev);
        assertThat(rev.getId()).isNotNull();
    }
    @Test
    public void getReviews() {
        ReviewMongoDB review = createTestReview();
        reviewMongoRepository.save(review);
        List<ReviewMongoDB> rev = reviewMongoRepository.findByProductId(10);
        assertThat(rev.size()).isGreaterThan(0);
        assertThat(rev.get(0).getComments().size()).isEqualTo(1);
    }

    private ReviewMongoDB createTestReview() {
        ReviewMongoDB rev = new ReviewMongoDB();
        rev.setProductId(10);
        rev.setTitle("Imax");
        Comment com = new Comment();
        com.setReviewComment("Some text");
        List<Comment> coms = new ArrayList();
        coms.add(com);
        rev.setComments(coms);
        return rev;
    }
}
