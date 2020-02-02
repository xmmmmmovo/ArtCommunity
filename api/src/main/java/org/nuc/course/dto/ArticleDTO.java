package org.nuc.course.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class ArticleDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String articleFront;

    private String articleTitle;

    private Long createTime;

    private Long articleAuthor;

    private String articleContent;

    private String userName;
}
