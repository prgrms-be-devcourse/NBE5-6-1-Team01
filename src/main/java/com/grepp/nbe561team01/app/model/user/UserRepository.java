package com.grepp.nbe561team01.app.model.user;

import com.grepp.nbe561team01.app.model.user.dto.UserDto;
import java.util.Optional;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserRepository {
    @Select("select password from USERS where email = #{email} and deleted_at is null")
    String selectPasswordByeMail(String email);

    @Update("update USERS set password=#{password} where email = #{email} and deleted_at is null")
    void update(String email, String password);

    @Select("select count(*) from users where email = #{email} and deleted_at is null")
    Boolean existsUser(String email);

    @Insert("insert into users(EMAIL, PASSWORD, ROLE, CREATED_AT)"
            + "values (#{email}, #{password}, #{role}, #{createdAt})")
    void insert(UserDto dto);


    // softDelete 적용
    @Update("update users set deleted_at = now() where email = #{email}")
    boolean removeUser(String email);

    @Select("select * from users where email = #{email} and deleted_at is null")
    UserDto findByEmail(String email);

    @Select("select * from users where email = #{email} and deleted_at is null")
    Optional<UserDto> selectByEmail(String email);

    @Update("update orders set ORDER_STATUS = #{status} where order_id = #{orderId}")
    void cancelOrder(int orderId, String status);

}
