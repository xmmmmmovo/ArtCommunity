package org.nuc.course.model;

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
    private Long commentTime;

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
    public Long getCommentTime() {
        return commentTime;
    }

    /**
     * @param commentTime
     */
    public void setCommentTime(Long commentTime) {
        this.commentTime = commentTime;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", commentBy=" + commentBy +
                ", commentContent='" + commentContent + '\'' +
                ", parentId=" + parentId +
                ", commentTime=" + commentTime +
                '}';
    }
}