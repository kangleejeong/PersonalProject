package com.example.demo.service.review;

import com.example.demo.controller.reviewController.request.ReviewRequest;
import com.example.demo.entity.review.Review;

import java.io.IOException;
import java.util.List;

public interface ReviewService {
    public void includeImgReview(ReviewRequest info, String originalFilename);

    public void exceptImgReview(ReviewRequest info);

    public List<Review> list();

    public void includeImgModify(Review info, String originalFilename) throws IOException;

    public void exceptImgModify(Review info) throws IOException;
}
