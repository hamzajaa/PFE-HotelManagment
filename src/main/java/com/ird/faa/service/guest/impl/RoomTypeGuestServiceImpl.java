package com.ird.faa.service.guest.impl;

import java.util.List;
import java.util.Date;

import java.util.ArrayList;

import com.ird.faa.bean.Expenses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

import com.ird.faa.bean.RoomType;
import com.ird.faa.bean.Amenity;
import com.ird.faa.dao.RoomTypeDao;
import com.ird.faa.service.guest.facade.RoomTypeGuestService;
import com.ird.faa.service.guest.facade.AmenityGuestService;

import com.ird.faa.ws.rest.provided.vo.RoomTypeVo;
import com.ird.faa.service.util.*;

import com.ird.faa.service.core.facade.ArchivableService;
import com.ird.faa.service.core.impl.AbstractServiceImpl;

@Service
public class RoomTypeGuestServiceImpl extends AbstractServiceImpl<RoomType> implements RoomTypeGuestService {

    @Autowired
    private RoomTypeDao roomTypeDao;

    @Autowired
    private ArchivableService<RoomType> archivableService;
    @Autowired
    private AmenityGuestService amenityService;


    @Autowired
    private EntityManager entityManager;


    @Override
    public List<RoomType> findAll() {
        return roomTypeDao.findAll();
    }

    @Override
    public RoomType findByShortCode(String shortCode) {
        if (shortCode == null) return null;
        return roomTypeDao.findByShortCode(shortCode);
    }

    @Override
    @Transactional
    public int deleteByShortCode(String shortCode) {
        return roomTypeDao.deleteByShortCode(shortCode);
    }

    @Override
    public RoomType findByIdOrShortCode(RoomType roomType) {
        RoomType resultat = null;
        if (roomType != null) {
            if (StringUtil.isNotEmpty(roomType.getId())) {
                resultat = roomTypeDao.getOne(roomType.getId());
            } else if (StringUtil.isNotEmpty(roomType.getShortCode())) {
                resultat = roomTypeDao.findByShortCode(roomType.getShortCode());
            }
        }
        return resultat;
    }

    @Override
    public RoomType findById(Long id) {
        if (id == null) return null;
        return roomTypeDao.getOne(id);
    }

    @Override
    public RoomType findByIdWithAssociatedList(Long id) {
        RoomType roomType = findById(id);
        findAssociatedLists(roomType);
        return roomType;
    }

    @Override
    public RoomType archiver(RoomType roomType) {
        if (roomType.getArchive() == null) {
            roomType.setArchive(false);
        }
        roomType.setArchive(true);
        roomType.setDateArchivage(new Date());
        roomTypeDao.save(roomType);
        return roomType;

    }

    @Override
    public RoomType desarchiver(RoomType roomType) {
        if (roomType.getArchive() == null) {
            roomType.setArchive(false);
        }
        roomType.setArchive(false);
        roomTypeDao.save(roomType);
        return roomType;
    }

    @Override
    public List<RoomType> findByCouponManagmentId(Long id) {
        return null;
    }

    @Override
    public void deleteByCouponManagmentId(Long id) {

    }

    @Override
    public List<RoomType> findByPaidServiceId(Long id) {
        return null;
    }

    @Override
    public void deleteByPaidServiceId(Long id) {

    }


    private void findAssociatedLists(RoomType roomType) {
        if (roomType != null && roomType.getId() != null) {
            List<Amenity> amenitys = amenityService.findByRoomTypeId(roomType.getId());
            roomType.setAmenitys(amenitys);
        }
    }

    private void deleteAssociatedLists(Long id) {
        if (id != null) {
            amenityService.deleteByRoomTypeId(id);
        }
    }

    private void updateAssociatedLists(RoomType roomType) {
        if (roomType != null && roomType.getId() != null) {
            List
                    <List<Amenity>> resultAmenitys = amenityService.getToBeSavedAndToBeDeleted(amenityService.findByRoomTypeId(roomType.getId()), roomType.getAmenitys());
            amenityService.delete(resultAmenitys.get(1));
            associateAmenity(roomType, resultAmenitys.get(0));
            amenityService.update(resultAmenitys.get(0));

        }
    }

    @Transactional
    public int deleteById(Long id) {
        int res = 0;
        if (roomTypeDao.findById(id).isPresent()) {
            deleteAssociatedLists(id);
            roomTypeDao.deleteById(id);
            res = 1;
        }
        return res;
    }


    @Override
    public RoomType update(RoomType roomType) {
        RoomType foundedRoomType = findById(roomType.getId());
        if (foundedRoomType == null) return null;
        else {
            archivableService.prepare(roomType);
            updateAssociatedLists(roomType);
            return roomTypeDao.save(roomType);
        }
    }

    private void prepareSave(RoomType roomType) {
        roomType.setDateCreation(new Date());
        if (roomType.getDateArchivage() == null)
            roomType.setDateArchivage(new Date());
        if (roomType.getArchive() == null)
            roomType.setArchive(false);


    }

    @Override
    public RoomType save(RoomType roomType) {
        prepareSave(roomType);

        RoomType result = null;
        RoomType foundedRoomType = findByShortCode(roomType.getShortCode());
        if (foundedRoomType == null) {


            RoomType savedRoomType = roomTypeDao.save(roomType);

            saveAmenitys(savedRoomType, roomType.getAmenitys());
            result = savedRoomType;
        }

        return result;
    }

    @Override
    public List<RoomType> save(List<RoomType> roomTypes) {
        List<RoomType> list = new ArrayList<>();
        for (RoomType roomType : roomTypes) {
            list.add(save(roomType));
        }
        return list;
    }


    @Override
    @Transactional
    public int delete(RoomType roomType) {
        if (roomType.getShortCode() == null) return -1;

        RoomType foundedRoomType = findByShortCode(roomType.getShortCode());
        if (foundedRoomType == null) return -1;
        roomTypeDao.delete(foundedRoomType);
        return 1;
    }


    public List<RoomType> findByCriteria(RoomTypeVo roomTypeVo) {

        String query = "SELECT o FROM RoomType o where 1=1 ";

        query += SearchUtil.addConstraint("o", "id", "=", roomTypeVo.getId());
        query += SearchUtil.addConstraint("o", "name", "LIKE", roomTypeVo.getName());
        query += SearchUtil.addConstraint("o", "image", "LIKE", roomTypeVo.getImage());
        query += SearchUtil.addConstraint("o", "shortCode", "LIKE", roomTypeVo.getShortCode());
        query += SearchUtil.addConstraint("o", "slug", "LIKE", roomTypeVo.getSlug());
        query += SearchUtil.addConstraint("o", "description", "LIKE", roomTypeVo.getDescription());
        query += SearchUtil.addConstraint("o", "baseOccupancy", "=", roomTypeVo.getBaseOccupancy());
        query += SearchUtil.addConstraint("o", "higherOcuupancy", "=", roomTypeVo.getHigherOcuupancy());
        query += SearchUtil.addConstraint("o", "numberOfExtraBed", "=", roomTypeVo.getNumberOfExtraBed());
        query += SearchUtil.addConstraint("o", "kidsOccupancy", "=", roomTypeVo.getKidsOccupancy());
        query += SearchUtil.addConstraint("o", "basePrice", "=", roomTypeVo.getBasePrice());
        query += SearchUtil.addConstraint("o", "additionalPersonPrice", "=", roomTypeVo.getAdditionalPersonPrice());
        query += SearchUtil.addConstraint("o", "extraBedPrice", "=", roomTypeVo.getExtraBedPrice());
        query += SearchUtil.addConstraint("o", "archive", "=", roomTypeVo.getArchive());
        query += SearchUtil.addConstraintDate("o", "dateArchivage", "=", roomTypeVo.getDateArchivage());
        query += SearchUtil.addConstraintDate("o", "dateCreation", "=", roomTypeVo.getDateCreation());
        query += SearchUtil.addConstraintMinMax("o", "baseOccupancy", roomTypeVo.getBaseOccupancyMin(), roomTypeVo.getBaseOccupancyMax());
        query += SearchUtil.addConstraintMinMax("o", "higherOcuupancy", roomTypeVo.getHigherOcuupancyMin(), roomTypeVo.getHigherOcuupancyMax());
        query += SearchUtil.addConstraintMinMax("o", "numberOfExtraBed", roomTypeVo.getNumberOfExtraBedMin(), roomTypeVo.getNumberOfExtraBedMax());
        query += SearchUtil.addConstraintMinMax("o", "kidsOccupancy", roomTypeVo.getKidsOccupancyMin(), roomTypeVo.getKidsOccupancyMax());
        query += SearchUtil.addConstraintMinMax("o", "basePrice", roomTypeVo.getBasePriceMin(), roomTypeVo.getBasePriceMax());
        query += SearchUtil.addConstraintMinMax("o", "additionalPersonPrice", roomTypeVo.getAdditionalPersonPriceMin(), roomTypeVo.getAdditionalPersonPriceMax());
        query += SearchUtil.addConstraintMinMax("o", "extraBedPrice", roomTypeVo.getExtraBedPriceMin(), roomTypeVo.getExtraBedPriceMax());
        query += SearchUtil.addConstraintMinMaxDate("o", "dateArchivage", roomTypeVo.getDateArchivageMin(), roomTypeVo.getDateArchivageMax());
        query += SearchUtil.addConstraintMinMaxDate("o", "dateCreation", roomTypeVo.getDateCreationMin(), roomTypeVo.getDateCreationMax());
        return entityManager.createQuery(query).getResultList();
    }

    private void saveAmenitys(RoomType roomType, List<Amenity> amenitys) {

        if (ListUtil.isNotEmpty(roomType.getAmenitys())) {
            List<Amenity> savedAmenitys = new ArrayList<>();
            amenitys.forEach(element -> {
                element.setRoomType(roomType);
                amenityService.save(element);
            });
            roomType.setAmenitys(savedAmenitys);
        }
    }


    @Override
    @Transactional
    public void delete(List<RoomType> roomTypes) {
        if (ListUtil.isNotEmpty(roomTypes)) {
            roomTypes.forEach(e -> roomTypeDao.delete(e));
        }
    }

    @Override
    public void update(List<RoomType> roomTypes) {
        if (ListUtil.isNotEmpty(roomTypes)) {
            roomTypes.forEach(e -> roomTypeDao.save(e));
        }
    }

    private void associateAmenity(RoomType roomType, List<Amenity> amenity) {
        if (ListUtil.isNotEmpty(amenity)) {
            amenity.forEach(e -> e.setRoomType(roomType));
        }
    }


}
