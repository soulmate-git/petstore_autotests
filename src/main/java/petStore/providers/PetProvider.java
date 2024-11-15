package petStore.providers;

import petStore.dto.Pet;

public class PetProvider {
    public static Pet createPet(String name, String status) {
        return Pet.builder()
                .name(name)
                .status(status)
                .build();
    }

    public static Pet createPet(Long id, String name, String status) {
        return Pet.builder()
                .id(id)
                .name(name)
                .status(status)
                .build();
    }
}
