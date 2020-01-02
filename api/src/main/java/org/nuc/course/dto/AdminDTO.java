package org.nuc.course.dto;

import lombok.Data;

@Data
public class AdminDTO {
    private Long id;
    private String token;
    private String userName;
    private String avatar;

    public AdminDTO(Long id, String token, String userName, String avatar) {
        this.id = id;
        this.token = token;
        this.userName = userName;
        this.avatar = avatar;
    }
}
