package nus.iss.day24workshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import nus.iss.day24workshop.model.Neworder;
import nus.iss.day24workshop.model.Orderdetails;
import nus.iss.day24workshop.repository.OrderRepository;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public Integer addOrder(Neworder neworder) {
        return orderRepository.addOrder(neworder);
    }

    public Integer addOrderDetails(Integer orderId, Orderdetails orderdetails) {
        return orderRepository.addOrderDetails(orderId, orderdetails);
    }

}
