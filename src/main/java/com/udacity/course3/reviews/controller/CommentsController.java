package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.entity.Comment.Comment;
import com.udacity.course3.reviews.entity.Comment.CommentRepository;
import com.udacity.course3.reviews.entity.Review.Review;
import com.udacity.course3.reviews.entity.Review.ReviewMongoDB;
import com.udacity.course3.reviews.entity.Review.ReviewMongoRepository;
import com.udacity.course3.reviews.entity.Review.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import javax.validation.Valid;
import java.util.List;

/**
 * Spring REST controller for working with comment entity.
 */
@RestController
@RequestMapping("/comments")
public class CommentsController {

    // TODO: Wire needed JPA repositories here
    @Autowired
    private CommentRepository commentRepo;

    @Autowired
    private ReviewRepository reviewRepo;

    /**
     * Creates a comment for a review.
     *
     * 1. Add argument for comment entity. Use {@link RequestBody} annotation.
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, save comment.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.POST)
    public ResponseEntity<?> createCommentForReview(@PathVariable("reviewId") Integer reviewId, @Valid @RequestBody Comment comment) {
        Review review;
        ReviewMongoDB rev;
        try {
            review = reviewRepo.findById(reviewId).orElseThrow(() -> new Exception("Review not found for " + reviewId.toString()));
            comment.setReviewId(reviewId);
            commentRepo.save(comment);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new HttpServerErrorException(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(null, HttpStatus.CREATED);
    }

    /**
     * List comments for a review.
     *
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, return list of comments.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.GET)
    public ResponseEntity<?> listCommentsForReview(@PathVariable("reviewId") Integer reviewId) {
        Review review;
        List<Comment> com;
        try {
            review = reviewRepo.findById(reviewId).orElseThrow(() -> new Exception("Review not found for " + reviewId.toString()));
            com = review.getComments();
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
            return new ResponseEntity<List<Comment>>(review.getComments(), HttpStatus.FOUND);
    }
}