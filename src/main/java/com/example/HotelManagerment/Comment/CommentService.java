package com.example.HotelManagerment.Comment;

import com.example.HotelManagerment.HotelInformation.HotelInformation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService implements ICommentService {

    private final CommentRepository commentRepository;

    @Override
    public CommentEntity addNewComment(CommentEntity comment) {
        return commentRepository.save(comment);
    }

    @Override
    public CommentResponse convertToResponse(CommentEntity theComment) {
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setId(theComment.getId());
        commentResponse.setUser(theComment.getUser());
        commentResponse.setHotelInformation(theComment.getHotelInformation());
        commentResponse.setDate(theComment.getDate());
        commentResponse.setService(theComment.getService());
        commentResponse.setClean(theComment.getClean());
        commentResponse.setCondition(theComment.getCondition());
        commentResponse.setContent(theComment.getContent());
        commentResponse.setFriendly(theComment.getFriendly());
        commentResponse.setFeedback(theComment.getFeedback());
        commentResponse.setConvenient(theComment.getConvenient());
        return commentResponse;
    }

    @Override
    public List<CommentEntity> getAllCommentByHotel(HotelInformation hotelInformation) {
        return commentRepository.findByHotelInformation(hotelInformation);
    }
}
