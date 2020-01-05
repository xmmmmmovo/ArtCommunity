package org.nuc.course.dto;

import lombok.Data;

import javax.persistence.Id;

@Data
public class ArtDTO {
    @Id
    private Long id;
    private String artName;
    private Long createTime;
    private Long modifiedTime;
    private Long artAuthor;
    private String userName;
    private Long artSize;
    private String length;
    private String height;
    private Long artTag;
    private String tagName;
    private Long artLikeNum;
    private Long artCommentNum;
    private String artContent;
    private String artPicUrl;
}
