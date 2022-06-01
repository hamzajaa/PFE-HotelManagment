package com.ird.faa.service.admin.impl;

import com.ird.faa.bean.Amenity;
import com.ird.faa.bean.RoomType;
import com.ird.faa.bean.RoomTypeItemAmenity;
import com.ird.faa.dao.RoomTypeItemAmenityDao;
import com.ird.faa.service.admin.facade.AmenityAdminService;
import com.ird.faa.service.admin.facade.RoomTypeAdminService;
import com.ird.faa.service.admin.facade.RoomTypeItemAmenityAdminService;
import com.ird.faa.service.core.facade.ArchivableService;
import com.ird.faa.service.core.impl.AbstractServiceImpl;
import com.ird.faa.service.util.ListUtil;
import com.ird.faa.service.util.SearchUtil;
import com.ird.faa.ws.rest.provided.vo.RoomTypeItemAmenityVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RoomTypeItemAmenityImpl extends AbstractServiceImpl<RoomTypeItemAmenity> implements RoomTypeItemAmenityAdminService {

    @Autowired
    private RoomTypeItemAmenityDao roomTypeItemAmenityDao;

    @Autowired
    private ArchivableService<RoomTypeItemAmenity> archivableService;

    @Autowired
    private RoomTypeAdminService roomTypeService;

    @Autowired
    private AmenityAdminService amenityService;

    @Autowired
    private EntityManager entityManager;


    @Override
    public List<RoomTypeItemAmenity> findAll() {
        return roomTypeItemAmenityDao.findAll();
    }

    @Override
    public RoomTypeItemAmenity findById(Long id) {
        if (id == null) return null;
        return roomTypeItemAmenityDao.getOne(id);
    }

    @Override
    public RoomTypeItemAmenity findByIdWithAssociatedList(Long id) {
        return findById(id);
    }

    @Override
    @Transactional
    public int deleteById(Long id) {
        int res = 0;
        if (roomTypeItemAmenityDao.findById(id).isPresent()) {
            roomTypeItemAmenityDao.deleteById(id);
            res = 1;
        }
        return res;
    }

    @Override
    public RoomTypeItemAmenity save(RoomTypeItemAmenity roomTypeItemAmenity) {
        prepareSave(roomTypeItemAmenity);


        findAmenity(roomTypeItemAmenity);
        findRoomType(roomTypeItemAmenity);

        return roomTypeItemAmenityDao.save(roomTypeItemAmenity);
    }

    private void prepareSave(RoomTypeItemAmenity roomTypeItemAmenity) {
        roomTypeItemAmenity.setDateCreation(new Date());
        if (roomTypeItemAmenity.getDateArchivage() == null)
            roomTypeItemAmenity.setDateArchivage(new Date());
        if (roomTypeItemAmenity.getArchive() == null)
            roomTypeItemAmenity.setArchive(false);


    }

    @Override
    public List<RoomTypeItemAmenity> save(List<RoomTypeItemAmenity> roomTypeItemAmenities) {
        List<RoomTypeItemAmenity> list = new ArrayList<>();
        for (RoomTypeItemAmenity roomTypeItemAmenity : roomTypeItemAmenities) {
            list.add(save(roomTypeItemAmenity));
        }
        return list;
    }

    @Override
    public RoomTypeItemAmenity update(RoomTypeItemAmenity roomTypeItemAmenity) {
        RoomTypeItemAmenity foundedRoomTypeItemAmenity = findById(roomTypeItemAmenity.getId());
        if (foundedRoomTypeItemAmenity == null) return null;
        else {
            archivableService.prepare(roomTypeItemAmenity);
            return roomTypeItemAmenityDao.save(roomTypeItemAmenity);
        }
    }

    @Override
    @Transactional
    public int delete(RoomTypeItemAmenity roomTypeItemAmenity) {
        if (roomTypeItemAmenity.getId() == null) return -1;
        RoomTypeItemAmenity foundedRoomTypeItemAmenity = findById(roomTypeItemAmenity.getId());
        if (foundedRoomTypeItemAmenity == null) return -1;
        roomTypeItemAmenityDao.delete(foundedRoomTypeItemAmenity);
        return 1;
    }

    public List<RoomTypeItemAmenity> findByCriteria(RoomTypeItemAmenityVo roomTypeItemAmenityVo) {
        String query = "SELECT o FROM RoomTypeItemAmenity o where 1=1 ";

        query += SearchUtil.addConstraint("o", "id", "=", roomTypeItemAmenityVo.getId());
        query += SearchUtil.addConstraint("o", "archive", "=", roomTypeItemAmenityVo.getArchive());
        query += SearchUtil.addConstraintDate("o", "dateArchivage", "=", roomTypeItemAmenityVo.getDateArchivage());
        query += SearchUtil.addConstraintDate("o", "dateCreation", "=", roomTypeItemAmenityVo.getDateCreation());
        query += SearchUtil.addConstraintMinMaxDate("o", "dateArchivage", roomTypeItemAmenityVo.getDateArchivageMin(), roomTypeItemAmenityVo.getDateArchivageMax());
        query += SearchUtil.addConstraintMinMaxDate("o", "dateCreation", roomTypeItemAmenityVo.getDateCreationMin(), roomTypeItemAmenityVo.getDateCreationMax());
        if (roomTypeItemAmenityVo.getAmenityVo() != null) {
            query += SearchUtil.addConstraint("o", "amenity.id", "=", roomTypeItemAmenityVo.getAmenityVo().getId());
        }

        if (roomTypeItemAmenityVo.getRoomTypeVo() != null) {
            query += SearchUtil.addConstraint("o", "roomType.id", "=", roomTypeItemAmenityVo.getRoomTypeVo().getId());
        }


        return entityManager.createQuery(query).getResultList();
    }

    private void findAmenity(RoomTypeItemAmenity roomTypeItemAmenity) {
        Amenity loadedAmenity = amenityService.findById(roomTypeItemAmenity.getAmenity().getId());

        if (loadedAmenity == null) {
            return;
        }
        roomTypeItemAmenity.setAmenity(loadedAmenity);
    }

    private void findRoomType(RoomTypeItemAmenity roomTypeItemAmenity) {
        RoomType loadedRoomType = null;
        if (roomTypeItemAmenity.getRoomType() != null && roomTypeItemAmenity.getRoomType().getId() != null)
            loadedRoomType = roomTypeService.findById(roomTypeItemAmenity.getRoomType().getId());

        if (loadedRoomType == null) {
            return;
        }
        roomTypeItemAmenity.setRoomType(loadedRoomType);
    }



    @Override
    @Transactional
    public void delete(List<RoomTypeItemAmenity> roomTypeItemAmenities) {
        if (ListUtil.isNotEmpty(roomTypeItemAmenities)) {
            roomTypeItemAmenities.forEach(e -> roomTypeItemAmenityDao.delete(e));
        }
    }

    @Override
    public void update(List<RoomTypeItemAmenity> roomTypeItemAmenities) {
        if (ListUtil.isNotEmpty(roomTypeItemAmenities)) {
            roomTypeItemAmenities.forEach(e -> roomTypeItemAmenityDao.save(e));
        }
    }

    @Override
    public List<RoomTypeItemAmenity> findByAmenityId(Long id) {
        return roomTypeItemAmenityDao.findByAmenityId(id);
    }

    @Override
    public int deleteByAmenityId(Long id) {
        return roomTypeItemAmenityDao.deleteByAmenityId(id);
    }

    @Override
    public List<RoomTypeItemAmenity> findByRoomTypeId(Long id) {
        return roomTypeItemAmenityDao.findByRoomTypeId(id);
    }

    @Override
    public int deleteByRoomTypeId(Long id) {
        return roomTypeItemAmenityDao.deleteByRoomTypeId(id);
    }

    @Override
    public RoomTypeItemAmenity archiver(RoomTypeItemAmenity roomTypeItemAmenity) {
        if (roomTypeItemAmenity.getArchive() == null) {
            roomTypeItemAmenity.setArchive(false);
        }
        roomTypeItemAmenity.setArchive(true);
        roomTypeItemAmenity.setDateArchivage(new Date());
        roomTypeItemAmenityDao.save(roomTypeItemAmenity);
        return roomTypeItemAmenity;

    }

    @Override
    public RoomTypeItemAmenity desarchiver(RoomTypeItemAmenity roomTypeItemAmenity) {
        if (roomTypeItemAmenity.getArchive() == null) {
            roomTypeItemAmenity.setArchive(false);
        }
        roomTypeItemAmenity.setArchive(false);
        roomTypeItemAmenityDao.save(roomTypeItemAmenity);
        return roomTypeItemAmenity;
    }
}
