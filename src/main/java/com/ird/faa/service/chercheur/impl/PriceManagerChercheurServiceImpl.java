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
import com.ird.faa.bean.PriceManager;
        import com.ird.faa.bean.RoomType;
import com.ird.faa.dao.PriceManagerDao;
import com.ird.faa.service.chercheur.facade.PriceManagerChercheurService;
        import com.ird.faa.service.chercheur.facade.RoomTypeChercheurService;

import com.ird.faa.ws.rest.provided.vo.PriceManagerVo;
import com.ird.faa.service.util.*;

    import com.ird.faa.service.core.facade.ArchivableService;
import com.ird.faa.service.core.impl.AbstractServiceImpl;

@Service
public class PriceManagerChercheurServiceImpl extends AbstractServiceImpl<PriceManager> implements PriceManagerChercheurService {

@Autowired
private PriceManagerDao priceManagerDao;

    @Autowired
    private ArchivableService<PriceManager> archivableService;
        @Autowired
        private RoomTypeChercheurService roomTypeService ;


@Autowired
private EntityManager entityManager;


@Override
public List<PriceManager> findAll(){
    List<PriceManager> result= new ArrayList();
    result.addAll(findAllNonArchive());
    result.addAll(findAllByOwner());
    return result;
}

        @Override
        public List<PriceManager> findByRoomTypeShortCode(String shortCode){
        return priceManagerDao.findByRoomTypeShortCode(shortCode);
        }

        @Override
        @Transactional
        public int deleteByRoomTypeShortCode(String shortCode){
        return priceManagerDao.deleteByRoomTypeShortCode(shortCode);
        }

        @Override
        public List<PriceManager> findByRoomTypeId(Long id){
        return priceManagerDao.findByRoomTypeId(id);
        }

        @Override
        @Transactional
        public int deleteByRoomTypeId(Long id){
        return priceManagerDao.deleteByRoomTypeId(id);
        }


@Override
public PriceManager findById(Long id){
if(id==null) return null;
return priceManagerDao.getOne(id);
}

@Override
public PriceManager findByIdWithAssociatedList(Long id){
    return findById(id);
}



@Transactional
public int deleteById(Long id){
int res=0;
if(priceManagerDao.findById(id).isPresent())  {
priceManagerDao.deleteById(id);
res = 1;
}
return res;
}


@Override
public PriceManager update(PriceManager priceManager){
PriceManager foundedPriceManager = findById(priceManager.getId());
if(foundedPriceManager==null) return null;
else{
    archivableService.prepare(priceManager);
return  priceManagerDao.save(priceManager);
}
}
    private void prepareSave(PriceManager priceManager){
        priceManager.setDateCreation(new Date());
        if(priceManager.getDateArchivage() == null)
        priceManager.setDateArchivage(new Date());
                    if(priceManager.getArchive() == null)
                priceManager.setArchive(false);




    }

@Override
public PriceManager save (PriceManager priceManager){
    prepareSave(priceManager);



    findRoomType(priceManager);

    return priceManagerDao.save(priceManager);


}

@Override
public List<PriceManager> save(List<PriceManager> priceManagers){
List<PriceManager> list = new ArrayList<>();
for(PriceManager priceManager: priceManagers){
list.add(save(priceManager));
}
return list;
}



@Override
@Transactional
public int delete(PriceManager priceManager){
    if(priceManager.getId()==null) return -1;
    PriceManager foundedPriceManager = findById(priceManager.getId());
    if(foundedPriceManager==null) return -1;
priceManagerDao.delete(foundedPriceManager);
return 1;
}


public List<PriceManager> findByCriteria(PriceManagerVo priceManagerVo){

String query = "SELECT o FROM PriceManager o where 1=1 ";

            query += SearchUtil.addConstraint( "o", "id","=",priceManagerVo.getId());
        query += SearchUtil.addConstraintDate( "o", "dateStart","=",priceManagerVo.getDateStart());
        query += SearchUtil.addConstraintDate( "o", "dateEnd","=",priceManagerVo.getDateEnd());
            query += SearchUtil.addConstraint( "o", "montant","=",priceManagerVo.getMontant());
            query += SearchUtil.addConstraint( "o", "archive","=",priceManagerVo.getArchive());
        query += SearchUtil.addConstraintDate( "o", "dateArchivage","=",priceManagerVo.getDateArchivage());
        query += SearchUtil.addConstraintDate( "o", "dateCreation","=",priceManagerVo.getDateCreation());
            query += SearchUtil.addConstraintMinMaxDate("o","dateStart",priceManagerVo.getDateStartMin(),priceManagerVo.getDateStartMax());
            query += SearchUtil.addConstraintMinMaxDate("o","dateEnd",priceManagerVo.getDateEndMin(),priceManagerVo.getDateEndMax());
            query += SearchUtil.addConstraintMinMax("o","montant",priceManagerVo.getMontantMin(),priceManagerVo.getMontantMax());
            query += SearchUtil.addConstraintMinMaxDate("o","dateArchivage",priceManagerVo.getDateArchivageMin(),priceManagerVo.getDateArchivageMax());
            query += SearchUtil.addConstraintMinMaxDate("o","dateCreation",priceManagerVo.getDateCreationMin(),priceManagerVo.getDateCreationMax());
    if(priceManagerVo.getRoomTypeVo()!=null){
        query += SearchUtil.addConstraint( "o", "roomType.id","=",priceManagerVo.getRoomTypeVo().getId());
            query += SearchUtil.addConstraint( "o", "roomType.shortCode","LIKE",priceManagerVo.getRoomTypeVo().getShortCode());
    }

return entityManager.createQuery(query).getResultList();
}

    private void findRoomType(PriceManager priceManager){
        RoomType loadedRoomType =roomTypeService.findByIdOrShortCode(priceManager.getRoomType());

    if(loadedRoomType==null ) {
    return;
    }
    priceManager.setRoomType(loadedRoomType);
    }

@Override
@Transactional
public void delete(List<PriceManager> priceManagers){
if(ListUtil.isNotEmpty(priceManagers)){
priceManagers.forEach(e->priceManagerDao.delete(e));
}
}
@Override
public void update(List<PriceManager> priceManagers){
if(ListUtil.isNotEmpty(priceManagers)){
priceManagers.forEach(e->priceManagerDao.save(e));
}
}




        public List<PriceManager> findAllNonArchive(){
        String query = "SELECT o FROM PriceManager o  WHERE o.archive != true AND o.visible = true";
        return entityManager.createQuery(query).getResultList();
        }

        public List<PriceManager> findAllByOwner(){
        List<PriceManager> result= new ArrayList();
        User currentUser = SecurityUtil.getCurrentUser();
        if (currentUser != null && StringUtil.isNotEmpty(currentUser.getUsername())){
        String query = "SELECT o FROM PriceManager o  WHERE o.archive != true AND o.visible = false AND o.username = '"+ currentUser.getUsername()+"'";
        result = entityManager.createQuery(query).getResultList();
        }
        return result;
        }



    }
