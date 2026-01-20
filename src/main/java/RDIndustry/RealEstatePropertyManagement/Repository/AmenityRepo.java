package RDIndustry.RealEstatePropertyManagement.Repository;

import RDIndustry.RealEstatePropertyManagement.Model.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmenityRepo extends JpaRepository<Amenity,Integer> {

    boolean existsByName(String name);
}
