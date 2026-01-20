package RDIndustry.RealEstatePropertyManagement.DTO.Response;

import RDIndustry.RealEstatePropertyManagement.DTO.Wrapper.AddressWrapper;
import RDIndustry.RealEstatePropertyManagement.DTO.Wrapper.RoomWrapper;
import RDIndustry.RealEstatePropertyManagement.Model.Address;
import RDIndustry.RealEstatePropertyManagement.Model.House;
import RDIndustry.RealEstatePropertyManagement.Model.Room;

import java.util.ArrayList;
import java.util.List;

public class HouseInfoResponseDTO {
    private Integer houseId;
    private String houseLocation;
    private Long salePrice;
    private String houseOwner;
    private List<RoomWrapper> roomsInfo;
    private AddressWrapper addressInfo;

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

    public String getHouseOwner() {
        return houseOwner;
    }

    public void setHouseOwner(String houseOwner) {
        this.houseOwner = houseOwner;
    }

    public List<RoomWrapper> getRoomsInfo() {
        return roomsInfo;
    }

    public void setRoomsInfo(List<RoomWrapper> roomsInfo) {
        this.roomsInfo = roomsInfo;
    }

    public AddressWrapper getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(AddressWrapper addressInfo) {
        this.addressInfo = addressInfo;
    }

    public HouseInfoResponseDTO(House h) {
        houseId = h.getId();
        houseLocation = h.getLocation();
        salePrice = h.getSalePrice();
        houseOwner = h.getOwner().getName();
        roomsInfo = setHouseRooms(h.getRooms());
        addressInfo = setTempAddress(h.getAddress());
    }
    public List<RoomWrapper> setHouseRooms(List<Room> rooms){
        List<RoomWrapper> list = new ArrayList<>();
        for(Room room : rooms){
            RoomWrapper tempRoom = new RoomWrapper();
            tempRoom.setRoomNo(room.getRoomNo());
            list.add(tempRoom);
        }
        return list;
    }
    public AddressWrapper setTempAddress(Address address){
        AddressWrapper addressWrapper = new AddressWrapper();
        addressWrapper.setCity(address.getCity());
        addressWrapper.setPinCode(address.getPinCode());
        return addressWrapper;
    }
}
