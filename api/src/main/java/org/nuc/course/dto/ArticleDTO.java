package org.nuc.course.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
public class ArticleDTO {
    @Id
    private Long id;

    @Column(name = "article_front")
    private String articleFront;

    @Column(name = "article_title")
    private String articleTitle;

    @Column(name = "create_time")
    private Long createTime;

    @Column(name = "article_author")
    private Long articleAuthor;

    @Column(name = "article_content")
    private String articleContent;

    @Column(name = "user_name")
    private String userName;
}
