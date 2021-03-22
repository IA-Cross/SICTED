package com.integrador.sicdet.repository;
import com.integrador.sicdet.entity.Tuser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TuserRepository extends JpaRepository<Tuser,Integer>{
}
