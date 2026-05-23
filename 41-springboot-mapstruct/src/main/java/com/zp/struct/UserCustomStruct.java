package com.zp.struct;


import com.zp.dto.UserCustomDTO;
import com.zp.dto.UserExtDTO;
import com.zp.entity.UserDO;
import com.zp.entity.UserExtDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * 微信公众号：Java技术栈
 *
 * @author 栈长
 */
@Mapper(componentModel = "spring")
public interface UserCustomStruct {

    @Mapping(source = "birthday", target = "birthday", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "regDate", expression = "java(org.apache.commons.lang3.time.DateFormatUtils.format(userDO.getRegDate(),\"yyyy-MM-dd HH:mm:ss\"))")
    @Mapping(source = "userExtDO", target = "userExtDTO")
    @Mapping(target = "memo", ignore = true)
    UserCustomDTO toUserCustomDTO(UserDO userDO);

    default UserExtDTO toUserExtDTO(UserExtDO userExtDO) {
        UserExtDTO userExtDTO = new UserExtDTO();
        userExtDTO.setKids(userExtDO.getKids());
        userExtDTO.setFavorite(userExtDO.getFavorite());

        // 覆盖这两个值
        userExtDTO.setRegSource("默认来源");
        userExtDTO.setSchool("默认学校");

        return userExtDTO;
    }

}
