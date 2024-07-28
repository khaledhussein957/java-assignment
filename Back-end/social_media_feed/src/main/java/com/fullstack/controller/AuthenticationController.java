package com.fullstack.controller;

import com.fullstack.Repository.BookRepository;
import com.fullstack.Repository.UserRepository;
import com.fullstack.model.*;
import com.fullstack.service.AuthenticationService;
import com.fullstack.service.JwtService;
import com.fullstack.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {
    @Autowired
    private final AuthenticationService authservice;
    @Autowired
    private UserServiceImp userServiceImp;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository postRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

//    private LikeRepository likeRepository;
    @Autowired
    private JwtService jwtService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authservice = authenticationService;
    }

    // register both admin and user
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody User request){
        return ResponseEntity.ok(authservice.register(request));
    }

    // login both user and admin
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody User request){
        return ResponseEntity.ok(authservice.login(request));
    }

    // admin only can register book
//    @PostMapping("/posts/create-post")
//    public ResponseEntity createPost(@RequestHeader("Authorization") String token, @RequestBody Post post) {
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        post.setUser(user);  // Assuming User object in UserDetails implementation
//        postRepository.save(post);
//        return ResponseEntity.ok("Post created successfully");
//    }

// user can update his info
//    @PutMapping("/{id}")
//    @Secured("USER")
//    public ResponseEntity updateUser(@RequestHeader("Authorization") String token , @PathVariable Long id, @RequestBody User user) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null && authentication.isAuthenticated()) {
//            User userDetails = (User) authentication.getPrincipal(); // Assuming User class implements UserDetails
//            user.setId(id);
//            user.setFirstName(user.getFirstName());
//            user.setLastName(user.getLastName());
//            user.setUsername(user.getUsername());
//            user.setPassword(passwordEncoder.encode(user.getPassword()));
//            user.setRole(user.getRole());
//            // Save updated user information
//            userRepository.save(user);
//            return ResponseEntity.ok(user);
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//    }

// admin updating the book
//    @PutMapping("/{postId}/comments/{commentId}")
//    public ResponseEntity updateComment(@RequestHeader("Authorization") String token, @PathVariable Long postId, @PathVariable Long commentId, @RequestBody Comment updatedComment) {
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Post post = postRepository.findById(postId).orElseThrow();
//        Comment comment = commentRepository.findById(commentId).orElseThrow();
//        if (!comment.getUser().getId().equals(user.getId())) {
//             return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not authorized to update this comment");
//        }
//        comment.setContent(updatedComment.getContent());
//        commentRepository.save(comment);
//        return ResponseEntity.ok(comment);
//    }

// admin deleting books
//    @DeleteMapping("/posts/delete-post/{postId}")
//    public ResponseEntity deletePost(@RequestHeader("Authorization") String token, @PathVariable Long postId) {
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Post post = postRepository.findById(postId).orElseThrow();
//        if (!post.getUser().getId().equals(user.getId())) {
//          return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not authorized to delete this post");
//        }
//        postRepository.delete(post);
//        return ResponseEntity.ok("Post deleted successfully");
//    }

// user deleting his registered books
//    @DeleteMapping("/posts/{postId}/comments/{commentId}")
//    public ResponseEntity deleteComment(@RequestHeader("Authorization") String token, @PathVariable Long postId, @PathVariable Long commentId) {
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Post post = postRepository.findById(postId).orElseThrow();
//        Comment comment = commentRepository.findById(commentId).orElseThrow();
//        if (!comment.getUser().getId().equals(user.getId())) {
//             return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not authorized to delete this comment");
//        }
//        commentRepository.delete(comment);
//        return ResponseEntity.ok("Comment deleted successfully");
//    }


}
