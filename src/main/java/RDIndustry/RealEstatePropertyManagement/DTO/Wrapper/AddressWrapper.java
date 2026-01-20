package RDIndustry.RealEstatePropertyManagement.DTO.Wrapper;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class AddressWrapper {

    @NotBlank(message = "City Required")
    private String city;
    @Positive(message = "Invalid PinCode")
    @NotBlank(message = "PinCode Required")
    private String pinCode;

    //getter/setter

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }
}

