package petstoretests;


import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
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

    @DataProvider(name = "status")
    public Object[][] status() {
        return new Object[][]{
                {"available"},
                {"pending"},
                {"sold"}
        };
    }

    @Test(dataProvider = "status")
    public void createPetPositive(String status) {
        Pet petRinna = PetProvider.createPet("Rinna", status);
        Response response = petService.create(petRinna);
        Pet pet = response.as(Pet.class);
        int statusCode = response.statusCode();

        assertEquals(SC_OK, statusCode);
        assertEquals(pet.getName(), petRinna.getName());
        assertEquals(pet.getStatus(), petRinna.getStatus());
        assertNotNull(pet.getId());
    }

    @Test
    public void createPetNegative() {
        Pet petNull = PetProvider.createPet(null, null);
        int statusCode = petService.create(petNull).statusCode();

        // TODO сервис возвращает статускод 200, а от сервиса ожидается 405
        assertEquals(SC_METHOD_NOT_ALLOWED, statusCode);
    }
}
