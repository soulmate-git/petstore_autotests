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
        Response response = petService.create(PetProvider.createPet("Rinna", "available"));

        Pet pet = response.then().extract().as(Pet.class);

        petService.remove(String.valueOf(pet.getId()));
        assertEquals(SC_OK, response.statusCode());
    }

    @Test
    public void deletePetNotFound() {
        assertEquals(SC_NOT_FOUND, petService.remove(null).statusCode());
    }

    @Test
    public void deletePetInvalidIdSupplied() {
        assertEquals(SC_BAD_REQUEST, petService.remove("not_valid_value").statusCode());
    }
}
