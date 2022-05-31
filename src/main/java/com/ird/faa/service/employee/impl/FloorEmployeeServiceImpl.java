package com.ird.faa.service.employee.impl;

import java.util.List;
    import java.util.Date;

import java.util.ArrayList;

import com.ird.faa.bean.Expenses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import com.ird.faa.bean.Floor;
import com.ird.faa.dao.FloorDao;
import com.ird.faa.service.employee.facade.FloorEmployeeService;

import com.ird.faa.ws.rest.provided.vo.FloorVo;
import com.ird.faa.service.util.*;

    import com.ird.faa.service.core.facade.ArchivableService;
import com.ird.faa.service.core.impl.AbstractServiceImpl;

@Service
public class FloorEmployeeServiceImpl extends AbstractServiceImpl<Floor> implements FloorEmployeeService {

@Autowired
private FloorDao floorDao;

    @Autowired
    private ArchivableService<Floor> archivableService;


@Autowired
private EntityManager entityManager;


@Override
public List<Floor> findAll(){
        return floorDao.findAll();
}

@Override
public Floor findById(Long id){
if(id==null) return null;
return floorDao.getOne(id);
}

@Override
public Floor findByIdWithAssociatedList(Long id){
    return findById(id);
}
    @Override
    public Floor archiver(Floor floor) {
    if (floor.getArchive() == null) {
    floor.setArchive(false);
    }
    floor.setArchive(true);
    floor.setDateArchivage(new Date());
    floorDao.save(floor);
    return floor;

    }

    @Override
    public Floor desarchiver(Floor floor) {
    if (floor.getArchive() == null) {
    floor.setArchive(false);
    }
    floor.setArchive(false);
    floorDao.save(floor);
    return floor;
    }




@Transactional
public int deleteById(Long id){
int res=0;
if(floorDao.findById(id).isPresent())  {
floorDao.deleteById(id);
res = 1;
}
return res;
}


@Override
public Floor update(Floor floor){
Floor foundedFloor = findById(floor.getId());
if(foundedFloor==null) return null;
else{
    archivableService.prepare(floor);
return  floorDao.save(floor);
}
}
    private void prepareSave(Floor floor){
        floor.setDateCreation(new Date());
        if(floor.getDateArchivage() == null)
        floor.setDateArchivage(new Date());
                    if(floor.getActive() == null)
                floor.setActive(false);
                    if(floor.getArchive() == null)
                floor.setArchive(false);




    }

@Override
public Floor save (Floor floor){
    prepareSave(floor);




    return floorDao.save(floor);


}

@Override
public List<Floor> save(List<Floor> floors){
List<Floor> list = new ArrayList<>();
for(Floor floor: floors){
list.add(save(floor));
}
return list;
}



@Override
@Transactional
public int delete(Floor floor){
    if(floor.getId()==null) return -1;
    Floor foundedFloor = findById(floor.getId());
    if(foundedFloor==null) return -1;
floorDao.delete(foundedFloor);
return 1;
}


public List<Floor> findByCriteria(FloorVo floorVo){

String query = "SELECT o FROM Floor o where 1=1 ";

            query += SearchUtil.addConstraint( "o", "id","=",floorVo.getId());
            query += SearchUtil.addConstraint( "o", "name","LIKE",floorVo.getName());
            query += SearchUtil.addConstraint( "o", "flootNumber","=",floorVo.getFlootNumber());
            query += SearchUtil.addConstraint( "o", "active","=",floorVo.getActive());
            query += SearchUtil.addConstraint( "o", "archive","=",floorVo.getArchive());
        query += SearchUtil.addConstraintDate( "o", "dateArchivage","=",floorVo.getDateArchivage());
        query += SearchUtil.addConstraintDate( "o", "dateCreation","=",floorVo.getDateCreation());
            query += SearchUtil.addConstraintMinMax("o","flootNumber",floorVo.getFlootNumberMin(),floorVo.getFlootNumberMax());
            query += SearchUtil.addConstraintMinMaxDate("o","dateArchivage",floorVo.getDateArchivageMin(),floorVo.getDateArchivageMax());
            query += SearchUtil.addConstraintMinMaxDate("o","dateCreation",floorVo.getDateCreationMin(),floorVo.getDateCreationMax());
return entityManager.createQuery(query).getResultList();
}


@Override
@Transactional
public void delete(List<Floor> floors){
if(ListUtil.isNotEmpty(floors)){
floors.forEach(e->floorDao.delete(e));
}
}
@Override
public void update(List<Floor> floors){
if(ListUtil.isNotEmpty(floors)){
floors.forEach(e->floorDao.save(e));
}
}





    }
