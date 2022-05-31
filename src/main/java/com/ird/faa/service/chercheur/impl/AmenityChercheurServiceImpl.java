package com.ird.faa.service.chercheur.impl;

import java.util.List;
    import java.util.Date;

import java.util.ArrayList;

import com.ird.faa.bean.Expenses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
    import com.ird.faa.service.util.StringUtil;
    import com.ird.faa.security.common.SecurityUtil;
    import com.ird.faa.security.bean.User;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import com.ird.faa.bean.Amenity;
import com.ird.faa.dao.AmenityDao;
import com.ird.faa.service.chercheur.facade.AmenityChercheurService;

import com.ird.faa.ws.rest.provided.vo.AmenityVo;
import com.ird.faa.service.util.*;

    import com.ird.faa.service.core.facade.ArchivableService;
import com.ird.faa.service.core.impl.AbstractServiceImpl;

@Service
public class AmenityChercheurServiceImpl extends AbstractServiceImpl<Amenity> implements AmenityChercheurService {

@Autowired
private AmenityDao amenityDao;

    @Autowired
    private ArchivableService<Amenity> archivableService;


@Autowired
private EntityManager entityManager;


@Override
public List<Amenity> findAll(){
    List<Amenity> result= new ArrayList();
    result.addAll(findAllNonArchive());
    result.addAll(findAllByOwner());
    return result;
}
    @Override
    public Amenity findByName(String name){
    if( name==null) return null;
    return amenityDao.findByName(name);
    }

    @Override
    @Transactional
    public int deleteByName(String  name) {
    return amenityDao.deleteByName(name);
    }

    @Override
    public List<Amenity> findByRoomTypeId(Long id) {
        return null;
    }

    @Override
    public void deleteByRoomTypeId(Long id) {

    }

    @Override
    public Amenity findByIdOrName(Amenity amenity){
    Amenity resultat=null;
    if(amenity != null){
    if(StringUtil.isNotEmpty(amenity.getId())){
    resultat= amenityDao.getOne(amenity.getId());
    }else if(StringUtil.isNotEmpty(amenity.getName())) {
    resultat= amenityDao.findByName(amenity.getName());
    }
    }
    return resultat;
    }

@Override
public Amenity findById(Long id){
if(id==null) return null;
return amenityDao.getOne(id);
}

@Override
public Amenity findByIdWithAssociatedList(Long id){
    return findById(id);
}



@Transactional
public int deleteById(Long id){
int res=0;
if(amenityDao.findById(id).isPresent())  {
amenityDao.deleteById(id);
res = 1;
}
return res;
}


@Override
public Amenity update(Amenity amenity){
Amenity foundedAmenity = findById(amenity.getId());
if(foundedAmenity==null) return null;
else{
    archivableService.prepare(amenity);
return  amenityDao.save(amenity);
}
}
    private void prepareSave(Amenity amenity){
        amenity.setDateCreation(new Date());
        if(amenity.getDateArchivage() == null)
        amenity.setDateArchivage(new Date());
                    if(amenity.getActive() == null)
                amenity.setActive(false);
                    if(amenity.getArchive() == null)
                amenity.setArchive(false);




    }

@Override
public Amenity save (Amenity amenity){
    prepareSave(amenity);

    Amenity result =null;
    Amenity foundedAmenity = findByName(amenity.getName());
    if(foundedAmenity == null){




    Amenity savedAmenity = amenityDao.save(amenity);

    result = savedAmenity;
    }

    return result;
}

@Override
public List<Amenity> save(List<Amenity> amenitys){
List<Amenity> list = new ArrayList<>();
for(Amenity amenity: amenitys){
list.add(save(amenity));
}
return list;
}



@Override
@Transactional
public int delete(Amenity amenity){
    if(amenity.getName()==null) return -1;

    Amenity foundedAmenity = findByName(amenity.getName());
    if(foundedAmenity==null) return -1;
amenityDao.delete(foundedAmenity);
return 1;
}


public List<Amenity> findByCriteria(AmenityVo amenityVo){

String query = "SELECT o FROM Amenity o where 1=1 ";

            query += SearchUtil.addConstraint( "o", "id","=",amenityVo.getId());
            query += SearchUtil.addConstraint( "o", "name","LIKE",amenityVo.getName());
            query += SearchUtil.addConstraint( "o", "image","LIKE",amenityVo.getImage());
            query += SearchUtil.addConstraint( "o", "active","=",amenityVo.getActive());
            query += SearchUtil.addConstraint( "o", "archive","=",amenityVo.getArchive());
        query += SearchUtil.addConstraintDate( "o", "dateArchivage","=",amenityVo.getDateArchivage());
        query += SearchUtil.addConstraintDate( "o", "dateCreation","=",amenityVo.getDateCreation());
            query += SearchUtil.addConstraintMinMaxDate("o","dateArchivage",amenityVo.getDateArchivageMin(),amenityVo.getDateArchivageMax());
            query += SearchUtil.addConstraintMinMaxDate("o","dateCreation",amenityVo.getDateCreationMin(),amenityVo.getDateCreationMax());
return entityManager.createQuery(query).getResultList();
}


@Override
@Transactional
public void delete(List<Amenity> amenitys){
if(ListUtil.isNotEmpty(amenitys)){
amenitys.forEach(e->amenityDao.delete(e));
}
}
@Override
public void update(List<Amenity> amenitys){
if(ListUtil.isNotEmpty(amenitys)){
amenitys.forEach(e->amenityDao.save(e));
}
}




        public List<Amenity> findAllNonArchive(){
        String query = "SELECT o FROM Amenity o  WHERE o.archive != true AND o.visible = true";
        return entityManager.createQuery(query).getResultList();
        }

        public List<Amenity> findAllByOwner(){
        List<Amenity> result= new ArrayList();
        User currentUser = SecurityUtil.getCurrentUser();
        if (currentUser != null && StringUtil.isNotEmpty(currentUser.getUsername())){
        String query = "SELECT o FROM Amenity o  WHERE o.archive != true AND o.visible = false AND o.username = '"+ currentUser.getUsername()+"'";
        result = entityManager.createQuery(query).getResultList();
        }
        return result;
        }



    }
