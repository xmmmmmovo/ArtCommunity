package org.nuc.course.model;

import java.util.Date;
import javax.persistence.*;

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comment_by")
    private Long commentBy;

    @Column(name = "comment_content")
    private String commentContent;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "comment_time")
    private Date commentTime;

    @Column(name = "comment_like_count")
    private Long commentLikeCount;

    @Column(name = "comment_count")
    private Integer commentCount;

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
     * @return comment_by
     */
    public Long getCommentBy() {
        return commentBy;
    }

    /**
     * @param commentBy
     */
    public void setCommentBy(Long commentBy) {
        this.commentBy = commentBy;
    }

    /**
     * @return comment_content
     */
    public String getCommentContent() {
        return commentContent;
    }

    /**
     * @param commentContent
     */
    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    /**
     * @return parent_id
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * @param parentId
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * @return comment_time
     */
    public Date getCommentTime() {
        return commentTime;
    }

    /**
     * @param commentTime
     */
    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    /**
     * @return comment_like_count
     */
    public Long getCommentLikeCount() {
        return commentLikeCount;
    }

    /**
     * @param commentLikeCount
     */
    public void setCommentLikeCount(Long commentLikeCount) {
        this.commentLikeCount = commentLikeCount;
    }

    /**
     * @return comment_count
     */
    public Integer getCommentCount() {
        return commentCount;
    }

    /**
     * @param commentCount
     */
    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }
}