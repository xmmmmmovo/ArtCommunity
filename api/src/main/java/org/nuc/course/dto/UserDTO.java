package org.nuc.course.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String token;
    private String userName;
    private String userBio;

    public UserDTO(Long id, String token, String userName, String userBio) {
        this.id = id;
        this.token = token;
        this.userName = userName;
        this.userBio = userBio;
    }
}
