package com.grepp.nbe561team01.app.model.order;

import com.grepp.nbe561team01.app.model.order.dto.admin.OrderInfoDto;
import com.grepp.nbe561team01.app.model.order.dto.OrderDto;
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

    public OrderDto getOrderById(Integer orderId) {
        return orderRepository.selectAllByOrderId(orderId);
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


}
