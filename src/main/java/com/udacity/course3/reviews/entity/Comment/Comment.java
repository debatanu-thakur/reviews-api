package com.udacity.course3.reviews.entity.Comment;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_comment")
public class Comment {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @CreatedDate
    @Column(name = "CREATED_AT", insertable=false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "UPDATED_AT", insertable=false)
    private LocalDateTime modifiedAt;

    @Column(name = "REVIEW_COMMENT")
    private String reviewComment;

    @Column(name = "REVIEW_ID")
    private int reviewId;

    public String getReviewComment() {
        return reviewComment;
    }

    public void setReviewComment(String comment) {
        this.reviewComment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }
}
