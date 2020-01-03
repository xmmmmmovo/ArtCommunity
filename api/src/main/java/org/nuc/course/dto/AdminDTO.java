package org.nuc.course.dto;

import lombok.Data;

@Data
public class AdminDTO {
    private Long id;
    private String token;
    private String adminName;
    private String avatar;

    public AdminDTO(Long id, String token, String adminName, String avatar) {
        this.id = id;
        this.token = token;
        this.adminName = adminName;
        this.avatar = avatar;
    }
}
