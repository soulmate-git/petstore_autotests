package petstoretests;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import petStore.dto.Order;
import petStore.dto.Pet;
import petStore.providers.OrderProvider;
import petStore.providers.PetProvider;
import petStore.services.PetService;
import petStore.services.StoreService;

import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.apache.http.HttpStatus.*;
import static org.testng.AssertJUnit.assertEquals;

public class DeleteOrderTest {
    StoreService storeService = new StoreService();
    PetService petService = new PetService();

    @Test
    public void deleteOrderById() {
        Pet petForOrder = PetProvider.createPet("Rinna", "available");
        Pet pet = petService.create(petForOrder).as(Pet.class);
        Order newOrder = OrderProvider.createOrder(Long.valueOf(randomNumeric(12)), pet.getId(), false);
        Response responseCreateOrder = storeService.createOrder(newOrder);
        Order order = responseCreateOrder.as(Order.class);

        int statusCodeCreate = responseCreateOrder.statusCode();
        Response responseDelete = storeService.removeOrder(String.valueOf(order.getId()));
        int statusCodeDelete = responseDelete.statusCode();

        assertEquals(SC_OK, statusCodeCreate);
        assertEquals(SC_OK, statusCodeDelete);
    }

    @Test
    public void deleteOrderNotFound() {
        int statusCode = storeService.removeOrder(null).statusCode();

        assertEquals(SC_NOT_FOUND, statusCode);
    }

    @Test
    public void deleteOrderInvalidIdSupplied() {
        int statusCode = storeService.removeOrder("not valid value").statusCode();

        // TODO От сервера ожидается код 400, а сервис возвращает 404
        assertEquals(SC_BAD_REQUEST, statusCode);
    }
}
