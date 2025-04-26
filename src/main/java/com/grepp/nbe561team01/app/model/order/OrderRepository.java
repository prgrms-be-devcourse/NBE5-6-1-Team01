package com.grepp.nbe561team01.app.model.order;

import com.grepp.nbe561team01.app.model.order.dto.OrderInfoDto;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OrderRepository {

    @Select("""
        SELECT EMAIL, ADDRESS, CREATED_AT, ORDER_STATUS FROM ORDERS
        ORDER BY EMAIL ASC, CREATED_AT DESC;
    """)
    List<OrderInfoDto> selectAllOrders();

}
