package petstoretests;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import petStore.dto.Pet;
import petStore.services.PetService;

import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.AssertJUnit.*;
import static petStore.providers.PetProvider.createPet;


public class UpdatePetTest {
    PetService petService = new PetService();

    @Test
    public void updatePet() {
        Pet oldPet = createPet("Tom", "available");
        Response createResponse = petService.create(oldPet);
        assertEquals(SC_OK, createResponse.statusCode());
        Long petId = createResponse.as(Pet.class).getId();

        Pet updatedPet = createPet(petId, "Rinna", "sold");
        Response updateResponse = petService.update(updatedPet);
        Pet currentPet = updateResponse.as(Pet.class);

        assertEquals(SC_OK, updateResponse.statusCode());
        assertNotSame(oldPet, updatedPet);
        assertEquals(updatedPet, currentPet);
    }
}
