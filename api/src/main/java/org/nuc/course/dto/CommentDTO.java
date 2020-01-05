package org.nuc.course.dto;

import javax.persistence.Column;
import javax.persistence.Id;

public class CommentDTO {
    @Id
    private Long id;
    private Long commentBy;
    private String userName;
    private String commentContent;
    private Long parentId;
    private String artName;
    private Long commentTime;
    private Long commentLikeCount;
}
