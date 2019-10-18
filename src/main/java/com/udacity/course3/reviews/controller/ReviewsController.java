package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.entity.Product.Product;
import com.udacity.course3.reviews.entity.Product.ProductRepository;
import com.udacity.course3.reviews.entity.Review.Review;
import com.udacity.course3.reviews.entity.Review.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Spring REST controller for working with review entity.
 */
@RestController
public class ReviewsController {

    // TODO: Wire JPA repositories here
    @Autowired
    private ReviewRepository reviewRepo;

    @Autowired
    private ProductRepository productRepo;


    /**
     * Creates a review for a product.
     * <p>
     * 1. Add argument for review entity. Use {@link RequestBody} annotation.
     * 2. Check for existence of product.
     * 3. If product not found, return NOT_FOUND.
     * 4. If found, save review.
     *
     * @param productId The id of the product.
     * @return The created review or 404 if product id is not found.
     */
    @PostMapping(value = "/reviews/products/{productId}")
    public ResponseEntity<?> createReviewForProduct(@PathVariable("productId") Integer productId) {
        throw new HttpServerErrorException(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * Lists reviews by product.
     *
     * @param productId The id of the product.
     * @return The list of reviews.
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.GET)
    public ResponseEntity<?> listReviewsForProduct(@PathVariable("productId") Integer productId) {
        Product prod;
        List<Review> reviews;
        try {
            prod = productRepo.findById(productId).orElseThrow(() -> new Exception("Product not found " + productId));
            reviews = prod.getReviews();
        } catch (Exception ex) {
            return new ResponseEntity(new ArrayList(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<Review>>(reviews, HttpStatus.FOUND);
    }
}