package com.example.homesphere_back.repositories;

import com.example.homesphere_back.models.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    Optional<Property> findById(Long id);

    @Query("SELECT p FROM Property p")
    List<Property> obtainAll();

    @Query("SELECT p FROM Property p ORDER BY p.value ASC")
    List<Property> orderAsc();

    @Query("SELECT p FROM Property p ORDER BY p.value DESC")
    List<Property> orderDes();

    @Query("SELECT p FROM Property p WHERE p.size < :size")
    List<Property> lessThanASize(int size);

    @Query("SELECT p FROM Property p WHERE p.size > :size")
    List<Property> greaterThanASize(int size);

    @Query("SELECT p FROM Property p WHERE p.value < :value")
    List<Property> lessThanAValue(int value);

    @Query("SELECT p FROM Property p WHERE p.value > :value")
    List<Property> greaterThanAValue(int value);

    List<Property> findByNeighboorhood(String neighboorhood);
    List<Property> findByPropertyType(String region);
    List<Property> findByPublicationType(String type);
    List<Property> findByBathrooms(int bathrooms);
    List<Property> findByBedrooms(int bedrooms);


}