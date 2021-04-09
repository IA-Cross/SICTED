package com.integrador.sicdet.repository;
import com.integrador.sicdet.entity.Tuserrole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TuserroleRepository extends JpaRepository<Tuserrole,Integer>{
    //List<Tuserrole> findAllByIdUser(Integer id);
    //Boolean existsByIdRol(Integer idRol);
    //boolean existsByIdUser(Integer id);
    //List<Tuserrole>findByIdRol(int id);
    //Tuserrole findByIdUser(@Param("id") int id);
    //List<Tuserrole>findAllUserByNames(@Param("names") String names);
    //Tuserrole findByIdActive(int id);
}
