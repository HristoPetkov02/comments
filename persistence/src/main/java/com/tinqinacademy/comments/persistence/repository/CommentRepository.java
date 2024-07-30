package com.tinqinacademy.comments.persistence.repository;

import com.tinqinacademy.comments.persistence.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface CommentRepository extends JpaRepository<Comment, UUID> {
}
