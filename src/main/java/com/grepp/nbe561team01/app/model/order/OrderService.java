package com.grepp.nbe561team01.app.model.order;

import com.grepp.nbe561team01.app.model.order.dto.admin.OrderInfoDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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


}
