package com.udacity.course3.reviews.entity.Review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;

public interface ReviewRepository extends JpaRepository<ResourceBundleViewResolver, Integer> {
}
