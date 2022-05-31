package com.ird.faa.service.chercheur.impl;

import java.util.List;
    import java.util.Date;

import java.util.ArrayList;

import com.ird.faa.bean.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
    import com.ird.faa.service.util.StringUtil;
    import com.ird.faa.security.common.SecurityUtil;
    import com.ird.faa.security.bean.User;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;

import com.ird.faa.dao.HouseKeepingDao;
import com.ird.faa.service.chercheur.facade.HouseKeepingChercheurService;
        import com.ird.faa.service.chercheur.facade.EmployeeChercheurService;
        import com.ird.faa.service.chercheur.facade.RoomTypeChercheurService;
        import com.ird.faa.service.chercheur.facade.FloorChercheurService;
        import com.ird.faa.service.chercheur.facade.HouseKeepingStatutChercheurService;
        import com.ird.faa.service.chercheur.facade.RoomChercheurService;

import com.ird.faa.ws.rest.provided.vo.HouseKeepingVo;
import com.ird.faa.service.util.*;

    import com.ird.faa.service.core.facade.ArchivableService;
import com.ird.faa.service.core.impl.AbstractServiceImpl;

@Service
public class HouseKeepingChercheurServiceImpl extends AbstractServiceImpl<HouseKeeping> implements HouseKeepingChercheurService {

@Autowired
private HouseKeepingDao houseKeepingDao;

    @Autowired
    private ArchivableService<HouseKeeping> archivableService;
        @Autowired
        private EmployeeChercheurService employeeService ;
        @Autowired
        private RoomTypeChercheurService roomTypeService ;
        @Autowired
        private FloorChercheurService floorService ;
        @Autowired
        private HouseKeepingStatutChercheurService houseKeepingStatutService ;
        @Autowired
        private RoomChercheurService roomService ;


@Autowired
private EntityManager entityManager;


@Override
public List<HouseKeeping> findAll(){
    List<HouseKeeping> result= new ArrayList();
    result.addAll(findAllNonArchive());
    result.addAll(findAllByOwner());
    return result;
}

        @Override
        public List<HouseKeeping> findByRoomRoomNumber(Long roomNumber){
        return houseKeepingDao.findByRoomRoomNumber(roomNumber);
        }

        @Override
        @Transactional
        public int deleteByRoomRoomNumber(Long roomNumber){
        return houseKeepingDao.deleteByRoomRoomNumber(roomNumber);
        }

        @Override
        public List<HouseKeeping> findByRoomId(Long id){
        return houseKeepingDao.findByRoomId(id);
        }

        @Override
        @Transactional
        public int deleteByRoomId(Long id){
        return houseKeepingDao.deleteByRoomId(id);
        }


        @Override
        public List<HouseKeeping> findByRoomTypeShortCode(String shortCode){
        return houseKeepingDao.findByRoomTypeShortCode(shortCode);
        }

        @Override
        @Transactional
        public int deleteByRoomTypeShortCode(String shortCode){
        return houseKeepingDao.deleteByRoomTypeShortCode(shortCode);
        }

        @Override
        public List<HouseKeeping> findByRoomTypeId(Long id){
        return houseKeepingDao.findByRoomTypeId(id);
        }

        @Override
        @Transactional
        public int deleteByRoomTypeId(Long id){
        return houseKeepingDao.deleteByRoomTypeId(id);
        }

        @Override
        public List<HouseKeeping> findByFloorId(Long id){
        return houseKeepingDao.findByFloorId(id);
        }

        @Override
        @Transactional
        public int deleteByFloorId(Long id){
        return houseKeepingDao.deleteByFloorId(id);
        }


        @Override
        public List<HouseKeeping> findByHouseKeepingStatusName(String name){
        return houseKeepingDao.findByHouseKeepingStatusName(name);
        }

        @Override
        @Transactional
        public int deleteByHouseKeepingStatusName(String name){
        return houseKeepingDao.deleteByHouseKeepingStatusName(name);
        }

        @Override
        public List<HouseKeeping> findByHouseKeepingStatusId(Long id){
        return houseKeepingDao.findByHouseKeepingStatusId(id);
        }

        @Override
        @Transactional
        public int deleteByHouseKeepingStatusId(Long id){
        return houseKeepingDao.deleteByHouseKeepingStatusId(id);
        }


        @Override
        public List<HouseKeeping> findByEmployeeCin(String cin){
        return houseKeepingDao.findByEmployeeCin(cin);
        }

        @Override
        @Transactional
        public int deleteByEmployeeCin(String cin){
        return houseKeepingDao.deleteByEmployeeCin(cin);
        }

        @Override
        public List<HouseKeeping> findByEmployeeId(Long id){
        return houseKeepingDao.findByEmployeeId(id);
        }

        @Override
        @Transactional
        public int deleteByEmployeeId(Long id){
        return houseKeepingDao.deleteByEmployeeId(id);
        }


@Override
public HouseKeeping findById(Long id){
if(id==null) return null;
return houseKeepingDao.getOne(id);
}

@Override
public HouseKeeping findByIdWithAssociatedList(Long id){
    return findById(id);
}



@Transactional
public int deleteById(Long id){
int res=0;
if(houseKeepingDao.findById(id).isPresent())  {
houseKeepingDao.deleteById(id);
res = 1;
}
return res;
}


@Override
public HouseKeeping update(HouseKeeping houseKeeping){
HouseKeeping foundedHouseKeeping = findById(houseKeeping.getId());
if(foundedHouseKeeping==null) return null;
else{
    archivableService.prepare(houseKeeping);
return  houseKeepingDao.save(houseKeeping);
}
}
    private void prepareSave(HouseKeeping houseKeeping){
        houseKeeping.setDateCreation(new Date());
        if(houseKeeping.getDateArchivage() == null)
        houseKeeping.setDateArchivage(new Date());
                    if(houseKeeping.getArchive() == null)
                houseKeeping.setArchive(false);




    }

@Override
public HouseKeeping save (HouseKeeping houseKeeping){
    prepareSave(houseKeeping);



    findRoom(houseKeeping);
    findRoomType(houseKeeping);
    findFloor(houseKeeping);
    findHouseKeepingStatut(houseKeeping);
    findEmployee(houseKeeping);

    return houseKeepingDao.save(houseKeeping);


}

@Override
public List<HouseKeeping> save(List<HouseKeeping> houseKeepings){
List<HouseKeeping> list = new ArrayList<>();
for(HouseKeeping houseKeeping: houseKeepings){
list.add(save(houseKeeping));
}
return list;
}



@Override
@Transactional
public int delete(HouseKeeping houseKeeping){
    if(houseKeeping.getId()==null) return -1;
    HouseKeeping foundedHouseKeeping = findById(houseKeeping.getId());
    if(foundedHouseKeeping==null) return -1;
houseKeepingDao.delete(foundedHouseKeeping);
return 1;
}


public List<HouseKeeping> findByCriteria(HouseKeepingVo houseKeepingVo){

String query = "SELECT o FROM HouseKeeping o where 1=1 ";

            query += SearchUtil.addConstraint( "o", "id","=",houseKeepingVo.getId());
            query += SearchUtil.addConstraint( "o", "archive","=",houseKeepingVo.getArchive());
        query += SearchUtil.addConstraintDate( "o", "dateArchivage","=",houseKeepingVo.getDateArchivage());
        query += SearchUtil.addConstraintDate( "o", "dateCreation","=",houseKeepingVo.getDateCreation());
            query += SearchUtil.addConstraintMinMaxDate("o","dateArchivage",houseKeepingVo.getDateArchivageMin(),houseKeepingVo.getDateArchivageMax());
            query += SearchUtil.addConstraintMinMaxDate("o","dateCreation",houseKeepingVo.getDateCreationMin(),houseKeepingVo.getDateCreationMax());
    if(houseKeepingVo.getRoomVo()!=null){
        query += SearchUtil.addConstraint( "o", "room.id","=",houseKeepingVo.getRoomVo().getId());
            query += SearchUtil.addConstraint( "o", "room.roomNumber","=",houseKeepingVo.getRoomVo().getRoomNumber());
    }

    if(houseKeepingVo.getRoomTypeVo()!=null){
        query += SearchUtil.addConstraint( "o", "roomType.id","=",houseKeepingVo.getRoomTypeVo().getId());
            query += SearchUtil.addConstraint( "o", "roomType.shortCode","LIKE",houseKeepingVo.getRoomTypeVo().getShortCode());
    }

    if(houseKeepingVo.getFloorVo()!=null){
        query += SearchUtil.addConstraint( "o", "floor.id","=",houseKeepingVo.getFloorVo().getId());
    }

    if(houseKeepingVo.getHouseKeepingStatusVo()!=null){
        query += SearchUtil.addConstraint( "o", "houseKeepingStatus.id","=",houseKeepingVo.getHouseKeepingStatusVo().getId());
            query += SearchUtil.addConstraint( "o", "houseKeepingStatus.name","LIKE",houseKeepingVo.getHouseKeepingStatusVo().getName());
    }

    if(houseKeepingVo.getEmployeeVo()!=null){
        query += SearchUtil.addConstraint( "o", "employee.id","=",houseKeepingVo.getEmployeeVo().getId());
            query += SearchUtil.addConstraint( "o", "employee.cin","LIKE",houseKeepingVo.getEmployeeVo().getCin());
    }

return entityManager.createQuery(query).getResultList();
}

    private void findRoom(HouseKeeping houseKeeping){
        Room loadedRoom =roomService.findByIdOrRoomNumber(houseKeeping.getRoom());

    if(loadedRoom==null ) {
    return;
    }
    houseKeeping.setRoom(loadedRoom);
    }
    private void findRoomType(HouseKeeping houseKeeping){
        RoomType loadedRoomType =roomTypeService.findByIdOrShortCode(houseKeeping.getRoomType());

    if(loadedRoomType==null ) {
    return;
    }
    houseKeeping.setRoomType(loadedRoomType);
    }
    private void findFloor(HouseKeeping houseKeeping){
        Floor loadedFloor = null;
        if(houseKeeping.getFloor() != null && houseKeeping.getFloor().getId() !=null)
        loadedFloor =floorService.findById(houseKeeping.getFloor().getId());

    if(loadedFloor==null ) {
    return;
    }
    houseKeeping.setFloor(loadedFloor);
    }
    private void findHouseKeepingStatut(HouseKeeping houseKeeping){
        HouseKeepingStatut loadedHouseKeepingStatut =houseKeepingStatutService.findByIdOrName(houseKeeping.getHouseKeepingStatus());

    if(loadedHouseKeepingStatut==null ) {
    return;
    }
    houseKeeping.setHouseKeepingStatus(loadedHouseKeepingStatut);
    }
    private void findEmployee(HouseKeeping houseKeeping){
        Employee loadedEmployee =employeeService.findByIdOrCin(houseKeeping.getEmployee());

    if(loadedEmployee==null ) {
    return;
    }
    houseKeeping.setEmployee(loadedEmployee);
    }

@Override
@Transactional
public void delete(List<HouseKeeping> houseKeepings){
if(ListUtil.isNotEmpty(houseKeepings)){
houseKeepings.forEach(e->houseKeepingDao.delete(e));
}
}
@Override
public void update(List<HouseKeeping> houseKeepings){
if(ListUtil.isNotEmpty(houseKeepings)){
houseKeepings.forEach(e->houseKeepingDao.save(e));
}
}




        public List<HouseKeeping> findAllNonArchive(){
        String query = "SELECT o FROM HouseKeeping o  WHERE o.archive != true AND o.visible = true";
        return entityManager.createQuery(query).getResultList();
        }

        public List<HouseKeeping> findAllByOwner(){
        List<HouseKeeping> result= new ArrayList();
        User currentUser = SecurityUtil.getCurrentUser();
        if (currentUser != null && StringUtil.isNotEmpty(currentUser.getUsername())){
        String query = "SELECT o FROM HouseKeeping o  WHERE o.archive != true AND o.visible = false AND o.username = '"+ currentUser.getUsername()+"'";
        result = entityManager.createQuery(query).getResultList();
        }
        return result;
        }



    }
