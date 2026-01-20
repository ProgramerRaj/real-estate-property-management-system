package RDIndustry.RealEstatePropertyManagement.DTO.Request;

import RDIndustry.RealEstatePropertyManagement.DTO.Wrapper.AddressWrapper;
import RDIndustry.RealEstatePropertyManagement.DTO.Wrapper.HouseWrapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class HouseRequestDTO {

    @NotNull(message = "OwnerId Required")
    @Positive(message = "Invalid OwnerId")
    private Integer ownerId;

    @Valid
    @NotNull(message = "House Data Required")
    private HouseWrapper houseData;

    @Valid
    @NotNull(message = "Address Data Required")
    private AddressWrapper addressData;

    //getter/setter
    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public HouseWrapper getHouseData() {
        return houseData;
    }

    public void setHouseData(HouseWrapper houseData) {
        this.houseData = houseData;
    }

    public AddressWrapper getAddressData() {
        return addressData;
    }

    public void setAddressData(AddressWrapper addressData) {
        this.addressData = addressData;
    }
}
