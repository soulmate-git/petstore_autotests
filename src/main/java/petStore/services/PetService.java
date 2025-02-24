package petStore.services;

import io.restassured.response.Response;
import petStore.dto.Pet;

import java.util.HashMap;

public class PetService extends BaseService {
    HashMap<String, String> headers = new HashMap<>();

    public PetService() {
        this.url = "/pet";
        headers.put("Content-type", "application/json");
    }

    public Response create(Pet pet) {
        return post(headers, pet, url);
    }

    public Response remove(String id) {
        return delete(url + "/" + id);
    }

    public Response findByStatus(String status) {
        return get(url + "/findByStatus?status=" + status);
    }

    public Response findById(Long id) {
        return get(url + "/" + id);
    }

    public Response update(Pet pet) {
        return put(url, pet);
    }
}
