package org.nuc.course.model;

import javax.persistence.*;

public class Art {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "art_name")
    private String artName;

    @Column(name = "art_author")
    private Long artAuthor;

    @Column(name = "create_time")
    private Long createTime;

    @Column(name = "modified_time")
    private Long modifiedTime;

    @Column(name = "art_size")
    private Long artSize;

    @Column(name = "art_tag")
    private Long artTag;

    @Column(name = "art_like_num")
    private Long artLikeNum;

    @Column(name = "art_commit_num")
    private Long artCommitNum;

    @Column(name = "art_content")
    private String artContent;

    @Column(name = "art_pic_url")
    private String artPicUrl;

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
     * @return art_name
     */
    public String getArtName() {
        return artName;
    }

    /**
     * @param artName
     */
    public void setArtName(String artName) {
        this.artName = artName;
    }

    /**
     * @return art_author
     */
    public Long getArtAuthor() {
        return artAuthor;
    }

    /**
     * @param artAuthor
     */
    public void setArtAuthor(Long artAuthor) {
        this.artAuthor = artAuthor;
    }

    /**
     * @return create_time
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * @return modified_time
     */
    public Long getModifiedTime() {
        return modifiedTime;
    }

    /**
     * @param modifiedTime
     */
    public void setModifiedTime(Long modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    /**
     * @return art_size
     */
    public Long getArtSize() {
        return artSize;
    }

    /**
     * @param artSize
     */
    public void setArtSize(Long artSize) {
        this.artSize = artSize;
    }

    /**
     * @return art_tag
     */
    public Long getArtTag() {
        return artTag;
    }

    /**
     * @param artTag
     */
    public void setArtTag(Long artTag) {
        this.artTag = artTag;
    }

    /**
     * @return art_like_num
     */
    public Long getArtLikeNum() {
        return artLikeNum;
    }

    /**
     * @param artLikeNum
     */
    public void setArtLikeNum(Long artLikeNum) {
        this.artLikeNum = artLikeNum;
    }

    /**
     * @return art_commit_num
     */
    public Long getArtCommitNum() {
        return artCommitNum;
    }

    /**
     * @param artCommitNum
     */
    public void setArtCommitNum(Long artCommitNum) {
        this.artCommitNum = artCommitNum;
    }

    /**
     * @return art_content
     */
    public String getArtContent() {
        return artContent;
    }

    /**
     * @param artContent
     */
    public void setArtContent(String artContent) {
        this.artContent = artContent;
    }

    /**
     * @return art_pic_url
     */
    public String getArtPicUrl() {
        return artPicUrl;
    }

    /**
     * @param artPicUrl
     */
    public void setArtPicUrl(String artPicUrl) {
        this.artPicUrl = artPicUrl;
    }
}