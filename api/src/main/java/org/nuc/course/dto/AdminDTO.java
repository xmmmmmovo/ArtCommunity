package org.nuc.course.dto;

import lombok.Data;

@Data
public class AdminDTO {
    private Long id;
    private String token;
    private String adminName;
    private String avatar;
    private String role;

    public AdminDTO(Long id, String token, String adminName, String avatar, String role) {
        this.id = id;
        this.token = token;
        this.adminName = adminName;
        this.avatar = avatar;
        this.role = role;
    }
}
