package coupon.management.coupon.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import coupon.management.coupon.common.ResponseHandler;

import java.util.*;

@RestController
public class UserController {
    
    @Autowired
    UserRepo userRepo;

    @GetMapping("/user")
    public ResponseEntity<Object> index() {
        // Response data initialisation
        List<UserEntity> result = null;
        HttpStatus httpStatus = HttpStatus.OK;
        String message = "Users fetched successfully!";
        String status = "Success";

        try {
            result = userRepo.findAll();
        } catch (Exception e) {
            status = "Error";
            message = e.toString();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return ResponseHandler.generateResponse(message, status, result, httpStatus);
    }

    /* @GetMapping("/user/${userId}")
    public UserEntity getUser(@PathVariable("userId") Integer userId) {
        return userRepo.findById(userId).get();
    } */

    @PostMapping("/user")
    public ResponseEntity<Object> createUser(@RequestBody Map<String, String> payload) {
        // Response data initialisation
        UserEntity result = null;
        HttpStatus httpStatus = HttpStatus.OK;
        String message = "User created successfully!";
        String status = "Success";

        // Get required params from request body
        String name = payload.get("name");
        String email = payload.get("email");


        try {
            result = new UserEntity(name, email);
            userRepo.save(result);    
        } catch (Exception e) {
            status = "Error";
            message = e.toString();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        
        return ResponseHandler.generateResponse(message, status, result, httpStatus);
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<Object> updateUser(
        @PathVariable("userId") Integer userId,
        @RequestBody Map<String, String> payload
    ) {
        // Response data initialisation
        UserEntity result = null;
        HttpStatus httpStatus = HttpStatus.OK;
        String message = "User updated successfully!";
        String status = "Success";

        // Get required params from request body
        String name = payload.get("name");
        String email = payload.get("email");

        try {
            UserEntity user = userRepo.findById(userId).get();
            user.setName(name);
            user.setEmail(email);
            userRepo.save(user);
            result = user;
        } catch (Exception e) {
            status = "Error";
            message = e.toString();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        
        return ResponseHandler.generateResponse(message, status, result, httpStatus);
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable("userId") Integer userId) {
        // Response data initialisation
        HttpStatus httpStatus = HttpStatus.OK;
        String message = "User deleted successfully!";
        String status = "Success";

        try {
            userRepo.deleteById(userId);
        } catch (Exception e) {
            status = "Error";
            message = e.toString();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        
        return ResponseHandler.generateResponse(message, status, null, httpStatus);
    }
}
