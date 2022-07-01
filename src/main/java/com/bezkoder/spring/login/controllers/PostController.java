package com.bezkoder.spring.login.controllers;

import com.bezkoder.spring.login.models.Paragrafe;
import com.bezkoder.spring.login.models.Post;
import com.bezkoder.spring.login.models.User;
import com.bezkoder.spring.login.payload.request.GetByIdRequest;
import com.bezkoder.spring.login.payload.request.PostRequest;
import com.bezkoder.spring.login.repository.PostRepository;
import com.bezkoder.spring.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin(origins = "http://localhost:3000/",
        maxAge = 3600,
        allowCredentials = "true",
        allowedHeaders = "*")
@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/getAllPosts")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }


    @GetMapping("/getPostedPosts")
    public Stream<Post> getPostedPosts() {
        return postRepository.findAll().stream().filter(Post::isPostat);
    }

    @GetMapping("/getUnpostedPosts")
    @PreAuthorize("hasRole('ADMIN')")
    public Stream<Post> getUnpostedPosts() {
        return postRepository.findAll().stream().filter(post -> !post.isPostat());
    }

    @PostMapping("/makePublic")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Boolean> makePublic(@RequestBody GetByIdRequest getByIdRequest) {
        Optional<Post> optionalPost = postRepository.findById(getByIdRequest.getId());
        Post post = optionalPost.get();
        if (!post.isPostat()) {
            post.setPostat(true);
        }

        postRepository.save(post);

        return ResponseEntity.ok(true);
    }

    @PostMapping("/makePrivate")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Boolean> makePrivate(@RequestBody GetByIdRequest getByIdRequest) {
        Optional<Post> optionalPost = postRepository.findById(getByIdRequest.getId());
        Post post = optionalPost.get();
        if (post.isPostat()) {
            post.setPostat(false);
        }

        postRepository.save(post);

        return ResponseEntity.ok(true);
    }


    @GetMapping("/getOnePost")
    @PreAuthorize("hasRole('ADMIN')")
    public Post getOnePost(@RequestBody GetByIdRequest getByIdRequest) {
        Optional<Post> optionalPost = postRepository.findById(getByIdRequest.getId());
        return optionalPost.get();
    }


    @PostMapping("/add")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity addPost(@RequestBody PostRequest postRequest) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            UserDetails resp = (UserDetails) principal;
//            return user;
            Optional<User> user = userRepository.findByUsername(resp.getUsername());

            System.out.println(user.get().getId());

            List<Paragrafe> sortedList = postRequest.getParagrafe().stream()
                    .sorted(Comparator.comparing(Paragrafe::getOk))
                    .collect(Collectors.toList());

            boolean isAdmin = resp.getAuthorities().stream()
                    .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));

            Post post = new Post(postRequest.getTitle(), postRequest.getCover(), sortedList, user.get().getId());
            post.setPostat(isAdmin);
            postRepository.save(post);
        }
        return ResponseEntity.ok().body("e oke");
    }

    @PostMapping("/delete")
    public ResponseEntity deletePost(@RequestBody GetByIdRequest getByIdRequest) {
        postRepository.deleteById(getByIdRequest.getId());
        return ResponseEntity.ok("s-a dus pe pule");
    }

    @GetMapping("/getOnePublicPost")
    public Post getOnePublicPost(@RequestBody GetByIdRequest getByIdRequest) {
        Optional<Post> optionalPost = postRepository.findById(getByIdRequest.getId());
        Post post = optionalPost.get();
        if (post.isPostat()) {
            return post;
        } else {
            return null;
        }
    }

}
