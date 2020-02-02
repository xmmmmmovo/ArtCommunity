package org.nuc.course.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class CommentDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long commentBy;
    private String userName;
    private String commentContent;
    private Long parentId;
    private Long artId;
    private String artName;
    private Long commentTime;
}
