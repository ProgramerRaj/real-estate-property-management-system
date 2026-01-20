package RDIndustry.RealEstatePropertyManagement.CustomException;

public class DuplicateRoomException extends RuntimeException{
    public DuplicateRoomException(String message) {
        super(message);
    }
}
