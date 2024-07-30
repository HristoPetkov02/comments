package com.tinqinacademy.comments.persistence.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String content;

    @CreationTimestamp
    @Column(name = "published_on", updatable = false)
    private LocalDateTime publishedOn;

    @UpdateTimestamp
    @Column(name = "last_edited_on")
    private LocalDateTime lastEditedOn;

    @Column(name = "room_id",nullable = false)
    private UUID roomId;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "last_edited_by", nullable = false)
    private UUID lastEditedBy;
}