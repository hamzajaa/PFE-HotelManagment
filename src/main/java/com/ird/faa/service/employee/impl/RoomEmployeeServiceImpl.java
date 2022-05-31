package com.ird.faa.service.employee.impl;

import java.util.List;
    import java.util.Date;

import java.util.ArrayList;

import com.ird.faa.bean.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;

import com.ird.faa.dao.RoomDao;
import com.ird.faa.service.employee.facade.RoomEmployeeService;
        import com.ird.faa.service.employee.facade.RoomTypeEmployeeService;
        import com.ird.faa.service.employee.facade.FloorEmployeeService;
        import com.ird.faa.service.employee.facade.HouseKeepingEmployeeService;

import com.ird.faa.ws.rest.provided.vo.RoomVo;
import com.ird.faa.service.util.*;

    import com.ird.faa.service.core.facade.ArchivableService;
import com.ird.faa.service.core.impl.AbstractServiceImpl;

@Service
public class RoomEmployeeServiceImpl extends AbstractServiceImpl<Room> implements RoomEmployeeService {

@Autowired
private RoomDao roomDao;

    @Autowired
    private ArchivableService<Room> archivableService;
        @Autowired
        private RoomTypeEmployeeService roomTypeService ;
        @Autowired
        private FloorEmployeeService floorService ;
        @Autowired
        private HouseKeepingEmployeeService houseKeepingService ;


@Autowired
private EntityManager entityManager;


@Override
public List<Room> findAll(){
        return roomDao.findAll();
}

        @Override
        public List<Room> findByRoomTypeShortCode(String shortCode){
        return roomDao.findByRoomTypeShortCode(shortCode);
        }

        @Override
        @Transactional
        public int deleteByRoomTypeShortCode(String shortCode){
        return roomDao.deleteByRoomTypeShortCode(shortCode);
        }

        @Override
        public List<Room> findByRoomTypeId(Long id){
        return roomDao.findByRoomTypeId(id);
        }

        @Override
        @Transactional
        public int deleteByRoomTypeId(Long id){
        return roomDao.deleteByRoomTypeId(id);
        }

        @Override
        public List<Room> findByFloorId(Long id){
        return roomDao.findByFloorId(id);
        }

        @Override
        @Transactional
        public int deleteByFloorId(Long id){
        return roomDao.deleteByFloorId(id);
        }

        @Override
        public List<Room> findByHouseKeepingId(Long id){
        return roomDao.findByHouseKeepingId(id);
        }

        @Override
        @Transactional
        public int deleteByHouseKeepingId(Long id){
        return roomDao.deleteByHouseKeepingId(id);
        }

    @Override
    public Room findByRoomNumber(Long roomNumber){
    if( roomNumber==null) return null;
    return roomDao.findByRoomNumber(roomNumber);
    }

    @Override
    @Transactional
    public int deleteByRoomNumber(Long  roomNumber) {
    return roomDao.deleteByRoomNumber(roomNumber);
    }
    @Override
    public Room findByIdOrRoomNumber(Room room){
    Room resultat=null;
    if(room != null){
    if(StringUtil.isNotEmpty(room.getId())){
    resultat= roomDao.getOne(room.getId());
    }else if(StringUtil.isNotEmpty(room.getRoomNumber())) {
    resultat= roomDao.findByRoomNumber(room.getRoomNumber());
    }
    }
    return resultat;
    }

@Override
public Room findById(Long id){
if(id==null) return null;
return roomDao.getOne(id);
}

@Override
public Room findByIdWithAssociatedList(Long id){
    return findById(id);
}
    @Override
    public Room archiver(Room room) {
    if (room.getArchive() == null) {
    room.setArchive(false);
    }
    room.setArchive(true);
    room.setDateArchivage(new Date());
    roomDao.save(room);
    return room;

    }

    @Override
    public Room desarchiver(Room room) {
    if (room.getArchive() == null) {
    room.setArchive(false);
    }
    room.setArchive(false);
    roomDao.save(room);
    return room;
    }




@Transactional
public int deleteById(Long id){
int res=0;
if(roomDao.findById(id).isPresent())  {
roomDao.deleteById(id);
res = 1;
}
return res;
}


@Override
public Room update(Room room){
Room foundedRoom = findById(room.getId());
if(foundedRoom==null) return null;
else{
    archivableService.prepare(room);
return  roomDao.save(room);
}
}
    private void prepareSave(Room room){
        room.setDateCreation(new Date());
        if(room.getDateArchivage() == null)
        room.setDateArchivage(new Date());
                    if(room.getArchive() == null)
                room.setArchive(false);




    }

@Override
public Room save (Room room){
    prepareSave(room);

    Room result =null;
    Room foundedRoom = findByRoomNumber(room.getRoomNumber());
    if(foundedRoom == null){



    findRoomType(room);
    findFloor(room);
    findHouseKeeping(room);

    Room savedRoom = roomDao.save(room);

    result = savedRoom;
    }

    return result;
}

@Override
public List<Room> save(List<Room> rooms){
List<Room> list = new ArrayList<>();
for(Room room: rooms){
list.add(save(room));
}
return list;
}



@Override
@Transactional
public int delete(Room room){
    if(room.getRoomNumber()==null) return -1;

    Room foundedRoom = findByRoomNumber(room.getRoomNumber());
    if(foundedRoom==null) return -1;
roomDao.delete(foundedRoom);
return 1;
}


public List<Room> findByCriteria(RoomVo roomVo){

String query = "SELECT o FROM Room o where 1=1 ";

            query += SearchUtil.addConstraint( "o", "id","=",roomVo.getId());
            query += SearchUtil.addConstraint( "o", "roomNumber","=",roomVo.getRoomNumber());
            query += SearchUtil.addConstraint( "o", "libelle","LIKE",roomVo.getLibelle());
            query += SearchUtil.addConstraint( "o", "archive","=",roomVo.getArchive());
        query += SearchUtil.addConstraintDate( "o", "dateArchivage","=",roomVo.getDateArchivage());
        query += SearchUtil.addConstraintDate( "o", "dateCreation","=",roomVo.getDateCreation());
            query += SearchUtil.addConstraintMinMax("o","roomNumber",roomVo.getRoomNumberMin(),roomVo.getRoomNumberMax());
            query += SearchUtil.addConstraintMinMaxDate("o","dateArchivage",roomVo.getDateArchivageMin(),roomVo.getDateArchivageMax());
            query += SearchUtil.addConstraintMinMaxDate("o","dateCreation",roomVo.getDateCreationMin(),roomVo.getDateCreationMax());
    if(roomVo.getRoomTypeVo()!=null){
        query += SearchUtil.addConstraint( "o", "roomType.id","=",roomVo.getRoomTypeVo().getId());
            query += SearchUtil.addConstraint( "o", "roomType.shortCode","LIKE",roomVo.getRoomTypeVo().getShortCode());
    }

    if(roomVo.getFloorVo()!=null){
        query += SearchUtil.addConstraint( "o", "floor.id","=",roomVo.getFloorVo().getId());
    }

    if(roomVo.getHouseKeepingVo()!=null){
        query += SearchUtil.addConstraint( "o", "houseKeeping.id","=",roomVo.getHouseKeepingVo().getId());
    }

return entityManager.createQuery(query).getResultList();
}

    private void findRoomType(Room room){
        RoomType loadedRoomType =roomTypeService.findByIdOrShortCode(room.getRoomType());

    if(loadedRoomType==null ) {
    return;
    }
    room.setRoomType(loadedRoomType);
    }
    private void findFloor(Room room){
        Floor loadedFloor = null;
        if(room.getFloor() != null && room.getFloor().getId() !=null)
        loadedFloor =floorService.findById(room.getFloor().getId());

    if(loadedFloor==null ) {
    return;
    }
    room.setFloor(loadedFloor);
    }
    private void findHouseKeeping(Room room){
        HouseKeeping loadedHouseKeeping = null;
        if(room.getHouseKeeping() != null && room.getHouseKeeping().getId() !=null)
        loadedHouseKeeping =houseKeepingService.findById(room.getHouseKeeping().getId());

    if(loadedHouseKeeping==null ) {
    return;
    }
    room.setHouseKeeping(loadedHouseKeeping);
    }

@Override
@Transactional
public void delete(List<Room> rooms){
if(ListUtil.isNotEmpty(rooms)){
rooms.forEach(e->roomDao.delete(e));
}
}
@Override
public void update(List<Room> rooms){
if(ListUtil.isNotEmpty(rooms)){
rooms.forEach(e->roomDao.save(e));
}
}





    }
