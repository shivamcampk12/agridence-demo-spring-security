package com.agridence.microservice.Assignment.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.GenerationType;


@ToString(of = {"Id", "title", "description", "user"})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notes")
public class Note {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Integer Id;
    private String title;
    private String description;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;
}
