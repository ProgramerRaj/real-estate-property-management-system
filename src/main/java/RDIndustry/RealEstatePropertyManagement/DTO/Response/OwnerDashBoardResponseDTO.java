package RDIndustry.RealEstatePropertyManagement.DTO.Response;

public class OwnerDashBoardResponseDTO {
    private Integer totalHouse;
    private Integer totalRooms;
    private Integer amenities;
    private Double avgSalePrice;

    public Integer getTotalHouse() {
        return totalHouse;
    }

    public void setTotalHouse(Integer totalHouse) {
        this.totalHouse = totalHouse;
    }

    public Integer getTotalRooms() {
        return totalRooms;
    }

    public void setTotalRooms(Integer totalRooms) {
        this.totalRooms = totalRooms;
    }

    public Integer getAmenities() {
        return amenities;
    }

    public void setAmenities(Integer amenities) {
        this.amenities = amenities;
    }

    public Double getAvgSalePrice() {
        return avgSalePrice;
    }

    public void setAvgSalePrice(Double avgSalePrice) {
        this.avgSalePrice = avgSalePrice;
    }
}
