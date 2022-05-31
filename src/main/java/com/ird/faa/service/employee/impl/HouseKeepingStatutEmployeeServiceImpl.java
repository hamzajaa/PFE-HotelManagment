package com.ird.faa.service.employee.impl;

import java.util.List;
    import java.util.Date;

import java.util.ArrayList;

import com.ird.faa.bean.Expenses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import com.ird.faa.bean.HouseKeepingStatut;
import com.ird.faa.dao.HouseKeepingStatutDao;
import com.ird.faa.service.employee.facade.HouseKeepingStatutEmployeeService;

import com.ird.faa.ws.rest.provided.vo.HouseKeepingStatutVo;
import com.ird.faa.service.util.*;

    import com.ird.faa.service.core.facade.ArchivableService;
import com.ird.faa.service.core.impl.AbstractServiceImpl;

@Service
public class HouseKeepingStatutEmployeeServiceImpl extends AbstractServiceImpl<HouseKeepingStatut> implements HouseKeepingStatutEmployeeService {

@Autowired
private HouseKeepingStatutDao houseKeepingStatutDao;

    @Autowired
    private ArchivableService<HouseKeepingStatut> archivableService;


@Autowired
private EntityManager entityManager;


@Override
public List<HouseKeepingStatut> findAll(){
        return houseKeepingStatutDao.findAll();
}
    @Override
    public HouseKeepingStatut findByName(String name){
    if( name==null) return null;
    return houseKeepingStatutDao.findByName(name);
    }

    @Override
    @Transactional
    public int deleteByName(String  name) {
    return houseKeepingStatutDao.deleteByName(name);
    }
    @Override
    public HouseKeepingStatut findByIdOrName(HouseKeepingStatut houseKeepingStatut){
    HouseKeepingStatut resultat=null;
    if(houseKeepingStatut != null){
    if(StringUtil.isNotEmpty(houseKeepingStatut.getId())){
    resultat= houseKeepingStatutDao.getOne(houseKeepingStatut.getId());
    }else if(StringUtil.isNotEmpty(houseKeepingStatut.getName())) {
    resultat= houseKeepingStatutDao.findByName(houseKeepingStatut.getName());
    }
    }
    return resultat;
    }

@Override
public HouseKeepingStatut findById(Long id){
if(id==null) return null;
return houseKeepingStatutDao.getOne(id);
}

@Override
public HouseKeepingStatut findByIdWithAssociatedList(Long id){
    return findById(id);
}
    @Override
    public HouseKeepingStatut archiver(HouseKeepingStatut houseKeepingStatut) {
    if (houseKeepingStatut.getArchive() == null) {
    houseKeepingStatut.setArchive(false);
    }
    houseKeepingStatut.setArchive(true);
    houseKeepingStatut.setDateArchivage(new Date());
    houseKeepingStatutDao.save(houseKeepingStatut);
    return houseKeepingStatut;

    }

    @Override
    public HouseKeepingStatut desarchiver(HouseKeepingStatut houseKeepingStatut) {
    if (houseKeepingStatut.getArchive() == null) {
    houseKeepingStatut.setArchive(false);
    }
    houseKeepingStatut.setArchive(false);
    houseKeepingStatutDao.save(houseKeepingStatut);
    return houseKeepingStatut;
    }




@Transactional
public int deleteById(Long id){
int res=0;
if(houseKeepingStatutDao.findById(id).isPresent())  {
houseKeepingStatutDao.deleteById(id);
res = 1;
}
return res;
}


@Override
public HouseKeepingStatut update(HouseKeepingStatut houseKeepingStatut){
HouseKeepingStatut foundedHouseKeepingStatut = findById(houseKeepingStatut.getId());
if(foundedHouseKeepingStatut==null) return null;
else{
    archivableService.prepare(houseKeepingStatut);
return  houseKeepingStatutDao.save(houseKeepingStatut);
}
}
    private void prepareSave(HouseKeepingStatut houseKeepingStatut){
        houseKeepingStatut.setDateCreation(new Date());
        if(houseKeepingStatut.getDateArchivage() == null)
        houseKeepingStatut.setDateArchivage(new Date());
                    if(houseKeepingStatut.getActive() == null)
                houseKeepingStatut.setActive(false);
                    if(houseKeepingStatut.getArchive() == null)
                houseKeepingStatut.setArchive(false);




    }

@Override
public HouseKeepingStatut save (HouseKeepingStatut houseKeepingStatut){
    prepareSave(houseKeepingStatut);

    HouseKeepingStatut result =null;
    HouseKeepingStatut foundedHouseKeepingStatut = findByName(houseKeepingStatut.getName());
    if(foundedHouseKeepingStatut == null){




    HouseKeepingStatut savedHouseKeepingStatut = houseKeepingStatutDao.save(houseKeepingStatut);

    result = savedHouseKeepingStatut;
    }

    return result;
}

@Override
public List<HouseKeepingStatut> save(List<HouseKeepingStatut> houseKeepingStatuts){
List<HouseKeepingStatut> list = new ArrayList<>();
for(HouseKeepingStatut houseKeepingStatut: houseKeepingStatuts){
list.add(save(houseKeepingStatut));
}
return list;
}



@Override
@Transactional
public int delete(HouseKeepingStatut houseKeepingStatut){
    if(houseKeepingStatut.getName()==null) return -1;

    HouseKeepingStatut foundedHouseKeepingStatut = findByName(houseKeepingStatut.getName());
    if(foundedHouseKeepingStatut==null) return -1;
houseKeepingStatutDao.delete(foundedHouseKeepingStatut);
return 1;
}


public List<HouseKeepingStatut> findByCriteria(HouseKeepingStatutVo houseKeepingStatutVo){

String query = "SELECT o FROM HouseKeepingStatut o where 1=1 ";

            query += SearchUtil.addConstraint( "o", "id","=",houseKeepingStatutVo.getId());
            query += SearchUtil.addConstraint( "o", "name","LIKE",houseKeepingStatutVo.getName());
            query += SearchUtil.addConstraint( "o", "active","=",houseKeepingStatutVo.getActive());
            query += SearchUtil.addConstraint( "o", "archive","=",houseKeepingStatutVo.getArchive());
        query += SearchUtil.addConstraintDate( "o", "dateArchivage","=",houseKeepingStatutVo.getDateArchivage());
        query += SearchUtil.addConstraintDate( "o", "dateCreation","=",houseKeepingStatutVo.getDateCreation());
            query += SearchUtil.addConstraintMinMaxDate("o","dateArchivage",houseKeepingStatutVo.getDateArchivageMin(),houseKeepingStatutVo.getDateArchivageMax());
            query += SearchUtil.addConstraintMinMaxDate("o","dateCreation",houseKeepingStatutVo.getDateCreationMin(),houseKeepingStatutVo.getDateCreationMax());
return entityManager.createQuery(query).getResultList();
}


@Override
@Transactional
public void delete(List<HouseKeepingStatut> houseKeepingStatuts){
if(ListUtil.isNotEmpty(houseKeepingStatuts)){
houseKeepingStatuts.forEach(e->houseKeepingStatutDao.delete(e));
}
}
@Override
public void update(List<HouseKeepingStatut> houseKeepingStatuts){
if(ListUtil.isNotEmpty(houseKeepingStatuts)){
houseKeepingStatuts.forEach(e->houseKeepingStatutDao.save(e));
}
}





    }
