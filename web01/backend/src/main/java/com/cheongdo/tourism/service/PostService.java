package com.cheongdo.tourism.service;

import com.cheongdo.tourism.dto.PostForm;
import com.cheongdo.tourism.entity.BoardType;
import com.cheongdo.tourism.entity.Member;
import com.cheongdo.tourism.entity.Post;
import com.cheongdo.tourism.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Page<Post> getList(BoardType boardType, int page, String keyword) {
        Pageable pageable = PageRequest.of(page, 10);
        if (keyword != null && !keyword.isBlank()) {
            return postRepository.findByBoardTypeAndTitleContainingOrderByCreatedAtDesc(
                    boardType, keyword, pageable);
        }
        return postRepository.findByBoardTypeOrderByCreatedAtDesc(boardType, pageable);
    }

    @Transactional
    public Post getDetail(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));
        post.increaseViews();
        return post;
    }

    @Transactional
    public Post create(BoardType boardType, PostForm form, Member author) {
        Post post = Post.builder()
                .boardType(boardType)
                .title(form.getTitle())
                .content(form.getContent())
                .author(author)
                .build();
        return postRepository.save(post);
    }

    @Transactional
    public Post update(Long id, PostForm form, Member loginMember) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));
        if (!post.getAuthor().getId().equals(loginMember.getId())
                && loginMember.getRole().name().equals("USER")) {
            throw new IllegalArgumentException("수정 권한이 없습니다.");
        }
        post.setTitle(form.getTitle());
        post.setContent(form.getContent());
        return post;
    }

    @Transactional
    public void delete(Long id, Member loginMember) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));
        if (!post.getAuthor().getId().equals(loginMember.getId())
                && loginMember.getRole().name().equals("USER")) {
            throw new IllegalArgumentException("삭제 권한이 없습니다.");
        }
        postRepository.delete(post);
    }
}
