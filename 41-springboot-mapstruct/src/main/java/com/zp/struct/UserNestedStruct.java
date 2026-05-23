package com.zp.struct;

import com.zp.dto.UserNestedDTO;
import com.zp.entity.UserNestedDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserNestedStruct {

    @Mapping(source = "birthday", target = "birthday", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "regDate", expression = "java(org.apache.commons.lang3.time.DateFormatUtils.format(userNestedDO.getRegDate(),\"yyyy-MM-dd HH:mm:ss\"))")
    @Mapping(source = "userAddressDO", target = ".")
    @Mapping(source = "userExtDO", target = ".")
    @Mapping(source = "userExtDO.memo", target = "memo")
    UserNestedDTO toUserNestedDTO(UserNestedDO userNestedDO);

}
