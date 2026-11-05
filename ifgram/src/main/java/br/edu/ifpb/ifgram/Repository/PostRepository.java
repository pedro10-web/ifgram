package br.edu.ifpb.ifgram.Repository;

import br.edu.ifpb.ifgram.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {


        List<Post> findByAutorId(Long autorId);


        List<Post> findByLegendaContainingIgnoreCase(String trecho);


        long countByAutorId(Long autorId);


        @Query("SELECT p FROM Post p ORDER BY p.criadoEm DESC")
        List<Post> listarFeed();
    }


