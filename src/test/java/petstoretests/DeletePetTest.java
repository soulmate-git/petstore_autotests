package petstoretests;

import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import petStore.dto.Pet;
import petStore.providers.PetProvider;
import petStore.services.PetService;

import static org.apache.http.HttpStatus.*;
import static org.testng.AssertJUnit.assertEquals;

public class DeletePetTest {
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
    public void deleteById(String status) {
        Long petId = 123L;
        Pet petWithId = PetProvider.createPet(petId, "Rinna", status);

        Response response = petService.create(petWithId);
        int statusCodeCreate = response.statusCode();
        assertEquals(SC_OK, statusCodeCreate);

        petService.remove(String.valueOf(petId));
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

        // TODO От сервера ожидается код 400, а сервис возвращает 200
        assertEquals(SC_BAD_REQUEST, statusCode);
    }
}
