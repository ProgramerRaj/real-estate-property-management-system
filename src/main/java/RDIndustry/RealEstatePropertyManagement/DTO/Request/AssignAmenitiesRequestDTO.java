package RDIndustry.RealEstatePropertyManagement.DTO.Request;

import java.util.List;

public class AssignAmenitiesRequestDTO {
    private List<Integer> amenityIds;

    public List<Integer> getAmenityIds() {
        return amenityIds;
    }

    public void setAmenityIds(List<Integer> amenityIds) {
        this.amenityIds = amenityIds;
    }
}
