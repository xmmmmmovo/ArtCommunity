package org.nuc.course.dto;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class LikeDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String artPicUrl;
    private String artName;
    private String tagName;
    private Long artId;
    private Long likeTime;
}
