package com.integrador.sicdet.repository;
import com.integrador.sicdet.entity.Tuser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TuserRepository extends JpaRepository<Tuser,Integer>{
    List<Tuser> findUserPageOrder();

    Tuser findByEmail(String email);

    Tuser findByOtherIdentifier(String identifier);

    boolean existsByEmail(String email);

    boolean existsByOtherIdentifier(String identifier);

    Tuser findByOtherIdentifierAndTokenLastTen(String identifier, String hashCode);
}
