package petStore.providers;

import petStore.dto.Order;

public class OrderProvider {
    public static Order createOrder(Long id, Long petId, boolean complete) {
        return Order.builder()
                .id(id)
                .petId(petId)
                .quantity(1)
                .complete(complete)
                .build();
    }
}
