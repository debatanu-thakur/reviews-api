package com.udacity.course3.reviews.entity.Comment;

import javax.persistence.Entity;

@Entity
public class Comment {
    private int id;
    private int reviewId;
    private String comment;
}
