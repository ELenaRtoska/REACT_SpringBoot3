package mk.finki.ukim.mk.lab.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Order {

    private UUID orderId;

    private String pizzaType;

    private String pizzaSize;

    private String clientName;

    private String clientAddress;

    public Order(){
        this.orderId = UUID.randomUUID();
    }

    public Order(String pizzaType, String pizzaSize, String clientName, String clientAddress) {
        this.pizzaType = pizzaType;
        this.pizzaSize = pizzaSize;
        this.clientName = clientName;
        this.clientAddress = clientAddress;
        this.orderId = UUID.randomUUID();
    }
}
