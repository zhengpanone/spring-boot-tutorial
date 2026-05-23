package com.zp.struct;

import com.zp.dto.UserMultiDTO;
import com.zp.entity.UserAddressDO;
import com.zp.entity.UserDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMultiStruct {

    @Mapping(source = "userDO.birthday", target = "birthday", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "userDO.regDate", expression = "java(org.apache.commons.lang3.time.DateFormatUtils.format(userDO.getRegDate(),\"yyyy-MM-dd HH:mm:ss\"))")
    @Mapping(source = "userAddressDO.postcode", target = "postcode")
    @Mapping(source = "userAddressDO.address", target = "address")
    @Mapping(target = "memo", ignore = true)
    UserMultiDTO toUserMultiDTO(UserDO userDO, UserAddressDO userAddressDO);

}
