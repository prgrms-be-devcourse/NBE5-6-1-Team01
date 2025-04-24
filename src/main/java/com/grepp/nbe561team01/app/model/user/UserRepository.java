package com.grepp.nbe561team01.app.model.user;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserRepository {

    // softDelete 적용
    @Update("update users set deleted_at = now() where email = #{email}")
    boolean removeUser(String email);

}
