package com.grepp.nbe561team01.app.model.order;

import com.grepp.nbe561team01.app.model.order.dto.OrderItemDto;
import com.grepp.nbe561team01.app.controller.web.Order.form.OrderItem;
import com.grepp.nbe561team01.app.controller.web.Order.form.OrderRequest;
import com.grepp.nbe561team01.app.model.item.ItemRepository;
import com.grepp.nbe561team01.app.model.item.dto.ItemDto;
import com.grepp.nbe561team01.app.model.order.code.OrderStatus;
import com.grepp.nbe561team01.app.model.order.dto.OrderItemDto;
import com.grepp.nbe561team01.app.model.order.dto.admin.OrderInfoDto;
import com.grepp.nbe561team01.app.model.order.dto.admin.OrderInfoDto;
import com.grepp.nbe561team01.app.model.order.dto.OrderDto;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import com.grepp.nbe561team01.app.model.order.dto.OrderDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ItemRepository itemRepository;

    public List<OrderDto> getAllOrders() {
        return orderRepository.selectAllOrders();
    }
    public List<String> getItemNamesByOrderId(Integer orderId) {
        return orderRepository.selectOrderItemNames(orderId);
    }

    @Transactional
    public Map<String, List<OrderDto>> findOrderAllByEmail(String email){
        Map<String, List<OrderDto>> orderList = new HashMap<>();
        orderList.put("ORDER", orderRepository.selectOrderByEmail(email));
        orderList.put("DELIVER", orderRepository.selectOrderDeliverByEmail(email));
        orderList.put("CANCEL", orderRepository.selectOrderCancelByEmail(email));

        return orderList;
    }

    @Transactional
    public Map<Integer, List<OrderItemDto>> findOrderItemByOrder(Map<String, List<OrderDto>> orderList) {
        List<Integer> orderIds = orderList.values().stream()
                .flatMap(List::stream)
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

    @Transactional
    public void createOrder(OrderDto orderDto, String email, List<OrderItem> orderItems){

        orderDto.setEmail(email);
        orderDto.setOrderStatus(OrderStatus.ORDER);
        orderRepository.insertOrder(orderDto);

        Integer orderId = orderRepository.selectLastInsertId();
        for(OrderItem item : orderItems){
            ItemDto itemDto = itemRepository.findById((long) Integer.parseInt(item.getItemId()));
            OrderItemDto orderItemDto = new OrderItemDto();
            orderItemDto.setOrderId(orderId);
            orderItemDto.setItemId(Math.toIntExact(itemDto.getItemId()));
            orderItemDto.setItemName(itemDto.getItemName());
            orderItemDto.setItemPrice(itemDto.getItemPrice());
            orderItemDto.setItemCount(item.getQuantity());

            orderItemRepository.insertOrderItem(orderItemDto);
        }
    }
    @Transactional
    public void updateOrderStatusAll(List<OrderDto> orderList) {
        LocalDateTime now = LocalDateTime.now();

        for (OrderDto info : orderList) {
            // STATUS가 ORDER일 때만 실행
            if (!"ORDER".equals(info.getOrderStatus())) continue;

            LocalDateTime createdAt = info.getCreatedAt();
            LocalDate createdDate = createdAt.toLocalDate();
            LocalDateTime createdDay14 = createdDate.atTime(14, 0);

            LocalDateTime targetTime;

            // 주문시간의 기준날짜 설정 (당일 14시 이전이면 당일 처리, 이후이면 다음날 처리)
            if (createdAt.isBefore(createdDay14)) targetTime = createdDate.atTime(14, 0);
            else targetTime = createdDate.plusDays(1).atTime(14, 0);
            log.info("targetTime: {} {}",createdAt, targetTime);

            // 현재 시간이 기준날짜이면, STATUS DELIVER로 업데이트
            if (now.isAfter(targetTime)) {
                orderRepository.updateStatusToDeliver(info.getOrderId());
                log.info("실행");
            }
        }
    }

    @Transactional
    public void updateOrderStatusByUser(String email){
        orderRepository.updateStatusByUserToDeliver(email);
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
