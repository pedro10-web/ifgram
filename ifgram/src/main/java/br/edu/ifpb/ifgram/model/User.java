package br.edu.ifpb.ifgram.Repository;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class UserRepository {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 120)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;
}
