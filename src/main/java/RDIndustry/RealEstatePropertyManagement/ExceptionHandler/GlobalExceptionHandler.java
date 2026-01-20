package RDIndustry.RealEstatePropertyManagement.ExceptionHandler;

import RDIndustry.RealEstatePropertyManagement.CustomException.DuplicateRoomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRunTimeEx(RuntimeException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodeArgValidationEx(MethodArgumentNotValidException ex){
        HashMap<String,String> map = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(errors->
                map.put(errors.getField(), errors.getDefaultMessage()));

        return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateRoomException.class)
    public ResponseEntity<?> handleDuplicateRoomEx(DuplicateRoomException ex){

        return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }
}
