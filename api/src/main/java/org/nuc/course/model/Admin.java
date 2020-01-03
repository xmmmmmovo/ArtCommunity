package org.nuc.course.model;

import java.util.Date;
import javax.persistence.*;

public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "admin_name")
    private String adminName;

    @Column(name = "admin_email")
    private String adminEmail;

    @Column(name = "admin_password")
    private String adminPassword;

    @Column(name = "admin_avatar")
    private String adminAvatar;

    @Column(name = "register_time")
    private Date registerTime;

    private String token;

    private String roles;

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
     * @return admin_name
     */
    public String getAdminName() {
        return adminName;
    }

    /**
     * @param adminName
     */
    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    /**
     * @return admin_email
     */
    public String getAdminEmail() {
        return adminEmail;
    }

    /**
     * @param adminEmail
     */
    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    /**
     * @return admin_password
     */
    public String getAdminPassword() {
        return adminPassword;
    }

    /**
     * @param adminPassword
     */
    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    /**
     * @return admin_avatar
     */
    public String getAdminAvatar() {
        return adminAvatar;
    }

    /**
     * @param adminAvatar
     */
    public void setAdminAvatar(String adminAvatar) {
        this.adminAvatar = adminAvatar;
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
     * @return token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return roles
     */
    public String getRoles() {
        return roles;
    }

    /**
     * @param roles
     */
    public void setRoles(String roles) {
        this.roles = roles;
    }
}