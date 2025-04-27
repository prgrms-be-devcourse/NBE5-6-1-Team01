package com.grepp.nbe561team01.app.model.order;

import com.grepp.nbe561team01.app.model.order.dto.OrderItemDto;
import com.grepp.nbe561team01.app.model.order.dto.admin.OrderInfoDto;
import com.grepp.nbe561team01.app.model.order.dto.OrderDto;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<OrderDto> getAllOrders() {
        return orderRepository.selectAllOrders();
    }
    public List<String> getItemNamesByOrderId(Integer orderId) {
        return orderRepository.selectOrderItemNames(orderId);
    }
  
    @Transactional
    public List<OrderDto> findOrderByEmail(String email){
        List<OrderDto> orderList = orderRepository.selectAllByEmail(email);
        return orderList;
    }

    @Transactional
    public boolean removeOrder(Integer orderId){
        return orderRepository.removeOrder(orderId);
    }


    public OrderDto findById(Integer orderId) {
        return orderRepository.selectAllByOrderId(orderId);
    }

    public List<OrderItemDto> findItemById(Integer orderId) {
        return orderRepository.selectOrderItemById(orderId);
    }

    public List<Integer> getTotalOrderStatuses() {
        List<Integer> orderStatuses = new ArrayList<>();
        orderStatuses.add(orderRepository.countOrders());
        orderStatuses.add(orderRepository.countCancelledStatus());
        orderStatuses.add(orderRepository.countDeliverOrderStatus());

        return orderStatuses;
    }

}
