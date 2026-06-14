package com.cheongdo.tourism.controller;

import com.cheongdo.tourism.dto.PostForm;
import com.cheongdo.tourism.entity.BoardType;
import com.cheongdo.tourism.entity.Member;
import com.cheongdo.tourism.entity.Post;
import com.cheongdo.tourism.service.PostService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/community")
@RequiredArgsConstructor
public class CommunityController {

    private final PostService postService;

    // ── 공지사항 (읽기 전용) ─────────────────
    @GetMapping("/notice")
    public String noticeList(@RequestParam(defaultValue = "0") int page,
                             @RequestParam(required = false) String keyword,
                             Model model) {
        Page<Post> posts = postService.getList(BoardType.NOTICE, page, keyword);
        model.addAttribute("posts", posts);
        model.addAttribute("keyword", keyword);
        model.addAttribute("boardType", "notice");
        return "community/sub61";
    }

    @GetMapping("/notice/{id}")
    public String noticeDetail(@PathVariable Long id, Model model) {
        model.addAttribute("post", postService.getDetail(id));
        model.addAttribute("boardType", "notice");
        return "community/detail";
    }

    // ── 자유게시판 ─────────────────────────
    @GetMapping("/board")
    public String boardList(@RequestParam(defaultValue = "0") int page,
                            @RequestParam(required = false) String keyword,
                            Model model) {
        Page<Post> posts = postService.getList(BoardType.BOARD, page, keyword);
        model.addAttribute("posts", posts);
        model.addAttribute("keyword", keyword);
        model.addAttribute("boardType", "board");
        return "community/sub62";
    }

    @GetMapping("/board/{id}")
    public String boardDetail(@PathVariable Long id, Model model) {
        model.addAttribute("post", postService.getDetail(id));
        model.addAttribute("boardType", "board");
        return "community/detail";
    }

    @GetMapping("/board/write")
    public String boardWriteForm(Model model) {
        model.addAttribute("postForm", new PostForm());
        model.addAttribute("boardType", "board");
        return "community/write";
    }

    @PostMapping("/board/write")
    public String boardWrite(@Valid @ModelAttribute PostForm postForm,
                             BindingResult result,
                             HttpSession session, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("boardType", "board");
            return "community/write";
        }
        Member loginMember = (Member) session.getAttribute("loginMember");
        postService.create(BoardType.BOARD, postForm, loginMember);
        return "redirect:/community/board";
    }

    @GetMapping("/board/edit/{id}")
    public String boardEditForm(@PathVariable Long id, Model model) {
        Post post = postService.getDetail(id);
        PostForm form = new PostForm();
        form.setTitle(post.getTitle());
        form.setContent(post.getContent());
        model.addAttribute("postForm", form);
        model.addAttribute("postId", id);
        model.addAttribute("boardType", "board");
        return "community/edit";
    }

    @PostMapping("/board/edit/{id}")
    public String boardEdit(@PathVariable Long id,
                            @Valid @ModelAttribute PostForm postForm,
                            BindingResult result,
                            HttpSession session, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("boardType", "board");
            model.addAttribute("postId", id);
            return "community/edit";
        }
        Member loginMember = (Member) session.getAttribute("loginMember");
        postService.update(id, postForm, loginMember);
        return "redirect:/community/board/" + id;
    }

    @GetMapping("/board/delete/{id}")
    public String boardDelete(@PathVariable Long id, HttpSession session) {
        Member loginMember = (Member) session.getAttribute("loginMember");
        postService.delete(id, loginMember);
        return "redirect:/community/board";
    }

    // ── 여행후기 ───────────────────────────
    @GetMapping("/review")
    public String reviewList(@RequestParam(defaultValue = "0") int page, Model model) {
        Page<Post> posts = postService.getList(BoardType.REVIEW, page, null);
        model.addAttribute("posts", posts);
        model.addAttribute("boardType", "review");
        return "community/sub63";
    }

    @GetMapping("/review/{id}")
    public String reviewDetail(@PathVariable Long id, Model model) {
        model.addAttribute("post", postService.getDetail(id));
        model.addAttribute("boardType", "review");
        return "community/detail";
    }

    @GetMapping("/review/write")
    public String reviewWriteForm(Model model) {
        model.addAttribute("postForm", new PostForm());
        model.addAttribute("boardType", "review");
        return "community/write";
    }

    @PostMapping("/review/write")
    public String reviewWrite(@Valid @ModelAttribute PostForm postForm,
                              BindingResult result,
                              HttpSession session, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("boardType", "review");
            return "community/write";
        }
        Member loginMember = (Member) session.getAttribute("loginMember");
        postService.create(BoardType.REVIEW, postForm, loginMember);
        return "redirect:/community/review";
    }

    @GetMapping("/review/delete/{id}")
    public String reviewDelete(@PathVariable Long id, HttpSession session) {
        Member loginMember = (Member) session.getAttribute("loginMember");
        postService.delete(id, loginMember);
        return "redirect:/community/review";
    }

    // ── Q&A ───────────────────────────────
    @GetMapping("/qna")
    public String qnaList(@RequestParam(defaultValue = "0") int page, Model model) {
        Page<Post> posts = postService.getList(BoardType.QNA, page, null);
        model.addAttribute("posts", posts);
        model.addAttribute("boardType", "qna");
        return "community/sub64";
    }

    @GetMapping("/qna/{id}")
    public String qnaDetail(@PathVariable Long id, Model model) {
        model.addAttribute("post", postService.getDetail(id));
        model.addAttribute("boardType", "qna");
        return "community/detail";
    }

    @GetMapping("/qna/write")
    public String qnaWriteForm(Model model) {
        model.addAttribute("postForm", new PostForm());
        model.addAttribute("boardType", "qna");
        return "community/write";
    }

    @PostMapping("/qna/write")
    public String qnaWrite(@Valid @ModelAttribute PostForm postForm,
                           BindingResult result,
                           HttpSession session, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("boardType", "qna");
            return "community/write";
        }
        Member loginMember = (Member) session.getAttribute("loginMember");
        postService.create(BoardType.QNA, postForm, loginMember);
        return "redirect:/community/qna";
    }

    @GetMapping("/qna/delete/{id}")
    public String qnaDelete(@PathVariable Long id, HttpSession session) {
        Member loginMember = (Member) session.getAttribute("loginMember");
        postService.delete(id, loginMember);
        return "redirect:/community/qna";
    }
}
