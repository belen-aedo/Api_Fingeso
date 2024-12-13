package com.example.homesphere_back.controllers;

import com.example.homesphere_back.models.Property;
import com.example.homesphere_back.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/property")
public class PropertyController {
    @Autowired
    private PropertyService propertyService;

    @PostMapping("/saveProperty")
    public ResponseEntity<Boolean> create(@RequestBody Property property) {
        boolean isSaved = propertyService.saveProperty(property);
        return ResponseEntity.ok(isSaved);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return  ResponseEntity.ok().body(propertyService.findById(id));
    }

    @GetMapping("/obtainAll")
    public ResponseEntity<?> obtainAll(){
        return ResponseEntity.ok().body(propertyService.obtainAll());
    }

    @GetMapping("/orderAsc")
    public ResponseEntity<?> orderAsc(){
        return ResponseEntity.ok().body(propertyService.orderAsc());
    }

    @GetMapping("/orderDes")
    public ResponseEntity<?> orderDes(){
        return ResponseEntity.ok().body(propertyService.orderDes());
    }

    @GetMapping("/lessThanASize/{size}")
    public ResponseEntity<?> lessThanASize(@PathVariable int size){
        return ResponseEntity.ok().body(propertyService.lessThanASize(size));
    }

    @GetMapping("/greaterThanASize/{size}")
    public ResponseEntity<?> greaterThanASize(@PathVariable int size){
        return ResponseEntity.ok().body(propertyService.greaterThanASize(size));
    }

    @GetMapping("/lessThanAValue/{value}")
    public ResponseEntity<?> lessThanAValue(@PathVariable int value){
        return ResponseEntity.ok().body(propertyService.lessThanAValue(value));
    }

    @GetMapping("/greaterThanAValue/{value}")
    public ResponseEntity<?> greaterThanAValue(@PathVariable int value){
        return ResponseEntity.ok().body(propertyService.greaterThanAValue(value));
    }

    @GetMapping("/findByNeighboorhood/{neighboorhood}")
    public ResponseEntity<?> findByNeighboorhood(@PathVariable String neighboorhood){
        return ResponseEntity.ok().body(propertyService.findByNeighboorhood(neighboorhood));
    }

    @GetMapping("/findByPropertyType/{propertyType}")
    public ResponseEntity<?> findByPropertyType(@PathVariable String propertyType){
        return ResponseEntity.ok().body(propertyService.findByPropertyType(propertyType));
    }

    @GetMapping("/findByPublicationType/{publicationType}")
    public ResponseEntity<?> findByPublicationType(@PathVariable String publicationType){
        return ResponseEntity.ok().body(propertyService.findByPublicationType(publicationType));
    }

    @GetMapping("/findByBathrooms/{bathrooms}")
    public ResponseEntity<?> findByBathrooms(@PathVariable int bathrooms){
        return ResponseEntity.ok().body(propertyService.findByBathrooms(bathrooms));
    }

    @GetMapping("/findByBedrooms/{bedrooms}")
    public ResponseEntity<?> findByBedrooms(@PathVariable int bedrooms){
        return ResponseEntity.ok().body(propertyService.findByBedrooms(bedrooms));
    }

    @PutMapping("/updateNeighboorhood/{id}/{neighboorhood}")
    public ResponseEntity<Boolean> updateNeighboorhood(@PathVariable Long id, @PathVariable String neighboorhood){
        return ResponseEntity.ok().body(propertyService.updateNeighboorhood(id, neighboorhood));
    }

    @PutMapping("/updatePropertyType/{id}/{propertyType}")
    public ResponseEntity<Boolean> updatePropertyType(@PathVariable Long id, @PathVariable String propertyType){
        return ResponseEntity.ok().body(propertyService.updatePropertyType(id, propertyType));
    }

    @PutMapping("/updateSize/{id}/{size}")
    public ResponseEntity<Boolean> updateSize(@PathVariable Long id, @PathVariable int size){
        return ResponseEntity.ok().body(propertyService.updateSize(id, size));
    }

    @PutMapping("/updateBedrooms/{id}/{bedrooms}")
    public ResponseEntity<Boolean> updateBedrooms(@PathVariable Long id, @PathVariable int bedrooms){
        return ResponseEntity.ok().body(propertyService.updateBedrooms(id, bedrooms));
    }

    @PutMapping("/updateBathrooms/{id}/{bathrooms}")
    public ResponseEntity<Boolean> updateBathrooms(@PathVariable Long id, @PathVariable int bathrooms){
        return ResponseEntity.ok().body(propertyService.updateBathrooms(id, bathrooms));
    }

    @PutMapping("/updatePrice/{id}/{price}")
    public ResponseEntity<Boolean> updatePrice(@PathVariable Long id, @PathVariable int price){
        return ResponseEntity.ok().body(propertyService.updatePrice(id, price));
    }

    @PutMapping("/updateDescription/{id}/{description}")
    public ResponseEntity<Boolean> updateDescription(@PathVariable Long id, @PathVariable String description){
        return ResponseEntity.ok().body(propertyService.updateDescription(id, description));
    }
}