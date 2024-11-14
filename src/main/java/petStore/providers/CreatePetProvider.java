package petStore.providers;

import petStore.dto.CreatePetDto;

public class CreatePetProvider {
    public static CreatePetDto createPet(String name, String status) {
        return CreatePetDto.builder()
                .name(name)
                .status(status)
                .build();
    }
}
