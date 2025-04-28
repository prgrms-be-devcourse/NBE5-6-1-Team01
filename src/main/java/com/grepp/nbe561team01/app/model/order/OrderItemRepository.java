package com.grepp.nbe561team01.app.model.order;


import com.grepp.nbe561team01.app.model.order.dto.OrderItemDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderItemRepository {

    @Insert("""
        INSERT INTO orderitems (order_id, item_id, item_name, item_price, item_count)
        VALUES (#{orderId}, #{itemId}, #{itemName}, #{itemPrice}, #{itemCount})
    """)
    void insertOrderItem(OrderItemDto orderItemDto);
}
