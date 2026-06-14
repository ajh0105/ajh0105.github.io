package com.cheongdo.tourism.repository;

import com.cheongdo.tourism.entity.BoardType;
import com.cheongdo.tourism.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByBoardTypeOrderByCreatedAtDesc(BoardType boardType, Pageable pageable);
    Page<Post> findByBoardTypeAndTitleContainingOrderByCreatedAtDesc(
            BoardType boardType, String keyword, Pageable pageable);
}
