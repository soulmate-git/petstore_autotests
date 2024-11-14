package petstoretests;

import org.testng.annotations.Test;
import petStore.dto.CreatePetDto;
import petStore.providers.CreatePetProvider;
import petStore.services.PetServices;

public class DeletePetTest {
    PetServices petService = new PetServices();

    @Test
    public void deletePet() {
        var response = petService.createPet(CreatePetProvider.createPet("Rinna", "available"));
        response.then().statusCode(200);
        var pet = response.then().extract().as(CreatePetDto.class);
        petService.deletePet(String.valueOf(pet.getId())).then().statusCode(200);
    }

    @Test
    public void deletePetNotFound() {
        petService.deletePet(null).then().statusCode(404);
    }

    @Test
    public void deletePetInvalidIdSupplied() {
        petService.deletePet("1111111111111111111111111111111111111111111111111111111111");
    }
}
