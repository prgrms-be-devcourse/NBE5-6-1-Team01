package com.grepp.nbe561team01.app.model.order;

import com.grepp.nbe561team01.app.model.order.dto.OrderDto;
import com.grepp.nbe561team01.app.model.order.dto.OrderItemDto;
import com.grepp.nbe561team01.app.model.order.dto.admin.OrderInfoDto;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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

    List<OrderDto> selectOrderByEmail(@Param("email") String email);

    @Select("select * from orders where email = #{email} and order_status = 'DELIVER'")
    List<OrderDto> selectOrderDeliverByEmail(String email);

    @Select("select * from orders where email = #{email} and order_status = 'CANCEL'")
    List<OrderDto> selectOrderCancelByEmail(String email);

    @Select("select * from orderitems where order_id = #{orderId}")
    List<OrderItemDto> selectItemByOrder(Integer orderId);

    @Select("select * from orders where order_id = #{orderId}")
    OrderDto selectAllByOrderId(Integer orderId);

    @Select("select * from orderitems where order_id = #{orderId}")
    List<OrderItemDto> selectOrderItemById(Integer orderId);

    // SoftDelete 적용
    @Update("update orders set deleted_at = now(), order_status = 'CANCEL' where order_id = #{orderId}")
    boolean removeOrder(Integer orderId);

    @Update("UPDATE orders SET deleted_at = NOW(), order_status = 'CANCEL' WHERE email = #{email} AND postcode = #{postcode} AND order_status = 'ORDER'")
    boolean removeOrdersByPostcode(String email, String postcode);

    // 관리자 유저 주문처리 시간 관리
    @Update("update orders set order_status = 'DELIVER' where order_id = #{orderId}")
    int updateStatusToDeliver(Integer orderId);

    // 유저 주문처리 시간 관리
    int updateStatusByUserToDeliver(@Param("email") String email);

    @Select("select count(order_id) from orders")
    int countOrders();

    @Select("select count(order_id) from orders where order_status = 'CANCEL'")
    int countCancelledStatus();

    @Select("select count(order_id) from orders where order_status = 'DELIVER'")
    int countDeliverOrderStatus();
    @Insert("""
        INSERT INTO orders (email, address, postcode, total_price, created_at, order_status)
        VALUES (#{email}, #{address}, #{postcode}, #{totalPrice}, NOW(), #{orderStatus})
    """)
    void insertOrder(OrderDto orderDto);

    @Select("SELECT LAST_INSERT_ID()")
    int selectLastInsertId();

}
