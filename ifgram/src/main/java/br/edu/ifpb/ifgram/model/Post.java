package br.edu.ifpb.ifgram.model;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Post {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private String imagemUrl;

        @Column(length = 300)
        private String legenda;

        private LocalDateTime criadoEm;

        @ManyToOne(optional = false)
        @JoinColumn(name = "user_id")
        private User autor;

        // Construtor vazio exigido pelo JPA
        public Post() {
        }

        // Construtor para criar uma publicação
        public Post(String imagemUrl, String legenda, User autor) {
            this.imagemUrl = imagemUrl;
            this.legenda = legenda;
            this.autor = autor;
            this.criadoEm = LocalDateTime.now();
        }

        public Long getId() {
            return id;
        }

        public String getImagemUrl() {
            return imagemUrl;
        }

        public String getLegenda() {
            return legenda;
        }

        public LocalDateTime getCriadoEm() {
            return criadoEm;
        }

        public User getAutor() {
            return autor;
        }
    }

}
