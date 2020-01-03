package org.nuc.course.model;

import java.util.Date;
import javax.persistence.*;

public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "article_front")
    private String articleFront;

    @Column(name = "article_title")
    private String articleTitle;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "article_content")
    private String articleContent;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return article_front
     */
    public String getArticleFront() {
        return articleFront;
    }

    /**
     * @param articleFront
     */
    public void setArticleFront(String articleFront) {
        this.articleFront = articleFront;
    }

    /**
     * @return article_title
     */
    public String getArticleTitle() {
        return articleTitle;
    }

    /**
     * @param articleTitle
     */
    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return article_content
     */
    public String getArticleContent() {
        return articleContent;
    }

    /**
     * @param articleContent
     */
    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }
}