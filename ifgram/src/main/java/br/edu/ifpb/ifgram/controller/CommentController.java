package br.edu.ifpb.ifgram.controller;


import br.edu.ifpb.ifgram.Service.CommentService;
import br.edu.ifpb.ifgram.dto.CommentRequest;
import br.edu.ifpb.ifgram.dto.CommentResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping("comment")
    public class CommentController {

        private final CommentService service;

        public CommentController(CommentService service) {
            this.service = service;
        }

        @PostMapping
        public ResponseEntity<CommentResponse> criar(
                @Valid @RequestBody CommentRequest request) {

            CommentResponse response = service.criar(request);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(response);
        }

        @GetMapping("/post/{postId}")
        public ResponseEntity<List<CommentResponse>> listarPorPost(
                @PathVariable Long postId) {

            return ResponseEntity.ok(service.listarPorPost(postId));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deletar(
                @PathVariable Long id) {

            service.deletar(id);

            return ResponseEntity.noContent().build();
        }
    }

