package com.example.HotelManagerment.Comment;

import com.example.HotelManagerment.HotelInformation.HotelInformation;
import com.example.HotelManagerment.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CommentEntity {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "hotelId")
    private HotelInformation hotelInformation;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "feedback")
    private String feedback;

    @Column(name = "clean")
    private Double clean;

    @Column(name = "service")
    private Double service;

    @Column(name = "convenient")
    private Double convenient;

    @Column(name = "hotelCondition")
    private Double condition;

    @Column(name = "friendly")
    private Double friendly;

    public CommentEntity(String content, User user, HotelInformation hotelInformation, LocalDate date, String feedback, Double clean, Double service, Double convenient, Double condition, Double friendly) {
        this.content = content;
        this.user = user;
        this.hotelInformation = hotelInformation;
        this.date = date;
        this.feedback = feedback;
        this.clean = clean;
        this.service = service;
        this.convenient = convenient;
        this.condition = condition;
        this.friendly = friendly;
    }
}
