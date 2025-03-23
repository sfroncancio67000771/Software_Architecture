package com.example.demo.services;

import com.example.demo.entities.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserService
{
     @Autowired
    private UserRepository userRepository;

    public void updateUser(String emailUser, User updateUser){
        Optional<User> userExist = userRepository.findByEmail(emailUser);

        if (userExist.isEmpty()){
            throw new RuntimeException("User not found");
        }

       User userExisting = userExist.get();


        if (updateUser.getName() != null){
            userExisting.setName(updateUser.getName());
        }
        if (updateUser.getEmail() != null){
            userExisting.setEmail(updateUser.getEmail());
        }
        if (updateUser.getProfession()!=null){
            userExisting.setProfession(updateUser.getProfession());
        }
        if (updateUser.getCountry()!=null){
            userExisting.setCountry(updateUser.getCountry());
        }

         userRepository.save(userExisting);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

}
