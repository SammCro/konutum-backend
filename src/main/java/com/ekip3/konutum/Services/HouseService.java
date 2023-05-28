package com.ekip3.konutum.Services;

import com.ekip3.konutum.Entities.House;
import com.ekip3.konutum.Repositories.HouseRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class HouseService {
    @Autowired
    private HouseRepository repo;

    public void saveMyHouse(House house){
        repo.save(house);
    }

    public Iterable<House> showAllHouses(){
        return repo.findAll();
    }

    public Iterable<House> deleteHouseById(Long id){
        repo.deleteById(id);
        return repo.findAll();
    }

    public House findByHouseId(Long id){
        return repo.findById(id).orElse(null);
    }

    public House findByHouseName(String name){
        return repo.findByHouseName(name);
    }
}
