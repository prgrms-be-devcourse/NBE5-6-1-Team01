package com.grepp.nbe561team01.app.model.order;

import com.grepp.nbe561team01.app.model.order.dto.OrderDto;
import com.grepp.nbe561team01.app.model.order.dto.OrderItemDto;
import com.grepp.nbe561team01.app.model.order.dto.admin.OrderInfoDto;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface OrderRepository {

    @Select(
        """
        select
            o.ORDER_ID,
            o.EMAIL,
            oi.ITEM_NAME,
            o.ADDRESS,
            o.ORDER_STATUS,
            o.CREATED_AT
        from
            ORDERS o
        join
            ORDERITEMS oi on o.ORDER_ID = oi.ORDER_ID
        order by 
            o.EMAIL, o.ORDER_ID
        """
    )
    List<OrderInfoDto> selectAllOrders();

    @Select("select ITEM_NAME from ORDERITEMS where ORDER_ID=#{orderId}")
    List<String> selectOrderItemNames(Integer orderId);

    @Select("select * from orders where email = #{email}")
    List<OrderDto> selectAllByEmail(String email);

    @Select("select * from orderitems where order_id = #{orderId}")
    List<OrderItemDto> selectItemByOrder(Integer orderId);

    // SoftDelete 적용
    @Update("update orders set deleted_at = now(), order_status = 'CANCEL' where order_id = #{orderId}")
    boolean removeOrder(Integer orderId);

    @Update("update orders set order_status = 'DELIVER' where order_id = #{orderId}")
    int updateStatusToDeliver(Integer orderId);
}
