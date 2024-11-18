package petstoretests;


import io.restassured.response.Response;
import org.testng.annotations.Test;
import petStore.dto.Pet;
import petStore.providers.PetProvider;
import petStore.services.PetService;

import static org.apache.http.HttpStatus.SC_METHOD_NOT_ALLOWED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

public class CreatePetTest {
    PetService petService = new PetService();

    @Test
    public void createPetPositive() {
        Pet petRinna = PetProvider.createPet("Rinna", "availiable");
        Response response = petService.create(petRinna);
        Pet pet = response.as(Pet.class);
        int statusCode = response.statusCode();

        assertEquals(pet.getName(), petRinna.getName());
        assertEquals(pet.getStatus(), petRinna.getStatus());
        assertNotNull(pet.getId());
        assertEquals(SC_OK, statusCode);
    }

    @Test
    public void createPetNegative() {
        Pet petNull = PetProvider.createPet(null, null);
        int statusCode = petService.create(petNull).statusCode();

        assertEquals(SC_METHOD_NOT_ALLOWED, statusCode);
    }
}
