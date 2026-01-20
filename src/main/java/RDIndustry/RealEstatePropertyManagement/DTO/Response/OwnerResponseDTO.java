package RDIndustry.RealEstatePropertyManagement.DTO.Response;

import RDIndustry.RealEstatePropertyManagement.Model.Owner;

public class OwnerResponseDTO {
    private Integer id;
    private String name;
    private String phone;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public OwnerResponseDTO(Owner owner) {
        id = owner.getId();
        name = owner.getName();
        phone = owner.getPhoneNo();
    }

    public OwnerResponseDTO() {
    }
}
