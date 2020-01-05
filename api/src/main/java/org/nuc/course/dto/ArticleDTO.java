package org.nuc.course.dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class ArticleDTO {
    private Long id;
    private String articleFront;
    private String articleTitle;
    private Long createTime;
    private String articleAuthor;
    private String articleContent;
}
