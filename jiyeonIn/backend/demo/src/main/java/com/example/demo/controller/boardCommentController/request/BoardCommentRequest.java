package com.example.demo.controller.boardCommentController.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardCommentRequest {
    private String id;
    private String comments;
    private Long bookingNo;

}
