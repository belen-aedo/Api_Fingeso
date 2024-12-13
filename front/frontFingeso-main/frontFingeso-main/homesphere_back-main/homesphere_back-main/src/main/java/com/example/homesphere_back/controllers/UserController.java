package com.example.homesphere_back.controllers;

import com.example.homesphere_back.models.Property;
import com.example.homesphere_back.models.Users;
import com.example.homesphere_back.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Boolean> register(@RequestBody Users user) {
        boolean isSaved = userService.saveUser(user);
        return ResponseEntity.ok(isSaved);
    }

    @GetMapping("/obtainAll")
    public ResponseEntity<?> obtainAll(){
        return ResponseEntity.ok().body(userService.obtainAll());
    }

    @PostMapping("/saveProperty/{id}")
    public ResponseEntity<Boolean> saveProperty(@RequestBody Property property, @PathVariable Long id) {
        return ResponseEntity.ok().body(userService.saveProperty(property, id));
    }

    @GetMapping("/validateRut/{rut}")
    public ResponseEntity<Boolean> validateRut(@PathVariable String rut){
        return ResponseEntity.ok().body(userService.validateRut(rut));
    }

    @GetMapping("/login/{email}/{password}")
    public ResponseEntity<Boolean> login(@PathVariable String email, @PathVariable String password) {
        boolean loginSuccessful = userService.login(email, password);
        return ResponseEntity.ok(loginSuccessful);
    }

    @GetMapping("/findByEmail/{email}")
    public ResponseEntity<Users> findByEmail(@PathVariable String email){
        return ResponseEntity.ok().body(userService.findByEmail(email));
    }

    @GetMapping("/findById/{email}")
    public ResponseEntity<Long> findById(@PathVariable String email){
        return ResponseEntity.ok().body(userService.findByEmail(email).getId());
    }

    @GetMapping("/findbyID/{id}")
    public ResponseEntity<?> findbyID(@PathVariable Long id){
        return ResponseEntity.ok().body(userService.findById(id));
    }

    @PostMapping("/updateEmail/{id}/{email}")
    public ResponseEntity<Boolean> updateEmail(@PathVariable Long id, @PathVariable String email){
        return ResponseEntity.ok().body(userService.updateEmail(id, email));
    }

    @PostMapping("/updatePassword/{id}/{password}")
    public ResponseEntity<Boolean> updatePassword(@PathVariable Long id, @PathVariable String password){
        return ResponseEntity.ok().body(userService.updatePassword(id, password));
    }

    @PostMapping("/updateNumber/{id}/{number}")
    public ResponseEntity<Boolean> updateNumber(@PathVariable Long id, @PathVariable String number){
        return ResponseEntity.ok().body(userService.updateNumber(id, number));
    }

    @GetMapping("/getPropertiesbyUser/{id}")
    public ResponseEntity<?> getPropertiesbyUser(@PathVariable Long id){
        return ResponseEntity.ok().body(userService.getPropertiesByUser(id));
    }

    @PostMapping("/likeProperty/{idProp}/{idUser}")
    public ResponseEntity<Boolean> likeProperty(@PathVariable Long idProp,
                                                @PathVariable Long idUser){
        return ResponseEntity.ok().body(userService.likeProperty(idProp, idUser));
    }

    @PostMapping("/removeLike/{idProp}/{idUser}")
    public ResponseEntity<Boolean> removeLike(@PathVariable Long idProp,
                                                @PathVariable Long idUser){
        return ResponseEntity.ok().body(userService.removeLike(idProp, idUser));
    }

    @GetMapping("/showLikedProperties/{idUser}")
    public ResponseEntity<?> showLikedProperties(@PathVariable Long idUser){
        return ResponseEntity.ok().body(userService.showLikedProperties(idUser));
    }

    @GetMapping("/userOwnProperty/{idUser}/{idProp}")
    public ResponseEntity<Boolean> userOwnProperty(@PathVariable Long idUser,
                                                   @PathVariable Long idProp){
        return ResponseEntity.ok().body(userService.userOwnProperty(idUser, idProp));
    }

    @PostMapping("/addVisitUser/{id_user}/{id_property}/{date}")
    public ResponseEntity<Boolean> addVisitUser(@PathVariable Long id_user, @PathVariable Long id_property, @PathVariable String date) {
        return ResponseEntity.ok().body(userService.addVisitUser(id_user, id_property, date));
    }

    @GetMapping("/showAllVisitsUser/{id}")
    public ResponseEntity<?> showAllVisitsUser(@PathVariable Long id){
        return ResponseEntity.ok().body(userService.showAllVisitsUser(id));
    }
}