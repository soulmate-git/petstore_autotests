package petstoretests;


import org.testng.annotations.Test;
import petStore.dto.CreatePetDto;
import petStore.providers.CreatePetProvider;
import petStore.services.PetServices;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

public class CreatePetTest {

    @Test
    public void createPetPositive() {
        PetServices petService = new PetServices();
        var response = petService.createPet(CreatePetProvider.createPet("Rinna", "available"));
        response.then().statusCode(200);
        var pet = response.then().extract().as(CreatePetDto.class);
        assertEquals("Rinna", pet.getName());
        assertEquals("available", pet.getStatus());
        assertNotNull(pet.getId());
    }

    @Test
    public void createPetNegative() {
        PetServices petService = new PetServices();
        petService.createPet(CreatePetProvider.createPet(null, null)).then().statusCode(405);
    }
}
