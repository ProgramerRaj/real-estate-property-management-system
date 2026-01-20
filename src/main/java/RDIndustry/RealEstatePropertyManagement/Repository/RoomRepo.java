package RDIndustry.RealEstatePropertyManagement.Repository;

import RDIndustry.RealEstatePropertyManagement.Model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepo extends JpaRepository<Room,Integer> {

    void deleteByHouseId(int houseId);

    boolean existsByHouseIdAndRoomNo(Integer houseId, Integer roomNo);

    @Query("SELECT r.roomNo FROM Room r WHERE r.house.id = :houseId")
    List<Integer> findRoomNosByHouseId(Integer houseId);

    Room findByRoomNo(Integer roomNo);

    Optional<Room> findByHouseIdAndRoomNo(Integer houseId, Integer roomNo);
}
