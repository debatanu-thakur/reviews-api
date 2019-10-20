package com.udacity.course3.reviews;

import com.udacity.course3.reviews.entity.Comment.Comment;
import com.udacity.course3.reviews.entity.Comment.CommentRepository;
import com.udacity.course3.reviews.entity.Product.Product;
import com.udacity.course3.reviews.entity.Product.ProductRepository;
import com.udacity.course3.reviews.entity.Review.Review;
import com.udacity.course3.reviews.entity.Review.ReviewRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(properties = "spring.datasource.url=")
public class ReviewsApplicationTests {
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ReviewRepository reviewRepository;

	@Autowired
	private CommentRepository commentRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void injectedComponentsAreNotNull(){
		assertThat(productRepository).isNotNull();
		assertThat(reviewRepository).isNotNull();
		assertThat(commentRepository).isNotNull();
	}

	@Test
	public void createProduct() throws Exception {
		Product prod = new Product();
		prod.setName("Samsung Galaxy");
		prod.setCreatedAt(LocalDateTime.now());
		prod.setModifiedAt(LocalDateTime.now());
		assertThat(prod.getId()).isEqualTo(0);
		Product newProd = productRepository.save(prod);
		assertThat(newProd.getId()).isGreaterThan(0);
		assertThat(newProd.getName()).isEqualTo("Samsung Galaxy");
	}

	@Test
	public void getProduct() throws Exception {
		Product prodExisting = productRepository.findById(1).orElse(new Product());
		assertThat(prodExisting.getId()).isGreaterThan(0);
		assertThat(prodExisting.getId()).isEqualTo(1);
	}

	@Test
	public void getAllProducts() throws Exception {
		List<Product> prodExisting = productRepository.findAll();
		assertThat(prodExisting.size()).isGreaterThan(0);
	}

	@Test
	public void createReview() throws Exception {
		Product prodExist = productRepository.findById(1).orElse(new Product());
		Review review = new Review();
		review.setProductId(prodExist.getId());
		review.setTitle("Iphone");
		review.setCreatedAt(LocalDateTime.now());
		assertThat(review.getId()).isEqualTo(0);
		Review newReview = reviewRepository.save(review);
		assertThat(newReview.getId()).isGreaterThan(0);
		assertThat(newReview.getTitle()).isEqualTo("Iphone");
	}

	@Test
	public void getReview() throws Exception {
		Product prodExist = productRepository.findById(1).orElse(new Product());
		List<Review> revs = prodExist.getReviews();
		Review existingReview = revs.get(0);
		Review newReview = reviewRepository.findById(existingReview.getId()).orElse(new Review());
		assertThat(newReview.getId()).isGreaterThan(0);
		assertThat(newReview.getId()).isEqualTo(existingReview.getId());
	}

	@Test
	public void createComment() throws Exception {
		Review review = reviewRepository.findById(1).orElse(new Review());
		Comment comment =  new Comment();
		comment.setReviewId(review.getId());
		comment.setCreatedAt(LocalDateTime.now());
		comment.setModifiedAt(LocalDateTime.now());
		comment.setReviewComment("Awesome");
		assertThat(comment.getId()).isEqualTo(0);
		Comment savedComment = commentRepository.save(comment);
		assertThat(savedComment.getId()).isGreaterThan(0);
		assertThat(savedComment.getReviewComment()).isEqualTo("Awesome");
	}

	@Test
	public void getComments() throws Exception {
		Review review = reviewRepository.findById(1).orElse(new Review());
		List<Comment> cmt = review.getComments();
		Comment existingComment = cmt.get(0);
		assertThat(existingComment.getId()).isGreaterThan(0);
		Comment testComment = commentRepository.findById(existingComment.getId()).orElse(new Comment());
		assertThat(testComment.getId()).isGreaterThan(0);
		assertThat(testComment.getId()).isEqualTo(existingComment.getId());
	}

}