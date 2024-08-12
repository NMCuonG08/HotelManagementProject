package com.example.HotelManagerment.Comment;

import com.example.HotelManagerment.HotelInformation.HotelInformation;
import com.example.HotelManagerment.HotelInformation.IHotelService;
import com.example.HotelManagerment.RoomInformation.IRoomService;
import com.example.HotelManagerment.User.IUserService;
import com.example.HotelManagerment.User.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.stream.events.Comment;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:5173")
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final ICommentService commentService;
    private final IUserService userService;
    private final IHotelService hotelService;

    @PostMapping("/add-comment")
    public ResponseEntity<CommentResponse> addComment(
            @RequestParam String content,
            @RequestParam Long userId,
            @RequestParam Long hotelId,
            @RequestParam LocalDate date,
            @RequestParam Double clean,
            @RequestParam Double service,
            @RequestParam Double convenient,
            @RequestParam Double condition,
            @RequestParam Double friendly
            ){
        User user = userService.getUserById(userId);
        Optional<HotelInformation> hotelInformation = hotelService.getHotelByID(hotelId);
        CommentResponse commentResponse = null;
        if(user != null && hotelInformation.isPresent()){
            CommentEntity comment = new CommentEntity(content,user,hotelInformation.get(),date,"",clean,service,convenient,condition,friendly);
            CommentEntity theComment =  commentService.addNewComment(comment);
            commentResponse = commentService.convertToResponse(theComment);
        }
        return ResponseEntity.ok(commentResponse);
    }
    @GetMapping("/all-comment")
    public ResponseEntity<List<CommentResponse>> getAllCommentByHotelId(@RequestParam Long hotelId){
        Optional<HotelInformation> hotelInformation = hotelService.getHotelByID(hotelId);
        List<CommentResponse> commentResponse = new ArrayList<>();
        if (hotelInformation.isPresent()){
            List<CommentEntity> comment = commentService.getAllCommentByHotel(hotelInformation.get());
            for (var com : comment) {
                commentResponse.add(commentService.convertToResponse(com));
            }
        }
        return ResponseEntity.ok(commentResponse);
    }



}
