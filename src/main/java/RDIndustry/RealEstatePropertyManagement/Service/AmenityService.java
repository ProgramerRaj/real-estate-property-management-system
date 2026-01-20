package RDIndustry.RealEstatePropertyManagement.Service;

import RDIndustry.RealEstatePropertyManagement.DTO.Request.AmenityRequestDTO;
import RDIndustry.RealEstatePropertyManagement.DTO.Response.AmenityResponseDTO;
import RDIndustry.RealEstatePropertyManagement.Model.Amenity;
import RDIndustry.RealEstatePropertyManagement.Repository.AmenityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AmenityService {

    @Autowired
    private AmenityRepo amenityRepo;

    public AmenityResponseDTO createAmenity(AmenityRequestDTO dto){

        if(amenityRepo.existsByName(dto.getName())){
            throw new RuntimeException("Amenity Already Exists");
        }
        Amenity amenity = new Amenity();
        amenity.setName(dto.getName());

        Amenity a = amenityRepo.save(amenity);
        return new AmenityResponseDTO(a);
    }
    @Transactional
    public List<AmenityResponseDTO> getAllAmenities() {

        List<Amenity> list = amenityRepo.findAll();
        if(list.isEmpty()){
            throw new RuntimeException("No Amenities Found");
        }

        List<AmenityResponseDTO> dtoList = new ArrayList<>();

        for (Amenity a : list) {
            AmenityResponseDTO dto = new AmenityResponseDTO(a);
            dtoList.add(dto);
        }
        return dtoList;
    }

    public String updateAmenity(Integer amenityId, AmenityRequestDTO dto) {

        Amenity amenity = amenityRepo.findById(amenityId)
                .orElseThrow(() -> new RuntimeException("Amenity not found"));

        amenity.setName(dto.getName());
        amenityRepo.save(amenity);

        return "Amenity updated successfully";
    }

    public String deleteAmenity(Integer amenityId) {

        Amenity amenity = amenityRepo.findById(amenityId)
                .orElseThrow(() -> new RuntimeException("Amenity not found"));

        amenityRepo.delete(amenity);
        return "Amenity deleted successfully";
    }
}
