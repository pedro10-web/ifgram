package br.edu.ifpb.ifgram.Service;


import br.edu.ifpb.ifgram.Repository.CommentRepository;
import br.edu.ifpb.ifgram.Repository.PostRepository;
import br.edu.ifpb.ifgram.Repository.UserRepository;
import br.edu.ifpb.ifgram.dto.CommentRequest;
import br.edu.ifpb.ifgram.dto.CommentResponse;
import br.edu.ifpb.ifgram.model.Comment;
import br.edu.ifpb.ifgram.model.Post;
import br.edu.ifpb.ifgram.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public CommentService(
            CommentRepository commentRepository,
            PostRepository postRepository,
            UserRepository userRepository) {

        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public CommentResponse criar(CommentRequest request) {

        Post post = postRepository.findById(request.postId())
                .orElseThrow(() ->
                        new RuntimeException("Post não encontrado"));

        User autor = userRepository.findById(request.autorId())
                .orElseThrow(() ->
                        new RuntimeException("Usuário não encontrado"));

        Comment comment = new Comment(
                request.texto(),
                post,
                autor
        );

        Comment commentSalvo = commentRepository.save(comment);

        return CommentResponse.from(commentSalvo);
    }

    public List<CommentResponse> listarPorPost(Long postId) {

        if (!postRepository.existsById(postId)) {
            throw new RuntimeException("Post não encontrado");
        }

        return commentRepository
                .findByPostIdOrderByCriadoEmAsc(postId)
                .stream()
                .map(CommentResponse::from)
                .toList();
    }

    @Transactional
    public void deletar(Long id) {

        if (!commentRepository.existsById(id)) {
            throw new RuntimeException("Comentário não encontrado");
        }

        commentRepository.deleteById(id);
    }
}
