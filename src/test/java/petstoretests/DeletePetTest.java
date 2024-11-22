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
        Long petId = 123L;
        Pet petWithId = PetProvider.createPet(petId, "Rinna", "available");
        Response response = petService.create(petWithId);
        int statusCodeCreate = response.statusCode();
        petService.remove(String.valueOf(petId));
        assertEquals(SC_OK, statusCodeCreate);
        int statusCodeFind = petService.findById(petId).statusCode();
        assertEquals(SC_NOT_FOUND, statusCodeFind);
    }

    @Test
    public void deletePetNotFound() {
        int statusCode = petService.remove(null).statusCode();
        assertEquals(SC_NOT_FOUND, statusCode);
    }

    @Test
    public void deletePetInvalidIdSupplied() {
        int statusCode = petService.remove("not valid value").statusCode();
        assertEquals(SC_BAD_REQUEST, statusCode);
    }
}
