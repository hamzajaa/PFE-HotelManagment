package com.ird.faa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.ird.faa.bean.Departement;


@Repository
public interface DepartementDao extends JpaRepository<Departement,Long> {




    Departement findByCode(String code);

    int deleteByCode(String code);



}
