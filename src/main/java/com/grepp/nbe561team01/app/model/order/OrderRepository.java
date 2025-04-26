package com.grepp.nbe561team01.app.model.order;

import com.grepp.nbe561team01.app.model.order.dto.OrderDto;
import com.grepp.nbe561team01.app.model.order.dto.admin.OrderInfoDto;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface OrderRepository {

    @Select("select * from orders")
    List<OrderDto> selectAllOrders();

    @Select("select ITEM_NAME from ORDERITEMS where ORDER_ID=#{orderId}")
    List<String> selectOrderItemNames(Integer orderId);

    @Select("select * from orders where email = #{email}")
    List<OrderDto> selectAllByEmail(String email);

    @Select("select * from orders where order_id = #{orderId}")
    OrderDto selectAllByOrderId(Integer orderId);

    // SoftDelete 적용
    @Update("update orders set deleted_at = now(), order_status = 'CANCEL' where order_id = #{orderId}")
    boolean removeOrder(Integer orderId);

}
