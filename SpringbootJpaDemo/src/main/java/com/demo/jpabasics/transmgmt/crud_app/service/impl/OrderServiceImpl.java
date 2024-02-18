package com.demo.jpabasics.transmgmt.crud_app.service.impl;

import com.demo.jpabasics.transmgmt.crud_app.dto.OrderRequest;
import com.demo.jpabasics.transmgmt.crud_app.dto.OrderResponse;
import com.demo.jpabasics.transmgmt.crud_app.entity.Order;
import com.demo.jpabasics.transmgmt.crud_app.entity.Payment;
import com.demo.jpabasics.transmgmt.crud_app.exception.PaymentException;
import com.demo.jpabasics.transmgmt.crud_app.repository.OrderRepository;
import com.demo.jpabasics.transmgmt.crud_app.repository.PaymentRepository;
import com.demo.jpabasics.transmgmt.crud_app.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private PaymentRepository paymentRepository;

    public OrderServiceImpl(OrderRepository orderRepository, PaymentRepository paymentRepository) {
        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    @Transactional
    public OrderResponse placeOrder(OrderRequest orderRequest) {

        Order order = orderRequest.getOrder();
        order.setStatus("INPROGRESS");
        order.setOrderTackingNumber(UUID.randomUUID().toString());
        orderRepository.save(order);

        Payment payment = orderRequest.getPayment();

        if(!payment.getType().equals("DEBIT")){
            throw new PaymentException("Payment card type do not support");
        }

        payment.setOrderId(order.getId());
        paymentRepository.save(payment);

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderTackingNumber(order.getOrderTackingNumber());
        orderResponse.setStatus(order.getStatus());
        orderResponse.setMessage("SUCCESS");
        return orderResponse;
    }
}
