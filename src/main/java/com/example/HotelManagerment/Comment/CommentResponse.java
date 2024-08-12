package com.example.HotelManagerment.Comment;

import com.example.HotelManagerment.HotelInformation.HotelInformation;
import com.example.HotelManagerment.User.User;
import lombok.*;

import java.time.LocalDate;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class CommentResponse {
    private Long id;
    private String content;
    private User user;
    private HotelInformation hotelInformation;
    private LocalDate date;
    private String feedback;
    private Double clean;
    private Double service;
    private Double convenient;
    private Double condition;
    private Double friendly;
}
