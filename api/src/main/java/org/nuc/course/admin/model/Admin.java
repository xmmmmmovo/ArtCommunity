package org.nuc.course.admin.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Table(name = "admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /** 邮箱 */
    @Column(name = "admin_email")
    private String email;

    /** 账户名 */
    @Column(name = "admin_name")
    private String name;

    /** 密码 */
    @Column(name = "admin_password")
    private String password;

    /** 密码 */
    @Column(name = "admin_avatar")
    private String avatar;

    /** 注册时间 */
    @Column(name = "register_time")
    private Timestamp registerTime;

}
