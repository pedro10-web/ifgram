package br.edu.ifpb.ifgram.dto;

public record UserResponse(long id, String nome, String email) {
    public static UserResponse from (User USer ) {
        return new UserResponse (user.getId(),user.getNome(),user.getEmail());
    }
}
