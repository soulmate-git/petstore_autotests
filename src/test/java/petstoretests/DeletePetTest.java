package petstoretests;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import petStore.dto.Pet;
import petStore.providers.PetProvider;
import petStore.services.PetService;

import static org.apache.http.HttpStatus.*;
import static org.testng.AssertJUnit.assertEquals;

public class DeletePetTest {
    PetService petService = new PetService();

    @Test
    public void deleteById() {
        Pet petWithId = PetProvider.createPet("Rinna", "available");
        Response response = petService.create(petWithId);
        int statusCode = response.statusCode();

        petService.remove(String.valueOf(petWithId.getId()));
        assertEquals(SC_OK, statusCode);
    }

    @Test
    public void deletePetNotFound() {
        int statusCode = petService.remove(null).statusCode();
        assertEquals(SC_NOT_FOUND, statusCode);
    }

    @Test
    public void deletePetInvalidIdSupplied() {
        int statusCode = petService.remove("not_valid_value").statusCode();
        assertEquals(SC_BAD_REQUEST, statusCode);
    }
}
