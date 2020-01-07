package org.nuc.course.model;

import javax.persistence.*;

@Table(name = "like_list")
public class LikeList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "art_id")
    private Long artId;

    @Column(name = "like_time")
    private Long likeTime;

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
     * @return user_id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return art_id
     */
    public Long getArtId() {
        return artId;
    }

    /**
     * @param artId
     */
    public void setArtId(Long artId) {
        this.artId = artId;
    }

    /**
     * @return like_time
     */
    public Long getLikeTime() {
        return likeTime;
    }

    /**
     * @param likeTime
     */
    public void setLikeTime(Long likeTime) {
        this.likeTime = likeTime;
    }
}