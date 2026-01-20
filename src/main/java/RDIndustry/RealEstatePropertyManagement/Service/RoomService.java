package RDIndustry.RealEstatePropertyManagement.Service;

import RDIndustry.RealEstatePropertyManagement.CustomException.DuplicateRoomException;
import RDIndustry.RealEstatePropertyManagement.DTO.Response.HouseInfoResponseDTO;
import RDIndustry.RealEstatePropertyManagement.DTO.Wrapper.RoomWrapper;
import RDIndustry.RealEstatePropertyManagement.Model.House;
import RDIndustry.RealEstatePropertyManagement.Model.Room;
import RDIndustry.RealEstatePropertyManagement.Repository.HouseRepo;
import RDIndustry.RealEstatePropertyManagement.Repository.OwnerRepo;
import RDIndustry.RealEstatePropertyManagement.Repository.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoomService {

    @Autowired
    private RoomRepo roomRepo;

    @Autowired
    private HouseRepo houseRepo;

    @Autowired
    private OwnerRepo ownerRepo;

    @Transactional
    public HouseInfoResponseDTO addRoom(Integer ownerId, Integer houseId, RoomWrapper roomData){
        House house = houseRepo.findById(houseId)
                .orElseThrow(()->new RuntimeException("House Not Found"));
        if(!ownerRepo.existsById(ownerId)){
            throw new RuntimeException("Owner Not Found");
        }
        if(house.getOwner().getId() != ownerId){
            throw new RuntimeException("This Is Not Your House\nYou Can't Add Room");
        }
        // DUPLICATE ROOM CHECK
        if (roomRepo.existsByHouseIdAndRoomNo(houseId, roomData.getRoomNo())) {
            throw new DuplicateRoomException(
                    "Room No " + roomData.getRoomNo() + " already exists in this house");
        }

        // Add New Room
        Room room = new Room(roomData);
        room.setHouse(house);
        house.getRooms().add(room);
        House h = houseRepo.save(house);

        //set House Response
        return new HouseInfoResponseDTO(h);
    }
    @Transactional
    public HouseInfoResponseDTO addRoom(Integer ownerId, Integer houseId, List<RoomWrapper> roomsData){
        House house = houseRepo.findById(houseId)
                .orElseThrow(()->new RuntimeException("House Not Found"));
        if(!ownerRepo.existsById(ownerId)){
            throw new RuntimeException("Owner Not Found");
        }
        if(house.getOwner().getId() != ownerId){
            throw new RuntimeException("This Is Not Your House\nYou Can't Add Room");
        }

        //Duplicate Room Check
        Set<Integer> existingRoomNos =
                new HashSet<>(roomRepo.findRoomNosByHouseId(houseId));

        for (RoomWrapper room : roomsData) {
            if (existingRoomNos.contains(room.getRoomNo())) {
                throw new DuplicateRoomException(
                        "Room No " + room.getRoomNo() + " already exists in this house");
            }
        }
        //Add New Rooms
        for(RoomWrapper rw : roomsData){
            Room room = new Room(rw);
            room.setHouse(house);
            house.getRooms().add(room);
        }
        House h = houseRepo.save(house);

        //set House Response
        return new HouseInfoResponseDTO(h);
    }

    @Transactional
    public List<RoomWrapper> getHouseRooms(Integer ownerId,Integer houseId){

        House house = houseRepo.findById(houseId).
                orElseThrow(()->new RuntimeException("House Not Found"));

        if(!ownerId.equals(house.getOwner().getId())){
            throw new RuntimeException("This is not your house");
        }

        List<RoomWrapper> roomList = new ArrayList<>();
        for(Room room : house.getRooms()){
            RoomWrapper rw = new RoomWrapper();
            rw.setRoomNo(room.getRoomNo());
            roomList.add(rw);
        }
        return roomList;
    }

    @Transactional
    public String deleteRoom(Integer ownerId,Integer houseId,Integer roomNo){
        House house = houseRepo.findById(houseId).
                orElseThrow(()->new RuntimeException("House Not Found"));

        if(!ownerId.equals(house.getOwner().getId())){
            throw new RuntimeException("This Is Not Your House");
        }
        Room room = roomRepo.findByHouseIdAndRoomNo(houseId, roomNo)
                .orElseThrow(() -> new RuntimeException("Room Not Found"));

        house.getRooms().remove(room);
        roomRepo.delete(room);

        houseRepo.save(house);
        return "Room Successfully Deleted With Room.No:- "+roomNo;
    }

}
