package org.nuc.course.model;

import java.util.Date;
import javax.persistence.*;

public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tag_name")
    private String tagName;

    @Column(name = "register_time")
    private Date registerTime;

    @Column(name = "usage_count")
    private Long usageCount;

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
     * @return tag_name
     */
    public String getTagName() {
        return tagName;
    }

    /**
     * @param tagName
     */
    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    /**
     * @return register_time
     */
    public Date getRegisterTime() {
        return registerTime;
    }

    /**
     * @param registerTime
     */
    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    /**
     * @return usage_count
     */
    public Long getUsageCount() {
        return usageCount;
    }

    /**
     * @param usageCount
     */
    public void setUsageCount(Long usageCount) {
        this.usageCount = usageCount;
    }
}