package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order;

import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.order.Order;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.order.OrderDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderUserResponse {
    private Integer orderId;
    private Date orderDate;
    private List<OrderDetailsData> orderDetailsData;

    public static OrderUserResponse fromOrder(Order order, List<OrderDetails> orderDetails) {
        return OrderUserResponse.builder()
                .orderId(order.getId())
                .orderDate(order.getOrderDate())
                .orderDetailsData(orderDetails
                        .stream()
                        .map(OrderDetailsData::fromOrderDetails)
                        .toList())
                .build();
    }
}