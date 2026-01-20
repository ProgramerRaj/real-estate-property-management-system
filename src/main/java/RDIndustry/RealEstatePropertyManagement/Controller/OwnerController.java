package RDIndustry.RealEstatePropertyManagement.Controller;

import RDIndustry.RealEstatePropertyManagement.DTO.Request.OwnerRequestDTO;
import RDIndustry.RealEstatePropertyManagement.Service.OwnerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/owner")
@Validated
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @PostMapping("/create")
    public ResponseEntity<?> createOwner(@RequestBody @Valid OwnerRequestDTO request){
        int ownerId = ownerService.createOwner(request);

        String s = "Owner successfully added\nWith OwnerId: "+ownerId;
        return new ResponseEntity<>(s, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{ownerId}")
    public ResponseEntity<?> deleteOwner(@PathVariable Integer ownerId){
        return new ResponseEntity<>(ownerService.deleteOwner(ownerId),HttpStatus.OK);
    }

    @GetMapping("/get-all-owners")
    public ResponseEntity<?> getAllOwners(){
        return new ResponseEntity<>(ownerService.getAllOwners(),HttpStatus.FOUND);
    }

    @GetMapping("/get-owner/{ownerId}")
    public ResponseEntity<?> getOwner(@PathVariable Integer ownerId){
        return new ResponseEntity<>(ownerService.getOwner(ownerId),HttpStatus.FOUND);
    }

    @GetMapping("/avg-price/per-location")
    public ResponseEntity<?> avgPricePerLocation(){
        return new ResponseEntity<>(ownerService.avgPriceEachLocation(),HttpStatus.OK);
    }

    @GetMapping("/count-house/per-location")
    public ResponseEntity<?> countHousePerLocation(){
        return new ResponseEntity<>(ownerService.countHousePerLocation(),HttpStatus.OK);
    }
    @GetMapping("/count-house/by-location/{location}")
    public ResponseEntity<?> countHouseByLocation(
            @PathVariable String location){
        return new ResponseEntity<>(ownerService.countHouseBYLocation(location),HttpStatus.OK);
    }
    @GetMapping("/count-owners")
    public ResponseEntity<?> countOwners(){
        return new ResponseEntity<>(ownerService.countOwners(),HttpStatus.OK);
    }
    @GetMapping("/count-all-Houses")
    public ResponseEntity<?> countAllHouse(){
        return new ResponseEntity<>(ownerService.countAllHouse(),HttpStatus.OK);
    }
    @GetMapping("/dashboard/{ownerId}")
    public ResponseEntity<?> ownerDashboard(@PathVariable Integer ownerId) {
        return new ResponseEntity<>(ownerService.getOwnerDashboard(ownerId),HttpStatus.OK);
    }
}
