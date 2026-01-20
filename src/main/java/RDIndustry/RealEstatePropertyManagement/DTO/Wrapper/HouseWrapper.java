package RDIndustry.RealEstatePropertyManagement.DTO.Wrapper;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.util.List;

public class HouseWrapper {

    @NotBlank(message = "House Location Required")
    private String location;

    @NotNull(message = "HouseSalePrice Required")
    @Positive(message = "Invalid SalePrice")
    @Min(value = 100000,message = "SalePrice Must Be GraterThan 100000 Or More")
    private Long salePrice;

    @Valid
    @NotEmpty(message = "House Rooms Required")
    private List<RoomWrapper> roomsData;

    //getter/setter

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Long salePrice) {
        this.salePrice = salePrice;
    }

    public List<RoomWrapper> getRoomsData() {
        return roomsData;
    }

    public void setRoomsData(List<RoomWrapper> roomsData) {
        this.roomsData = roomsData;
    }
}
