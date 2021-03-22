package com.integrador.sicdet.repository;

import com.integrador.sicdet.entity.Turl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurlRepository extends JpaRepository<Turl, Integer> {
    Turl findByUrl(String url);

}
