package com.grepp.nbe561team01.app.model.order;

import com.grepp.nbe561team01.app.model.order.dto.OrderItemDto;
import com.grepp.nbe561team01.app.model.order.dto.admin.OrderInfoDto;
import com.grepp.nbe561team01.app.model.order.dto.OrderDto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<OrderInfoDto> getAllOrders() {
        return orderRepository.selectAllOrders();
    }
    public List<String> getItemNamesByOrderId(String orderId) {
        return orderRepository.selectOrderItemNames(orderId);
    }
  
    @Transactional
    public List<OrderDto> findOrderByEmail(String email){
        List<OrderDto> orderList = orderRepository.selectAllByEmail(email);
        return orderList;
    }

    @Transactional
    public Map<Integer, List<OrderItemDto>> findOrderItemByOrder(List<OrderDto> orderList) {
        List<Integer> orderIds = orderList.stream()
                .map(OrderDto::getOrderId)
                .collect(Collectors.toList());

        Map<Integer, List<OrderItemDto>> itemListByOrderId = new HashMap<>();
        for(Integer id:orderIds){
            itemListByOrderId.put(id, orderRepository.selectItemByOrder(id));
        }

        return itemListByOrderId;
    }

    @Transactional
    public boolean removeOrder(Integer orderId){
        return orderRepository.removeOrder(orderId);
    }
}
