package com.ekip3.konutum.Controllers;

import com.ekip3.konutum.Entities.House;
import com.ekip3.konutum.Services.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@Controller
@RequestMapping("/house")
public class HouseController {
    @Autowired
    private HouseService houseService;

    @GetMapping("/getAllHouses")
    public ResponseEntity<?> getAllHouses(){
        return ResponseEntity.ok(houseService.showAllHouses());
    }

    @GetMapping("/getHouse/{id}")
    public ResponseEntity<?> getHouse(@PathVariable Long id){
        return ResponseEntity.ok(houseService.findByHouseId(id));
    }

    @DeleteMapping("/deleteHouse/{id}")
    public ResponseEntity<?> deleteHouse(@PathVariable Long id){
        return ResponseEntity.ok(houseService.deleteHouseById(id));
    }

    @PostMapping("/addHouse")
    public ResponseEntity<?> addHouse(@Valid @RequestBody House request){
        return ResponseEntity.ok(houseService.saveHouse(request));
    }
}
