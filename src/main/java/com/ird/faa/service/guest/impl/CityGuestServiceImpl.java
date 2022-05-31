package com.ird.faa.service.guest.impl;

import java.util.List;

import java.util.ArrayList;

import com.ird.faa.bean.Expenses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

import com.ird.faa.bean.City;
import com.ird.faa.bean.Country;
import com.ird.faa.dao.CityDao;
import com.ird.faa.service.guest.facade.CityGuestService;
import com.ird.faa.service.guest.facade.CountryGuestService;

import com.ird.faa.ws.rest.provided.vo.CityVo;
import com.ird.faa.service.util.*;

import com.ird.faa.service.core.impl.AbstractServiceImpl;

@Service
public class CityGuestServiceImpl extends AbstractServiceImpl<City> implements CityGuestService {

    @Autowired
    private CityDao cityDao;

    @Autowired
    private CountryGuestService countryService;


    @Autowired
    private EntityManager entityManager;


    @Override
    public List<City> findAll() {
        return cityDao.findAll();
    }

    @Override
    public List<City> findByCountryCode(String code) {
        return cityDao.findByCountryCode(code);
    }

    @Override
    @Transactional
    public int deleteByCountryCode(String code) {
        return cityDao.deleteByCountryCode(code);
    }

    @Override
    public List<City> findByCountryId(Long id) {
        return cityDao.findByCountryId(id);
    }

    @Override
    @Transactional
    public int deleteByCountryId(Long id) {
        return cityDao.deleteByCountryId(id);
    }

    @Override
    public City findByCode(String code) {
        if (code == null) return null;
        return cityDao.findByCode(code);
    }

    @Override
    @Transactional
    public int deleteByCode(String code) {
        return cityDao.deleteByCode(code);
    }

    @Override
    public City findByIdOrCode(City city) {
        City resultat = null;
        if (city != null) {
            if (StringUtil.isNotEmpty(city.getId())) {
                resultat = cityDao.getOne(city.getId());
            } else if (StringUtil.isNotEmpty(city.getCode())) {
                resultat = cityDao.findByCode(city.getCode());
            }
        }
        return resultat;
    }

    @Override
    public City findById(Long id) {
        if (id == null) return null;
        return cityDao.getOne(id);
    }

    @Override
    public City findByIdWithAssociatedList(Long id) {
        return findById(id);
    }


    @Transactional
    public int deleteById(Long id) {
        int res = 0;
        if (cityDao.findById(id).isPresent()) {
            cityDao.deleteById(id);
            res = 1;
        }
        return res;
    }


    @Override
    public City update(City city) {
        City foundedCity = findById(city.getId());
        if (foundedCity == null) return null;
        else {
            return cityDao.save(city);
        }
    }

    @Override
    public City save(City city) {

        City result = null;
        City foundedCity = findByCode(city.getCode());
        if (foundedCity == null) {


            findCountry(city);

            City savedCity = cityDao.save(city);

            result = savedCity;
        }

        return result;
    }

    @Override
    public List<City> save(List<City> citys) {
        List<City> list = new ArrayList<>();
        for (City city : citys) {
            list.add(save(city));
        }
        return list;
    }


    @Override
    @Transactional
    public int delete(City city) {
        if (city.getCode() == null) return -1;

        City foundedCity = findByCode(city.getCode());
        if (foundedCity == null) return -1;
        cityDao.delete(foundedCity);
        return 1;
    }


    public List<City> findByCriteria(CityVo cityVo) {

        String query = "SELECT o FROM City o where 1=1 ";

        query += SearchUtil.addConstraint("o", "id", "=", cityVo.getId());
        query += SearchUtil.addConstraint("o", "libelle", "LIKE", cityVo.getLibelle());
        query += SearchUtil.addConstraint("o", "code", "LIKE", cityVo.getCode());
        if (cityVo.getCountryVo() != null) {
            query += SearchUtil.addConstraint("o", "country.id", "=", cityVo.getCountryVo().getId());
            query += SearchUtil.addConstraint("o", "country.code", "LIKE", cityVo.getCountryVo().getCode());
        }

        return entityManager.createQuery(query).getResultList();
    }

    private void findCountry(City city) {
        Country loadedCountry = countryService.findByIdOrCode(city.getCountry());

        if (loadedCountry == null) {
            return;
        }
        city.setCountry(loadedCountry);
    }

    @Override
    @Transactional
    public void delete(List<City> citys) {
        if (ListUtil.isNotEmpty(citys)) {
            citys.forEach(e -> cityDao.delete(e));
        }
    }

    @Override
    public void update(List<City> citys) {
        if (ListUtil.isNotEmpty(citys)) {
            citys.forEach(e -> cityDao.save(e));
        }
    }


}
