package coupon.management.coupon.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    
    @Autowired
    UserRepo userRepo;

    @GetMapping("/user")
    public List<UserEntity> index() {
        return userRepo.findAll();
    }

    /* @GetMapping("/user/${userId}")
    public UserEntity getUser(@PathVariable("userId") Integer userId) {
        return userRepo.findById(userId).get();
    } */

    @PostMapping("/user")
    public String createUser(@RequestParam String name, @RequestParam String email) {
        UserEntity user = new UserEntity(name, email);
        userRepo.save(user);
        return "saved";
    }

    @PutMapping("/user/{userId}")
    public String updateUser(
        @PathVariable("userId") Integer userId,
        @RequestParam String name,
        @RequestParam String email
    ) {
        UserEntity user = userRepo.findById(userId).get();
        user.setName(name);
        user.setEmail(email);
        userRepo.save(user);
        return "saved";
    }

    @DeleteMapping("/user/{userId}")
    public String deleteUser(@PathVariable("userId") Integer userId) {
        userRepo.deleteById(userId);
        return "deleted";
    }
}
