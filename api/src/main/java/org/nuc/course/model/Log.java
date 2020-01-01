package org.nuc.course.model;

import javax.persistence.*;

public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "operator_id")
    private Long operatorId;

    @Column(name = "operator_identity")
    private String operatorIdentity;

    @Column(name = "operate_content")
    private String operateContent;

    @Column(name = "operate_type")
    private String operateType;

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
     * @return operator_id
     */
    public Long getOperatorId() {
        return operatorId;
    }

    /**
     * @param operatorId
     */
    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    /**
     * @return operator_identity
     */
    public String getOperatorIdentity() {
        return operatorIdentity;
    }

    /**
     * @param operatorIdentity
     */
    public void setOperatorIdentity(String operatorIdentity) {
        this.operatorIdentity = operatorIdentity;
    }

    /**
     * @return operate_content
     */
    public String getOperateContent() {
        return operateContent;
    }

    /**
     * @param operateContent
     */
    public void setOperateContent(String operateContent) {
        this.operateContent = operateContent;
    }

    /**
     * @return operate_type
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * @param operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }
}