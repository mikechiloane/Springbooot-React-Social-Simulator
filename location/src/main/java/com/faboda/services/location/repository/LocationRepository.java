package com.faboda.services.location.repository;

import com.faboda.services.location.models.Location;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LocationRepository extends MongoRepository<Location, String> {

        @Query("{ 'username' : ?0 }")
        Location findLocationByUsername(String username);

        List<Location> findByUsernameNot(String username);

}
