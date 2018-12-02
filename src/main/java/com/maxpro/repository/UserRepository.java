package com.maxpro.repository;

import com.maxpro.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends MongoRepository<User, String> {

    User findByUsername(String userName);
   // void addUser(User user);

}
