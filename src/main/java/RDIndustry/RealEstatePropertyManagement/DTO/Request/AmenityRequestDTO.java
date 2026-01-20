package RDIndustry.RealEstatePropertyManagement.DTO.Request;

import jakarta.validation.constraints.NotBlank;

public class AmenityRequestDTO {
    @NotBlank(message = "Amenity-Name Required")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
