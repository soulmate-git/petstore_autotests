package petstoretests;


import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import petStore.dto.Pet;
import petStore.services.PetService;

import java.util.List;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.AssertJUnit.assertEquals;

public class GetPetTest {
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
    public void getPetListByStatus(String status) {
        Response response = petService.findByStatus(status);
        int statusCode = response.statusCode();
        List<Pet> pets = response.as(new TypeRef<>() {
        });
        for (Pet pet : pets) {
            String actualStatus = pet.getStatus();
            assertEquals(status, actualStatus);
        }

        assertEquals(SC_OK, statusCode);
    }

    @Test
    public void getPetListByInvalidStatus() {
        Response response = petService.findByStatus("invalid status");
        int statusCode = response.statusCode();

        assertEquals(SC_BAD_REQUEST, statusCode);

    }

}
