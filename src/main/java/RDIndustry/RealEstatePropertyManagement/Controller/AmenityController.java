package RDIndustry.RealEstatePropertyManagement.Controller;

import RDIndustry.RealEstatePropertyManagement.DTO.Request.AmenityRequestDTO;
import RDIndustry.RealEstatePropertyManagement.Service.AmenityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/house-amenity")
public class AmenityController {

    @Autowired
    private AmenityService amenityService;

    @PostMapping("/create")
    public ResponseEntity createAmenity(
            @RequestBody @Valid AmenityRequestDTO request){
        return new ResponseEntity(amenityService.createAmenity(request), HttpStatus.CREATED);
    }
    @GetMapping("/get-all")
    public ResponseEntity<?> getAllAmenities(){
        return new ResponseEntity<>(amenityService.getAllAmenities(),HttpStatus.FOUND);
    }
    @PutMapping("/update/{amenityId}")
    public ResponseEntity<String> updateAmenity(
            @PathVariable Integer amenityId,
            @RequestBody @Valid AmenityRequestDTO dto) {
        return ResponseEntity.ok(amenityService.updateAmenity(amenityId, dto));
    }
    @DeleteMapping("/delete/{amenityId}")
    public ResponseEntity<String> deleteAmenity(
            @PathVariable Integer amenityId) {
        return ResponseEntity.ok(amenityService.deleteAmenity(amenityId));
    }
}
