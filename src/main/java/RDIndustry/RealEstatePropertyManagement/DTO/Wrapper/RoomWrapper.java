package RDIndustry.RealEstatePropertyManagement.DTO.Wrapper;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class RoomWrapper {

    @NotNull(message = "House RoomNo required")
    @Positive(message = "RoomNo Must Be 0 Or more")
    private Integer roomNo;

    //getter/setter

    public Integer getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(Integer roomNo) {
        this.roomNo = roomNo;
    }
}
