package com.example.homesphere_back.services;

import com.example.homesphere_back.models.Property;
import com.example.homesphere_back.repositories.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {
    @Autowired
    PropertyRepository propertyRepository;

    public boolean saveProperty(Property property){
        propertyRepository.save(property);
        return true;
    }

    public Optional<Property> findById(Long id){return propertyRepository.findById(id);}
    public List<Property> obtainAll(){return propertyRepository.obtainAll();}
    public List<Property> orderAsc(){return propertyRepository.orderAsc();}
    public List<Property> orderDes(){return propertyRepository.orderDes();}
    public List<Property> lessThanASize(int size){return propertyRepository.lessThanASize(size);}
    public List<Property> greaterThanASize(int size){return propertyRepository.greaterThanASize(size);}
    public List<Property> lessThanAValue(int value){return propertyRepository.lessThanAValue(value);}
    public List<Property> greaterThanAValue(int value){return propertyRepository.greaterThanAValue(value);}
    public List<Property> findByNeighboorhood(String neighboorhood){return propertyRepository.findByNeighboorhood(neighboorhood);}
    public List<Property> findByPropertyType(String region){return propertyRepository.findByPropertyType(region);}
    public List<Property> findByPublicationType(String type){return propertyRepository.findByPublicationType(type);}
    public List<Property> findByBathrooms(int bathrooms){return propertyRepository.findByBathrooms(bathrooms);}
    public List<Property> findByBedrooms(int bedrooms){return propertyRepository.findByBedrooms(bedrooms);}

    public boolean updateNeighboorhood(Long id, String neighboorhood){
        // busca el usuario, si no lo encuentra retorna false
        if(!propertyRepository.findById(id).isPresent()){
            return false;
        }
        Property property = propertyRepository.findById(id).get();
        property.setNeighboorhood(neighboorhood);
        propertyRepository.save(property);
        return true;
    }

    public boolean updatePropertyType(Long id, String propertyType){
        // busca el usuario, si no lo encuentra retorna false
        if(!propertyRepository.findById(id).isPresent()){
            return false;
        }
        Property property = propertyRepository.findById(id).get();
        property.setPropertyType(propertyType);
        propertyRepository.save(property);
        return true;
    }

    public boolean updateSize(Long id, int size){
        // busca el usuario, si no lo encuentra retorna false
        if(!propertyRepository.findById(id).isPresent()){
            return false;
        }
        Property property = propertyRepository.findById(id).get();
        property.setSize(size);
        propertyRepository.save(property);
        return true;
    }

    public boolean updateBedrooms(Long id, int bedrooms){
        // busca el usuario, si no lo encuentra retorna false
        if(!propertyRepository.findById(id).isPresent()){
            return false;
        }
        Property property = propertyRepository.findById(id).get();
        property.setBedrooms(bedrooms);
        propertyRepository.save(property);
        return true;
    }

    public boolean updateBathrooms(Long id, int bathrooms){
        // busca el usuario, si no lo encuentra retorna false
        if(!propertyRepository.findById(id).isPresent()){
            return false;
        }
        Property property = propertyRepository.findById(id).get();
        property.setBathrooms(bathrooms);
        propertyRepository.save(property);
        return true;
    }

    public boolean updatePrice(Long id, int price){
        // busca el usuario, si no lo encuentra retorna false
        if(!propertyRepository.findById(id).isPresent()){
            return false;
        }
        Property property = propertyRepository.findById(id).get();
        property.setValue(price);
        propertyRepository.save(property);
        return true;
    }

    public boolean updateDescription(Long id, String description){
        // busca el usuario, si no lo encuentra retorna false
        if(!propertyRepository.findById(id).isPresent()){
            return false;
        }
        Property property = propertyRepository.findById(id).get();
        property.setDescription(description);
        propertyRepository.save(property);
        return true;
    }

}
