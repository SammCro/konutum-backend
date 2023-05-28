package com.ekip3.konutum.Services;

import com.ekip3.konutum.Entities.User;
import com.ekip3.konutum.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class  UserService {
    @Autowired
    private UserRepository userRepo;

    public void saveUser(User user){
        userRepo.save(user);
    }

    public User getUser(String email){
        return userRepo.findByEmail(email);
    }

    public User getUser(Long id){
        return userRepo.findById(id).orElse(null);
    }

    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    public void deleteUser(Long id){
        userRepo.deleteById(id);
    }

    public void deleteUser(User user){
        userRepo.delete(user);
    }

    public void updateUser(User user){
        userRepo.save(user);
    }

    public boolean isUserExist(String email, String password){
        return userRepo.existsByEmailAndPassword(email, password);
    }

    public List<Long> getFavoriteHouses(String email){
        // getIdOfFavoriteHouses() returns a string like "1,2,3,4,5"
        String[] favoriteHouses = userRepo.findByEmail(email).getIdOfFavoriteHouses().split(",");
        List<Long> favoriteHousesList = new ArrayList<>();
        for (String houseId : favoriteHouses) {
            favoriteHousesList.add(Long.parseLong(houseId));
        }
        return favoriteHousesList;
    }

}
