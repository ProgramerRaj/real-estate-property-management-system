package RDIndustry.RealEstatePropertyManagement.Service;

import RDIndustry.RealEstatePropertyManagement.DTO.Response.AvgPricePerLocationResponseDTO;
import RDIndustry.RealEstatePropertyManagement.DTO.Response.CountHouseLocResDTO;
import RDIndustry.RealEstatePropertyManagement.DTO.Response.OwnerDashBoardResponseDTO;
import RDIndustry.RealEstatePropertyManagement.DTO.Wrapper.CountHousesDTO;
import RDIndustry.RealEstatePropertyManagement.DTO.Wrapper.CountOwnersDTO;
import RDIndustry.RealEstatePropertyManagement.DTO.Response.OwnerResponseDTO;
import RDIndustry.RealEstatePropertyManagement.DTO.Request.OwnerRequestDTO;
import RDIndustry.RealEstatePropertyManagement.Model.House;
import RDIndustry.RealEstatePropertyManagement.Model.Owner;
import RDIndustry.RealEstatePropertyManagement.Repository.HouseRepo;
import RDIndustry.RealEstatePropertyManagement.Repository.OwnerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OwnerService {

    @Autowired
    private OwnerRepo ownerRepo;

    @Autowired
    private HouseRepo houseRepo;

    @Transactional
    public int createOwner(OwnerRequestDTO request) {

        if (ownerRepo.existsByPhoneNo(request.getPhoneNo())) {
            throw new RuntimeException("Owner Already Exists");
        }
        Owner owner = new Owner();
        owner.setName(request.getName());
        owner.setPhoneNo(request.getPhoneNo());
        owner = ownerRepo.save(owner);
        return owner.getId();
    }

    @Transactional
    public String deleteOwner(Integer ownerId){
        if(!ownerRepo.existsById(ownerId)){
            throw new RuntimeException("Sorry, Owner Not Found");
        }
        ownerRepo.deleteById(ownerId);
        return "Owner Successfully Deleted With Id:- "+ownerId;
    }

    @Transactional
    public List<OwnerResponseDTO> getAllOwners(){
        List<Owner> owners = ownerRepo.findAll();

        if(owners.isEmpty()){
            throw new RuntimeException("Owner's Not Found");
        }

        List<OwnerResponseDTO> dtoList = new ArrayList<>();
        for(Owner owner : owners){
            OwnerResponseDTO dto = new OwnerResponseDTO(owner);
            dtoList.add(dto);
        }
        return dtoList;
    }
    public OwnerResponseDTO getOwner(Integer ownerId){
        Owner owner = ownerRepo.findById(ownerId)
                .orElseThrow(()-> new RuntimeException("Owner Not Found"));

        return new OwnerResponseDTO(owner);
    }

    @Transactional
    public List<AvgPricePerLocationResponseDTO> avgPriceEachLocation(){

        List<AvgPricePerLocationResponseDTO> list = houseRepo.getAvgPerLocation();

        if(list.isEmpty()){
            throw new RuntimeException("House Or Location Not Found");
        }
        return list;
    }
    @Transactional
    public List<CountHouseLocResDTO> countHousePerLocation(){
        List<CountHouseLocResDTO> list = houseRepo.countHousePerLocation();

        if(list.isEmpty()){
            throw new RuntimeException("House Or Location Not Found");
        }
        return list;
    }
    @Transactional
    public CountHouseLocResDTO countHouseBYLocation(String location){
        if(!houseRepo.existsByLocation(location)){
            throw new RuntimeException("Location Not Found");
        }
        return houseRepo.countHouseByLocation(location);
    }
    @Transactional
    public CountOwnersDTO countOwners(){
       Long totalCount = ownerRepo.count();
       CountOwnersDTO response = new CountOwnersDTO();
       response.setTotalOwners(totalCount);
       return response;
    }
    @Transactional
    public CountHousesDTO countAllHouse(){
        Long totalCount = houseRepo.count();
        CountHousesDTO response = new CountHousesDTO();
        response.setTotalHouses(totalCount);
        return response;
    }
    @Transactional
    public OwnerDashBoardResponseDTO getOwnerDashboard(Integer ownerId) {

        Owner owner = ownerRepo.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Owner Not Found"));

        int totalHouses = owner.getHouses().size();
        int totalRooms = 0;
        int totalAmenities = 0;
        long totalSalePrice = 0;

        for (House house : owner.getHouses()) {
            totalRooms += house.getRooms().size();
            totalSalePrice += house.getSalePrice();
            totalAmenities += house.getAmenities().size();
        }

        double avgSalePrice =  (double)(totalSalePrice / totalHouses);

        OwnerDashBoardResponseDTO response = new OwnerDashBoardResponseDTO();
        response.setAmenities(totalAmenities);
        response.setTotalHouse(totalHouses);
        response.setAvgSalePrice(avgSalePrice);
        response.setTotalRooms(totalRooms);
        return response;
    }
}
