package RDIndustry.RealEstatePropertyManagement.Repository;

import RDIndustry.RealEstatePropertyManagement.DTO.Response.AvgPricePerLocationResponseDTO;
import RDIndustry.RealEstatePropertyManagement.DTO.Response.CountHouseLocResDTO;
import RDIndustry.RealEstatePropertyManagement.Model.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseRepo extends JpaRepository<House,Integer> {

    List<House> findByLocation(String location);

    @Query(value = "select location as location" +
            ",avg(sale_price) as averagePrice" +
            " from house group by location",nativeQuery = true)
    List<AvgPricePerLocationResponseDTO> getAvgPerLocation();

    @Query(value = "select location as location" +
            ",count(id) as totalHouse" +
            " from house group by location",nativeQuery = true)
    List<CountHouseLocResDTO> countHousePerLocation();

    @Query(value = "SELECT location as location,COUNT(id) as totalHouse FROM house WHERE location = :location",nativeQuery = true)
    CountHouseLocResDTO countHouseByLocation(String location);

    Boolean existsByLocation(String location);
}
