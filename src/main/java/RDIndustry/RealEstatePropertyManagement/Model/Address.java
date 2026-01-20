package RDIndustry.RealEstatePropertyManagement.Model;

import RDIndustry.RealEstatePropertyManagement.DTO.Wrapper.AddressWrapper;
import jakarta.persistence.*;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String city;

    @Column(name = "pincode")
    private String pinCode;

    @OneToOne
    @JoinColumn(name = "house-id",unique = true)
    private House house;

    //getter/setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public Address(AddressWrapper addressData) {
        city = addressData.getCity();
        pinCode = addressData.getPinCode();
    }

    public Address() {
    }
}
