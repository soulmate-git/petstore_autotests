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
        Pet oldPet = petService.create(createPet("Tom", "available")).as(Pet.class);
        Long petId = oldPet.getId();
        Pet newPet = createPet(petId, "Rinna", "sold");
        Response response = petService.update(newPet);
        int statusCode = response.statusCode();
        Pet actualPet = response.as(Pet.class);

        assertNotSame(oldPet, newPet);
        assertEquals(newPet, actualPet);
        assertEquals(SC_OK,statusCode);
    }
}
