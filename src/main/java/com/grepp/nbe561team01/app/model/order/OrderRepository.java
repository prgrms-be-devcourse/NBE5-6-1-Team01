package com.grepp.nbe561team01.app.model.order;

import com.grepp.nbe561team01.app.model.order.dto.admin.OrderInfoDto;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
    List<String> selectOrderItemNames(String orderId);


}
