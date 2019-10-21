package com.udacity.course3.reviews.entity.Review;

import com.udacity.course3.reviews.entity.Comment.Comment;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(value = "review")
public class ReviewMongoDB {

    @Id
    private String Id;

    private LocalDateTime createdAt;

    private List<Comment> comments;

    private int productId;

    private String title;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public ReviewMongoDB() {

    }

    public  ReviewMongoDB(Review r) {
        title = r.getTitle();
        createdAt = r.getCreatedAt();
        comments = r.getComments();

    }
}
