package RDIndustry.RealEstatePropertyManagement.Controller;

import RDIndustry.RealEstatePropertyManagement.DTO.Request.AssignAmenitiesRequestDTO;
import RDIndustry.RealEstatePropertyManagement.DTO.Request.HouseRequestDTO;
import RDIndustry.RealEstatePropertyManagement.DTO.Response.HouseInfoResponseDTO;
import RDIndustry.RealEstatePropertyManagement.Service.HouseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/owner/house")
public class HouseController {

    @Autowired
    private HouseService houseService;

    @PostMapping("/add")
    public ResponseEntity<?> addHouse(@RequestBody @Valid HouseRequestDTO request){
        HouseInfoResponseDTO response = houseService.addHouse(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateHouse(
                                        @RequestParam
                                        Integer houseId,

                                        @RequestBody
                                        @Valid
                                        HouseRequestDTO request){
        return new ResponseEntity<>(houseService.updateHouse(houseId,request),HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteHouse(
                                        @RequestParam
                                        Integer ownerId,

                                        @RequestParam
                                        Integer houseId){
        return ResponseEntity.ok(houseService.deleteHouse(ownerId, houseId));
    }

    @GetMapping("/get-by-location")
    public ResponseEntity<?> getHousesByLocation(
                                                @RequestParam
                                                Integer ownerId,

                                                @RequestParam
                                                String location){
        return new ResponseEntity<>(houseService.getHousesByLocation(ownerId,location),HttpStatus.FOUND);
    }
    @GetMapping("/get-house")
    public ResponseEntity<?> getOwnerHouse(
            @RequestParam Integer ownerId,
            @RequestParam Integer houseId){

        return new ResponseEntity<>(houseService.getOwnerHouse(ownerId, houseId),HttpStatus.FOUND);
    }
    @GetMapping("/get-all-house/{ownerId}")
    public ResponseEntity<?> getOwnerHouses(@PathVariable Integer ownerId){
        return new ResponseEntity<>(houseService.getOwnerHouses(ownerId),HttpStatus.FOUND);
    }
    @PutMapping("/assign-amenities")
    public ResponseEntity<?> assignAmenitiesToHouse(
            @RequestParam Integer ownerId,
            @RequestParam Integer houseId,
            @RequestBody AssignAmenitiesRequestDTO request){

        return new ResponseEntity<>(houseService.assignAmenityToHouse(ownerId,houseId,request),HttpStatus.OK);
    }
    @GetMapping("/get/house/amenities")
    public ResponseEntity<?> getHouseAmenities(
            @RequestParam Integer ownerId,
            @RequestParam Integer houseId) {

        return new ResponseEntity<>(houseService.getHouseAmenities(ownerId, houseId),HttpStatus.FOUND);
    }
    @DeleteMapping("/delete/house/amenity")
    public ResponseEntity<?> removeAmenity(
            @RequestParam Integer ownerId,
            @RequestParam Integer houseId,
            @RequestParam Integer amenityId) {

        return new ResponseEntity<>(houseService.removeAmenity(ownerId, houseId, amenityId),HttpStatus.OK);
    }
}
