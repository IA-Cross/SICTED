package com.integrador.sicdet.repository;
import com.integrador.sicdet.entity.Tuserrole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TuserroleRepository extends JpaRepository<Tuserrole,Integer>{
}
