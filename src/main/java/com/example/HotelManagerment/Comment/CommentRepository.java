package com.example.HotelManagerment.Comment;

import com.example.HotelManagerment.HotelInformation.HotelInformation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {


    List<CommentEntity> findByHotelInformation(HotelInformation hotelInformation);
}
