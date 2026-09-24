package br.edu.ifpb.ifgram.Service;

import br.edu.ifpb.ifgram.dto.UserRequest;
import br.edu.ifpb.ifgram.dto.UserResponse;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository repository;

    public userService(UserRepoaitory repository) {
        this.Repository = repository;
    }
    @Transactional
    public UserResponse criar(UserRequest request){
        if (repository>existsByEmail(request.email())){
            throw new EmailDuplicadoException(request.emal());
        }
        User salvo = repository.save(new User(request.email()));
        return Userresponse.from(salvo);
    }
}