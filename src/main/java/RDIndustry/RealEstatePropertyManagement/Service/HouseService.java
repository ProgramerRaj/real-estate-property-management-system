package RDIndustry.RealEstatePropertyManagement.Service;

import RDIndustry.RealEstatePropertyManagement.DTO.Request.AssignAmenitiesRequestDTO;
import RDIndustry.RealEstatePropertyManagement.DTO.Request.HouseRequestDTO;
import RDIndustry.RealEstatePropertyManagement.DTO.Response.AmenityResponseDTO;
import RDIndustry.RealEstatePropertyManagement.DTO.Response.HouseInfoResponseDTO;
import RDIndustry.RealEstatePropertyManagement.DTO.Response.HouseResponseDTO;
import RDIndustry.RealEstatePropertyManagement.DTO.Wrapper.RoomWrapper;
import RDIndustry.RealEstatePropertyManagement.Model.*;
import RDIndustry.RealEstatePropertyManagement.Repository.AmenityRepo;
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
public class HouseService {

    @Autowired
    private HouseRepo houseRepo;

    @Autowired
    private OwnerRepo ownerRepo;

    @Autowired
    private RoomRepo roomRepo;

    @Autowired
    private AmenityRepo amenityRepo;

    @Transactional
    public HouseInfoResponseDTO addHouse(HouseRequestDTO request){
        Owner owner = ownerRepo.findById(request.getOwnerId())
                .orElseThrow(()->new RuntimeException("Owner Not Found"));


        Address address = new Address(request.getAddressData());
        List<Room> list = new ArrayList<>();

        for(RoomWrapper r : request.getHouseData().getRoomsData()){
            Room room = new Room(r);
            list.add(room);
        }

        House house = new House();
        house.setLocation(request.getHouseData().getLocation());
        house.setSalePrice(request.getHouseData().getSalePrice());
        house.setAddress(address);
        house.setOwner(owner);
        house.setRooms(list);

        address.setHouse(house);
        for(Room room : list){
            room.setHouse(house);
        }
        house = houseRepo.save(house);

        return new HouseInfoResponseDTO(house);
    }

    @Transactional
    public HouseInfoResponseDTO updateHouse(Integer houseId, HouseRequestDTO request){

        House house = houseRepo.findById(houseId)
                .orElseThrow(()-> new RuntimeException("House Not Found"));

        Owner owner = ownerRepo.findById(request.getOwnerId())
                .orElseThrow(()-> new RuntimeException("Owner Not Found"));

        //if not match current owner and houseOwner
        if(house.getOwner().getId() != owner.getId()){
            throw new RuntimeException("This Is Not Your House\nPlease Enter Your HouseId");
        }

        // Update house fields
        house.setLocation(request.getHouseData().getLocation());
        house.setSalePrice(request.getHouseData().getSalePrice());

        // Update address
        Address address = house.getAddress();
        address.setCity(request.getAddressData().getCity());
        address.setPinCode(request.getAddressData().getPinCode());

        // Delete Old Rooms
        roomRepo.deleteByHouseId(houseId);

        // Add New Rooms
        List<Room> rooms = new ArrayList<>();
        for(RoomWrapper roomWrapper : request.getHouseData().getRoomsData()){
            Room room = new Room(roomWrapper);
            room.setHouse(house);
            rooms.add(room);
        }

        house.setRooms(rooms);
        //first child(address) to parent(house) set
        // after parent(house) to child(address) set
        address.setHouse(house);
        house.setAddress(address);

        house = houseRepo.save(house);

        return new HouseInfoResponseDTO(house);
    }

    public String deleteHouse(Integer ownerId,Integer houseId){

        if(!ownerRepo.existsById(ownerId)){
            throw new RuntimeException("Owner Not Found");
        }
        House house = houseRepo.findById(houseId).
                orElseThrow(()-> new RuntimeException("House Not Found"));

        if(house.getOwner().getId() != ownerId){
            throw new RuntimeException("Sorry, But This Is Not Your House\nYou Can't Delete It");
        }
        houseRepo.delete(house);
        return "House Successfully Deleted";
    }

    public List<HouseResponseDTO> getHousesByLocation(Integer ownerId, String location){
        if(!ownerRepo.existsById(ownerId)){
            throw new RuntimeException("Owner Not Found\nSo You can't See Houses");
        }

        List<House> houses = houseRepo.findByLocation(location);
        if(houses.isEmpty()){
            throw new RuntimeException("Not Found Any House At "+location);
        }
        List<HouseResponseDTO> houseDTOList = new ArrayList<>();
        for(House house : houses){
            HouseResponseDTO dto = new HouseResponseDTO(house);
            houseDTOList.add(dto);
        }
        return houseDTOList;
    }
    public HouseResponseDTO getOwnerHouse(Integer ownerId, Integer houseId){

        House house = houseRepo.findById(houseId)
                .orElseThrow(()->new RuntimeException("House Not Found"));

        if(!ownerId.equals(house.getOwner().getId())){
            throw new RuntimeException("This Is Not Your House");
        }

        HouseResponseDTO dto = new HouseResponseDTO(house);
        return dto;
    }

    @Transactional
    public List<HouseResponseDTO> getOwnerHouses(Integer id){
        Owner owner = ownerRepo.findById(id)
                .orElseThrow(()->new RuntimeException("Owner not found"));

        List<HouseResponseDTO> dtoList = new ArrayList<>();
        for(House house : owner.getHouses()){
            HouseResponseDTO dto = new HouseResponseDTO(house);
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Transactional
    public String assignAmenityToHouse(
            Integer ownerId,
            Integer houseId,
            AssignAmenitiesRequestDTO request){

        House house = houseRepo.findById(houseId).
                orElseThrow(()->new RuntimeException("House Not Found With Id:- "+houseId));

        if(!ownerId.equals(house.getOwner().getId())){
            throw new RuntimeException("This Is Not Your House");
        }
        // Remove duplicate IDs from request
        Set<Integer> uniqueIds = new HashSet<>(request.getAmenityIds());

        // Get existing amenity IDs of house
        Set<Integer> existingAmenityIds = new HashSet<>();
        for (Amenity a : house.getAmenities()) {
            existingAmenityIds.add(a.getId());
        }

        // Remove already assigned amenities
        uniqueIds.removeAll(existingAmenityIds);

        if (uniqueIds.isEmpty()) {
            throw new RuntimeException("All Amenities Already Exists In This House");
        }

        // Fetch new amenities
        List<Amenity> newAmenities = amenityRepo.findAllById(uniqueIds);

        if(newAmenities.isEmpty()){
            return "Some Amenities Are Not Found\nSo It's Not Added";
        }
        for (Amenity amenity : newAmenities) {
            house.getAmenities().add(amenity);     // house side
            amenity.getHouses().add(house);        // amenity side
        }

        houseRepo.save(house);
        return "Amenities Successfully Assigned In This House";
    }
    @Transactional
    public List<AmenityResponseDTO> getHouseAmenities(Integer ownerId, Integer houseId) {

        House house = houseRepo.findById(houseId)
                .orElseThrow(() -> new RuntimeException("House Not Found"));

        if (!ownerId.equals(house.getOwner().getId())) {
            throw new RuntimeException("This Is Not Your House");
        }

        if(house.getAmenities().isEmpty()){
            throw new RuntimeException("Amenities Not Found In This House");
        }

        List<AmenityResponseDTO> response = new ArrayList<>();
        for (Amenity amenity : house.getAmenities()) {
            AmenityResponseDTO dto = new AmenityResponseDTO(amenity);
            response.add(dto);
        }
        return response;
    }

    @Transactional
    public String removeAmenity(Integer ownerId, Integer houseId, Integer amenityId) {
        House house = houseRepo.findById(houseId)
                .orElseThrow(() -> new RuntimeException("House Not Found"));

        if (!ownerId.equals(house.getOwner().getId())) {
            throw new RuntimeException("This Is Not Your House");
        }

        Amenity amenity = amenityRepo.findById(amenityId)
                .orElseThrow(() -> new RuntimeException("Amenity Not Found"));

        if (!house.getAmenities().remove(amenity)) {
            throw new RuntimeException("Amenity Not Assigned To This House");
        }
        houseRepo.save(house);
        return "Amenity Removed Successfully";
    }
}
