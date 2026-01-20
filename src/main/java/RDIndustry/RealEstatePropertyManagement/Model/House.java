package RDIndustry.RealEstatePropertyManagement.Model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "house")
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String location;

    @Column(name = "sale_price")
    private long salePrice;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Room> rooms = new ArrayList<>();

    @OneToOne(mappedBy = "house",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Address address;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable( name = "house_amenity",
            joinColumns = @JoinColumn(name = "house_id"),
    inverseJoinColumns = @JoinColumn(name = "amenity_id"))
    private List<Amenity> amenities = new ArrayList<>();


    //getter/setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(long salePrice) {
        this.salePrice = salePrice;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Amenity> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<Amenity> amenities) {
        this.amenities = amenities;
    }
}
