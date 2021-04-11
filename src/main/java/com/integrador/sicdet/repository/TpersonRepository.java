package com.integrador.sicdet.repository;
import com.integrador.sicdet.entity.Tperson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TpersonRepository extends JpaRepository<Tperson,Integer>{
    List<Tperson> findPersonjTest();
    Tperson findByIdActive(int id);
}
