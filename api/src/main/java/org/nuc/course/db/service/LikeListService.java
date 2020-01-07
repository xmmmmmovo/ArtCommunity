package org.nuc.course.db.service;
import org.nuc.course.dto.LikeDTO;
import org.nuc.course.model.LikeList;
import org.nuc.course.db.service.Service;

import java.util.List;


/**
 * Created by xmmmmmovo on 2020/01/07.
 */
public interface LikeListService extends Service<LikeList> {
    List<LikeDTO> findAllList();
    List<LikeDTO> findLikeDTO(String token);
    LikeDTO findADTO(Long id);
}
