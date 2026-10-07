package br.edu.ifpb.ifgram.dto;

import br.edu.ifpb.ifgram.model.User;

public record UserResponse(long id, String nome, String email) {
    public static UserResponse from (User user) {
        return new UserResponse (user.getId(),user.getNome(),user.getEmail());
    }
}
