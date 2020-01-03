package org.nuc.course.utils;

import org.nuc.course.dto.AdminDTO;
import org.nuc.course.dto.UserDTO;
import org.nuc.course.model.Admin;
import org.nuc.course.model.User;

public class DTOUtils {

    public static UserDTO userToDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getToken(),
                user.getUserName(),
                user.getUserBio()
        );
    }

    public static AdminDTO adminToDTO(Admin admin) {
        return new AdminDTO(
                admin.getId(),
                admin.getToken(),
                admin.getAdminName(),
                admin.getAdminAvatar(),
                admin.getRoles()
        );
    }
}
