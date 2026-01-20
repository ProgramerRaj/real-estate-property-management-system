package RDIndustry.RealEstatePropertyManagement.Repository;

import RDIndustry.RealEstatePropertyManagement.Model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnerRepo extends JpaRepository<Owner,Integer> {

    boolean existsByPhoneNo(String phoneNo);


}
