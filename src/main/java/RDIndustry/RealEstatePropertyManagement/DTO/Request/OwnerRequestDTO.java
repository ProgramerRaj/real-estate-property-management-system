package RDIndustry.RealEstatePropertyManagement.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class OwnerRequestDTO {

    @NotBlank(message = "Owner name required")
    private String name;

    @NotBlank(message = "Owner PhoneNo Required")
    @Pattern(regexp = "^[0-9]{10}$",
            message = "Phone number must be exactly 10 digits")
    private String phoneNo;

    public String getName() {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo( String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
