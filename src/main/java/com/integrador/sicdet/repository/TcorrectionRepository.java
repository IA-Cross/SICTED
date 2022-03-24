package com.integrador.sicdet.repository;
import com.integrador.sicdet.entity.Tcorrection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TcorrectionRepository extends JpaRepository<Tcorrection,Integer>{
    List<Tcorrection> findByIdTesis(@Param("idTesis") int idTesis, Pageable pageable);
}
