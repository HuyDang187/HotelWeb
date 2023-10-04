package com.hotelky4.projecthotel.dao;

import com.hotelky4.projecthotel.entity.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthoritiesRepository extends JpaRepository<Authorities,Integer> {

}
