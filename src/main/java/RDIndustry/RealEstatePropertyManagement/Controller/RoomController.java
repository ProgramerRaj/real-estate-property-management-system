package RDIndustry.RealEstatePropertyManagement.Controller;

import RDIndustry.RealEstatePropertyManagement.DTO.Wrapper.RoomWrapper;
import RDIndustry.RealEstatePropertyManagement.Service.RoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/house-rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping("/add-room")
    public ResponseEntity<?> addRoom(@RequestParam
                                     Integer ownerId,

                                     @RequestParam
                                     Integer houseId,

                                     @RequestBody
                                     @Valid
                                     RoomWrapper room){
        return new ResponseEntity<>(roomService.addRoom(ownerId, houseId, room), HttpStatus.OK);
    }

    @PostMapping("/add-rooms")
    public ResponseEntity<?> addRoom(@RequestParam
                                     Integer ownerId,

                                     @RequestParam
                                     Integer houseId,

                                     @RequestBody
                                     @Valid List<RoomWrapper> rooms){
        return new ResponseEntity<>(roomService.addRoom(ownerId,houseId,rooms),HttpStatus.OK);
    }

    @GetMapping("/get-rooms")
    public ResponseEntity<?> getHouseRooms(
            @RequestParam Integer ownerId,
            @RequestParam Integer houseId){
        return new ResponseEntity<>(roomService.getHouseRooms(ownerId, houseId),HttpStatus.FOUND);
    }

    @DeleteMapping("/delete-room")
    public ResponseEntity<?> deleteRoom(
            @RequestParam Integer ownerId,
            @RequestParam Integer houseId,
            @RequestParam Integer roomNo){
        return new ResponseEntity<>(roomService.deleteRoom(ownerId, houseId, roomNo),HttpStatus.OK);
    }
}
