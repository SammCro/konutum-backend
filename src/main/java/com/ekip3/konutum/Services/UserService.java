package com.ekip3.konutum.Services;

import com.ekip3.konutum.Entities.House;
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

    @Autowired
    private HouseService houseService;

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

    public void deleteUser(String email){
        userRepo.deleteByEmail(email);
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

    public List<House> getFavoriteHouses(Long id){
        String[] favoriteHouses = userRepo.findById(id).get().getIdOfFavoriteHouses().split(",");
        List<House> favoriteHousesList = new ArrayList<>();
        for (String favoriteHouse : favoriteHouses) {
            favoriteHousesList.add(houseService.findByHouseId(Long.parseLong(favoriteHouse)));
        }
        return favoriteHousesList;
    }

    public String getUserType(String email, String password){
        return userRepo.findUserType(email, password);
    }

}
