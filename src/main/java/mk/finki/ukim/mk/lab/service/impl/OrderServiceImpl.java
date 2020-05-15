package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public Order placeOrder(String pizzaType, String pizzaSize, String clientName, String address) {
        return new Order(pizzaType, pizzaSize, clientName, address);
    }

}
