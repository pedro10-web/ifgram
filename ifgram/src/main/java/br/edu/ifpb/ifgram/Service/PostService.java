package br.edu.ifpb.ifgram.Service;

import br.edu.ifpb.ifgram.Repository.PostRepository;
import br.edu.ifpb.ifgram.Repository.UserRepository;
import br.edu.ifpb.ifgram.dto.PostRequest;
import br.edu.ifpb.ifgram.dto.PostResponse;
import br.edu.ifpb.ifgram.model.Post;
import br.edu.ifpb.ifgram.model.User;
import jakarta.transaction.Transactional;

import java.util.List;

public class PostService {

        private final PostRepository postRepository;
        private final UserRepository userRepository;

        public PostService(PostRepository postRepository,
                           UserRepository userRepository) {
            this.postRepository = postRepository;
            this.userRepository = userRepository;
        }

        @Transactional
        public PostResponse criar(PostRequest request) {

            User autor = userRepository.findById(request.autorId())
                    .orElseThrow(() ->
                            new RuntimeException("Usuário não encontrado"));

            Post post = new Post(
                    request.imagemUrl(),
                    request.legenda(),
                    autor
            );

            Post postSalvo = postRepository.save(post);

            return PostResponse.from(postSalvo);
        }

        public List<PostResponse> listarFeed() {
            return postRepository.listarFeed()
                    .stream()
                    .map(PostResponse::from)
                    .toList();
        }

        public List<PostResponse> listarPorAutor(Long autorId) {
            return postRepository.findByAutorId(autorId)
                    .stream()
                    .map(PostResponse::from)
                    .toList();
        }

        public PostResponse buscarPorId(Long id) {

            Post post = postRepository.findById(id)
                    .orElseThrow(() ->
                            new RuntimeException("Post não encontrado"));

            return PostResponse.from(post);
        }

        @Transactional
        public void deletar(Long id) {

            if (!postRepository.existsById(id)) {
                throw new RuntimeException("Post não encontrado");
            }

            postRepository.deleteById(id);
        }
    }

