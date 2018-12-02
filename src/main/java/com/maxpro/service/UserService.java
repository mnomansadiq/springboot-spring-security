package com.maxpro.service;

import com.maxpro.model.User;
import com.maxpro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String create(User user) {
        String message = "";
        try {
            userRepository.save(user);
            message = "Created successfully";
        } catch (Exception e) {
            message = "Unable to save";
            e.printStackTrace();
        }
        return message;
    }

    public List<User> retrieve() {
        return userRepository.findAll();
    }

    public String update(User user, String id) {
        String message = "";
        try {
            User userById = userRepository.findOne(id);
            if (userById != null || !userById.equals(null) || user.getId() != null && id.equals(user.getId())) {
                userRepository.save(user);
                message = "Updated successfully";
            } else {
                message = "Unable to updated";
            }
        } catch (Exception e) {
            message = "Unable to updated";
            e.printStackTrace();
        }
        return message;
    }

    public String delete(String id) {
        String message = "";
        try {
            User user = userRepository.findOne(id);
            userRepository.delete(user);
            message = "Deleted successfully";
        } catch (Exception e) {
            message = "Unable to delete";
            e.printStackTrace();
        }
        return message;
    }

}
