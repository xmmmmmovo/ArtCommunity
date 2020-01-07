package org.nuc.course.dto;

import lombok.Data;

@Data
public class LikeDTO {
    private Long id;
    private String artPicUrl;
    private String artName;
    private String tagName;
    private Long artId;
    private Long likeTime;
}
