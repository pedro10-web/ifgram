package br.edu.ifpb.ifgram.Repository;


import br.edu.ifpb.ifgram.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository
        extends JpaRepository<Comment, Long> {

    List<Comment> findByPostIdOrderByCriadoEmAsc(Long postId);

    long countByPostId(Long postId);

    List<Comment> findByAutorId(Long autorId);
}