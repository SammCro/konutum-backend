package com.ekip3.konutum.Repositories;

import com.ekip3.konutum.Entities.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseRepository  extends JpaRepository<House, Long> {
    House findByHouseName(String name);
}
