package petStore.services;

import io.restassured.response.Response;
import petStore.dto.CreatePetDto;
import petStore.utils.HttpUtil;

import java.util.HashMap;

public class PetServices extends HttpUtil {
    HashMap<String, String> headers = new HashMap<>();

    public PetServices() {
        this.url = "/pet";
        headers.put("Content-type", "application/json");
    }

    public Response createPet(CreatePetDto pet) {
        return post(headers, pet, url);
    }

    public Response deletePet(String id) {
        return delete(url + "/" + id);
    }
}
