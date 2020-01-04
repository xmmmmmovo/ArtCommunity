package org.nuc.course.model;

import javax.persistence.*;

public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tag_name")
    private String tagName;

    @Column(name = "usage_count")
    private Long usageCount;

    @Column(name = "tag_describe")
    private String tagDescribe;

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

    /**
     * @return tag_describe
     */
    public String getTagDescribe() {
        return tagDescribe;
    }

    /**
     * @param tagDescribe
     */
    public void setTagDescribe(String tagDescribe) {
        this.tagDescribe = tagDescribe;
    }
}