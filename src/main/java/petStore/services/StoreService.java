package petStore.services;

import io.restassured.response.Response;
import petStore.dto.Order;

import java.util.HashMap;

public class StoreService extends BaseService {
    HashMap<String, String> headers = new HashMap<>();

    public StoreService() {
        this.url = "/store/order";
        headers.put("Content-type", "application/json");
    }

    public Response createOrder(Order order) {
        return post(headers, order, url);
    }

    public Response removeOrder(String id) {
        return delete(url + "/" + id);
    }
}
