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
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.AssertJUnit.*;

public class CreateOrderTest {
    StoreService storeService = new StoreService();
    PetService petService = new PetService();

    @Test
    public void createOrderPositive() {
        Pet petForOrder = PetProvider.createPet("Rinna", "available");
        Pet pet = petService.create(petForOrder).as(Pet.class);
        Order newOrder = OrderProvider.createOrder(Long.valueOf(randomNumeric(12)), pet.getId(), false);
        Response responseCreateOrder = storeService.createOrder(newOrder);

        Order order = responseCreateOrder.as(Order.class);
        int statusCode = responseCreateOrder.statusCode();

        assertEquals(SC_OK, statusCode);
        assertNotNull(order.getId());
        assertTrue(order.getQuantity() > 0);
    }

    @Test
    public void createOrderNegative() {
        Order newOrder = OrderProvider.createOrder(null, null, true);

        Response responseCreateOrder = storeService.createOrder(newOrder);
        int statusCode = responseCreateOrder.statusCode();

        assertEquals(SC_BAD_REQUEST, statusCode);
    }
}
