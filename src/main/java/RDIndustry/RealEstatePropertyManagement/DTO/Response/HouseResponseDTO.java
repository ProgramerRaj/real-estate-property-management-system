package RDIndustry.RealEstatePropertyManagement.DTO.Response;

import RDIndustry.RealEstatePropertyManagement.DTO.Wrapper.AddressWrapper;
import RDIndustry.RealEstatePropertyManagement.DTO.Wrapper.RoomWrapper;
import RDIndustry.RealEstatePropertyManagement.Model.Address;
import RDIndustry.RealEstatePropertyManagement.Model.House;
import RDIndustry.RealEstatePropertyManagement.Model.Room;

import java.util.ArrayList;
import java.util.List;

public class HouseResponseDTO {
    private Integer houseId;
    private String houseLocation;
    private Long salePrice;
    private List<RoomWrapper> rooms;
    private AddressWrapper address;


    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public String getHouseLocation() {
        return houseLocation;
    }

    public void setHouseLocation(String houseLocation) {
        this.houseLocation = houseLocation;
    }

    public Long getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Long salePrice) {
        this.salePrice = salePrice;
    }

    public List<RoomWrapper> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomWrapper> rooms) {
        this.rooms = rooms;
    }

    public AddressWrapper getAddress() {
        return address;
    }

    public void setAddress(AddressWrapper address) {
        this.address = address;
    }

    public HouseResponseDTO(House h) {
        houseId = h.getId();
        houseLocation = h.getLocation();
        salePrice = h.getSalePrice();
        rooms = initRooms(h.getRooms());
        address = initAddress(h.getAddress());
    }
    public List<RoomWrapper> initRooms(List<Room> rooms){
        List<RoomWrapper> rwList = new ArrayList<>();
        for(Room room : rooms){
            RoomWrapper rw = new RoomWrapper();
            rw.setRoomNo(room.getRoomNo());
            rwList.add(rw);
        }
        return rwList;
    }
    public AddressWrapper initAddress(Address address){
        AddressWrapper aw = new AddressWrapper();
        aw.setCity(address.getCity());
        aw.setPinCode(address.getPinCode());
        return aw;
    }
}
