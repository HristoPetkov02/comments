package com.tinqinacademy.comments.persistence.repository;

import com.tinqinacademy.comments.persistence.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface CommentRepository extends JpaRepository<Comment, UUID> {
    Optional<List<Comment>> findCommentsByRoomId(UUID roomId);
}
