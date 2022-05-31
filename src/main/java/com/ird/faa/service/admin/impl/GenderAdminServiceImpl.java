package com.ird.faa.service.admin.impl;

import com.ird.faa.bean.Gender;
import com.ird.faa.dao.GenderDao;
import com.ird.faa.service.admin.facade.GenderAdminService;
import com.ird.faa.service.core.impl.AbstractServiceImpl;
import com.ird.faa.service.util.ListUtil;
import com.ird.faa.service.util.SearchUtil;
import com.ird.faa.service.util.StringUtil;
import com.ird.faa.ws.rest.provided.vo.GenderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Service
public class GenderAdminServiceImpl extends AbstractServiceImpl<Gender> implements GenderAdminService {

    @Autowired
    private GenderDao genderDao;


    @Autowired
    private EntityManager entityManager;


    @Override
    public List<Gender> findAll() {
        return genderDao.findAll();
    }

    @Override
    public Gender findByCode(String code) {
        if (code == null) return null;
        return genderDao.findByCode(code);
    }

    @Override
    @Transactional
    public int deleteByCode(String code) {
        return genderDao.deleteByCode(code);
    }

    @Override
    public Gender findByIdOrCode(Gender gender) {
        Gender resultat = null;
        if (gender != null) {
            if (StringUtil.isNotEmpty(gender.getId())) {
                resultat = genderDao.getOne(gender.getId());
            } else if (StringUtil.isNotEmpty(gender.getCode())) {
                resultat = genderDao.findByCode(gender.getCode());
            }
        }
        return resultat;
    }

    @Override
    public Gender findById(Long id) {
        if (id == null) return null;
        return genderDao.getOne(id);
    }

    @Override
    public Gender findByIdWithAssociatedList(Long id) {
        return findById(id);
    }


    @Transactional
    public int deleteById(Long id) {
        int res = 0;
        if (genderDao.findById(id).isPresent()) {
            genderDao.deleteById(id);
            res = 1;
        }
        return res;
    }


    @Override
    public Gender update(Gender gender) {
        Gender foundedGender = findById(gender.getId());
        if (foundedGender == null) return null;
        else {
            return genderDao.save(gender);
        }
    }

    @Override
    public Gender save(Gender gender) {

        Gender result = null;
        Gender foundedGender = findByCode(gender.getCode());
        if (foundedGender == null) {


            Gender savedGender = genderDao.save(gender);

            result = savedGender;
        }

        return result;
    }

    @Override
    public List<Gender> save(List<Gender> genders) {
        List<Gender> list = new ArrayList<>();
        for (Gender gender : genders) {
            list.add(save(gender));
        }
        return list;
    }


    @Override
    @Transactional
    public int delete(Gender gender) {
        if (gender.getCode() == null) return -1;

        Gender foundedGender = findByCode(gender.getCode());
        if (foundedGender == null) return -1;
        genderDao.delete(foundedGender);
        return 1;
    }


    public List<Gender> findByCriteria(GenderVo genderVo) {

        String query = "SELECT o FROM Gender o where 1=1 ";

        query += SearchUtil.addConstraint("o", "id", "=", genderVo.getId());
        query += SearchUtil.addConstraint("o", "libelle", "LIKE", genderVo.getName());
        query += SearchUtil.addConstraint("o", "code", "LIKE", genderVo.getCode());
        return entityManager.createQuery(query).getResultList();
    }


    @Override
    @Transactional
    public void delete(List<Gender> genders) {
        if (ListUtil.isNotEmpty(genders)) {
            genders.forEach(e -> genderDao.delete(e));
        }
    }

    @Override
    public void update(List<Gender> genders) {
        if (ListUtil.isNotEmpty(genders)) {
            genders.forEach(e -> genderDao.save(e));
        }
    }


}
