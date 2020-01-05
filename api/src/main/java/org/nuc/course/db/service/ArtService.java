package org.nuc.course.db.service;
import org.nuc.course.dto.ArtDTO;
import org.nuc.course.model.Art;
import org.nuc.course.db.service.Service;

import java.util.List;


/**
 * Created by xmmmmmovo on 2020/01/04.
 */
public interface ArtService extends Service<Art> {
    List<ArtDTO> findAllList();
}
