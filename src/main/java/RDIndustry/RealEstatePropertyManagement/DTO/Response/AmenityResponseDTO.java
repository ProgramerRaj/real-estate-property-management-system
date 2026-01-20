package RDIndustry.RealEstatePropertyManagement.DTO.Response;

import RDIndustry.RealEstatePropertyManagement.Model.Amenity;

public class AmenityResponseDTO {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AmenityResponseDTO(Amenity amenity) {
        id = amenity.getId();
        name = amenity.getName();
    }

    public AmenityResponseDTO() {
    }
}
