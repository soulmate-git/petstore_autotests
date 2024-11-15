package petstoretests;


import io.restassured.response.Response;
import org.testng.annotations.Test;
import petStore.dto.Pet;
import petStore.services.PetService;

import static org.apache.http.HttpStatus.SC_METHOD_NOT_ALLOWED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.AssertJUnit.assertEquals;
import static petStore.providers.PetProvider.createPet;

public class CreatePetTest {
    PetService petService = new PetService();

    @Test
    public void createPetPositive() {
        Response response = petService.create(createPet("Rinna", "available"));

        Pet pet = response.then().extract().as(Pet.class);

        assertEquals(createPet(pet.getId(), "Rinna", "available"), pet);
        assertEquals(SC_OK, response.getStatusCode());
    }

    @Test
    public void createPetNegative() {
        assertEquals(SC_METHOD_NOT_ALLOWED, petService.create(createPet(null, null)).statusCode());
    }
}
