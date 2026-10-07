package br.edu.ifpb.ifgram.Service;

import br.edu.ifpb.ifgram.Repository.UserRepository;
import br.edu.ifpb.ifgram.dto.UserRequest;
import br.edu.ifpb.ifgram.dto.UserResponse;

import br.edu.ifpb.ifgram.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }
    @Transactional
    public UserResponse criar(UserRequest request) throws Exception {
        if (repository.existsByEmail(request.email())){
            throw new Exception(request.email());
        }
        User salvo = repository.save(new User(request.nome(), request.email()));
        return UserResponse.from(salvo);
    }
}