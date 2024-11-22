package petstoretests;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import petStore.dto.Pet;
import petStore.providers.PetProvider;
import petStore.services.PetService;

import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.AssertJUnit.assertEquals;


public class UpdatePetTest {
    PetService petService = new PetService();

    @Test
    public void updatePet() {
        Pet petForUpdate = PetProvider.createPet("Tom", "available");
        Pet petOnServer = petService.create(petForUpdate).as(Pet.class);
        Long petId = petOnServer.getId();
        Pet expectedPet = PetProvider.createPet(petId, "Rinna", "sold");
        Response response = petService.update(expectedPet);
        int statusCode = response.statusCode();
        Pet actualPet = response.as(Pet.class);

        assertEquals(expectedPet, actualPet);
        assertEquals(SC_OK,statusCode);
    }
}
