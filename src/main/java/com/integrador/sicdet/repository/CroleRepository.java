package com.integrador.sicdet.repository;
import com.integrador.sicdet.entity.Crole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CroleRepository extends JpaRepository<Crole,Integer>{
}
