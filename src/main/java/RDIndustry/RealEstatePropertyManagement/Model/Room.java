package RDIndustry.RealEstatePropertyManagement.Model;

import RDIndustry.RealEstatePropertyManagement.DTO.Wrapper.RoomWrapper;
import jakarta.persistence.*;

@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "room_no")
    private int roomNo;

    @ManyToOne
    @JoinColumn(name = "house_id")
    private House house;

    //getter/setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public Room(RoomWrapper roomData) {
        roomNo = roomData.getRoomNo();
    }

    public Room() {
    }
}
