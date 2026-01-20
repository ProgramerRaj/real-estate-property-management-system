package RDIndustry.RealEstatePropertyManagement.Repository;

import RDIndustry.RealEstatePropertyManagement.Model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepo extends JpaRepository<Address,Integer> {
}
