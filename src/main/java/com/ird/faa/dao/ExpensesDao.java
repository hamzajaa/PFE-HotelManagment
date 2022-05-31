package com.ird.faa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.ird.faa.bean.Expenses;


@Repository
public interface ExpensesDao extends JpaRepository<Expenses,Long> {






    List<Expenses> findByExpensesCategoryId(Long id);

    int deleteByExpensesCategoryId(Long id);


}
