package br.edu.ifpb.ifgram.controller;

import br.edu.ifpb.ifgram.Service.PostService;
import br.edu.ifpb.ifgram.dto.PostRequest;
import br.edu.ifpb.ifgram.dto.PostResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping("post")
    public class PostController {

        private final PostService service;

        public PostController(PostService service) {
            this.service = service;
        }

        @GetMapping
        public ResponseEntity<List<PostResponse>> listarFeed() {
            return ResponseEntity.ok(service.listarFeed());
        }

        @GetMapping("/{id}")
        public ResponseEntity<PostResponse> buscarPorId(
                @PathVariable Long id) {

            return ResponseEntity.ok(service.buscarPorId(id));
        }

        @GetMapping("/autor/{autorId}")
        public ResponseEntity<List<PostResponse>> listarPorAutor(
                @PathVariable Long autorId) {

            return ResponseEntity.ok(service.listarPorAutor(autorId));
        }

        @PostMapping
        public ResponseEntity<PostResponse> criar(
                @Valid @RequestBody PostRequest request) {

            PostResponse response = service.criar(request);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(response);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deletar(
                @PathVariable Long id) {

            service.deletar(id);

            return ResponseEntity.noContent().build();
        }
    }


