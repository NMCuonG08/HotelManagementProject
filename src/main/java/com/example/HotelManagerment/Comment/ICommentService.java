package com.example.HotelManagerment.Comment;

import com.example.HotelManagerment.HotelInformation.HotelInformation;

import java.util.List;
import java.util.Optional;

public interface ICommentService {
    CommentEntity addNewComment(CommentEntity comment);

    CommentResponse convertToResponse(CommentEntity theComment);

    List<CommentEntity> getAllCommentByHotel(HotelInformation hotelInformation);
}
