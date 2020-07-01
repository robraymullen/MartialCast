package com.rob.video.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rob.video.server.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
