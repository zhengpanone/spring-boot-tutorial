package com.zp.struct;

import com.zp.dto.UserShowDTO;
import com.zp.entity.UserDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserSpringStruct {

    @Mapping(source = "birthday", target = "birthday", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "regDate", expression = "java(org.apache.commons.lang3.time.DateFormatUtils.format(userDO.getRegDate(),\"yyyy-MM-dd HH:mm:ss\"))")
    @Mapping(source = "userExtDO.regSource", target = "registerSource")
    @Mapping(source = "userExtDO.favorite", target = "favorite")
    @Mapping(target = "memo", ignore = true)
    UserShowDTO toUserShowDTO(UserDO userDO);

    List<UserShowDTO> toUserShowDTOs(List<UserDO> userDOs);

}
