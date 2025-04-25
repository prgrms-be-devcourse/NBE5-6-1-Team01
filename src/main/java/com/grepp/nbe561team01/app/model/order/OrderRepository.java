package com.grepp.nbe561team01.app.model.order;

import com.grepp.nbe561team01.app.model.order.dto.OrderDto;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface OrderRepository {

    @Select("select * from orders where email = #{email} and deleted_at is null")
    List<OrderDto> selectAllByEmail(String email);

    // SoftDelete 적용
    @Update("update orders set deleted_at = now() where order_id = #{orderId}")
    boolean removeOrder(Integer orderId);
}
