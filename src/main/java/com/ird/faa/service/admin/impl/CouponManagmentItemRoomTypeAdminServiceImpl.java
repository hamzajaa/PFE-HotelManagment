package com.ird.faa.service.admin.impl;

import com.ird.faa.bean.CouponManagment;
import com.ird.faa.bean.CouponManagmentItemRoomType;
import com.ird.faa.bean.RoomType;
import com.ird.faa.dao.CouponManagmentItemRoomTypeDao;
import com.ird.faa.service.admin.facade.CouponManagmentAdminService;
import com.ird.faa.service.admin.facade.CouponManagmentItemRoomTypeAdminService;
import com.ird.faa.service.admin.facade.RoomTypeAdminService;
import com.ird.faa.service.core.facade.ArchivableService;
import com.ird.faa.service.core.impl.AbstractServiceImpl;
import com.ird.faa.service.util.ListUtil;
import com.ird.faa.service.util.SearchUtil;
import com.ird.faa.ws.rest.provided.vo.CouponManagmentItemRoomTypeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CouponManagmentItemRoomTypeAdminServiceImpl extends AbstractServiceImpl<CouponManagmentItemRoomType> implements CouponManagmentItemRoomTypeAdminService {

    @Autowired
    private CouponManagmentItemRoomTypeDao couponManagmentItemRoomTypeDao;

    @Autowired
    private ArchivableService<CouponManagmentItemRoomType> archivableService;

    @Autowired
    private RoomTypeAdminService roomTypeService;

    @Autowired
    private CouponManagmentAdminService couponManagmentService;

    @Autowired
    private EntityManager entityManager;


    @Override
    public List<CouponManagmentItemRoomType> findAll() {
        return couponManagmentItemRoomTypeDao.findAll();
    }

    @Override
    public CouponManagmentItemRoomType findById(Long id) {
        if (id == null) return null;
        return couponManagmentItemRoomTypeDao.getOne(id);
    }

    @Override
    public CouponManagmentItemRoomType findByIdWithAssociatedList(Long id) {
        return findById(id);
    }

    @Override
    @Transactional
    public int deleteById(Long id) {
        int res = 0;
        if (couponManagmentItemRoomTypeDao.findById(id).isPresent()) {
            couponManagmentItemRoomTypeDao.deleteById(id);
            res = 1;
        }
        return res;
    }

    @Override
    public CouponManagmentItemRoomType save(CouponManagmentItemRoomType couponManagmentItemRoomType) {
        prepareSave(couponManagmentItemRoomType);


        findRoomType(couponManagmentItemRoomType);
        findCouponManagment(couponManagmentItemRoomType);

        return couponManagmentItemRoomTypeDao.save(couponManagmentItemRoomType);
    }

    private void prepareSave(CouponManagmentItemRoomType couponManagmentItemRoomType) {
        couponManagmentItemRoomType.setDateCreation(new Date());
        if (couponManagmentItemRoomType.getDateArchivage() == null)
            couponManagmentItemRoomType.setDateArchivage(new Date());
        if (couponManagmentItemRoomType.getArchive() == null)
            couponManagmentItemRoomType.setArchive(false);


    }

    @Override
    public List<CouponManagmentItemRoomType> save(List<CouponManagmentItemRoomType> couponManagmentItemRoomTypes) {
        List<CouponManagmentItemRoomType> list = new ArrayList<>();
        for (CouponManagmentItemRoomType couponManagmentItemRoomType : couponManagmentItemRoomTypes) {
            list.add(save(couponManagmentItemRoomType));
        }
        return list;
    }

    @Override
    public CouponManagmentItemRoomType update(CouponManagmentItemRoomType couponManagmentItemRoomType) {
        CouponManagmentItemRoomType foundedCouponManagmentItemRoomType = findById(couponManagmentItemRoomType.getId());
        if (foundedCouponManagmentItemRoomType == null) return null;
        else {
            archivableService.prepare(couponManagmentItemRoomType);
            return couponManagmentItemRoomTypeDao.save(couponManagmentItemRoomType);
        }
    }

    @Override
    @Transactional
    public int delete(CouponManagmentItemRoomType couponManagmentItemRoomType) {
        if (couponManagmentItemRoomType.getId() == null) return -1;
        CouponManagmentItemRoomType foundedCouponManagmentItemRoomType = findById(couponManagmentItemRoomType.getId());
        if (foundedCouponManagmentItemRoomType == null) return -1;
        couponManagmentItemRoomTypeDao.delete(foundedCouponManagmentItemRoomType);
        return 1;
    }

    public List<CouponManagmentItemRoomType> findByCriteria(CouponManagmentItemRoomTypeVo couponManagmentItemRoomTypesVo) {
        String query = "SELECT o FROM CouponManagmentItemRoomType o where 1=1 ";

        query += SearchUtil.addConstraint("o", "id", "=", couponManagmentItemRoomTypesVo.getId());
        query += SearchUtil.addConstraint("o", "archive", "=", couponManagmentItemRoomTypesVo.getArchive());
        query += SearchUtil.addConstraintDate("o", "dateArchivage", "=", couponManagmentItemRoomTypesVo.getDateArchivage());
        query += SearchUtil.addConstraintDate("o", "dateCreation", "=", couponManagmentItemRoomTypesVo.getDateCreation());
        query += SearchUtil.addConstraintMinMaxDate("o", "dateArchivage", couponManagmentItemRoomTypesVo.getDateArchivageMin(), couponManagmentItemRoomTypesVo.getDateArchivageMax());
        query += SearchUtil.addConstraintMinMaxDate("o", "dateCreation", couponManagmentItemRoomTypesVo.getDateCreationMin(), couponManagmentItemRoomTypesVo.getDateCreationMax());
        if (couponManagmentItemRoomTypesVo.getCouponManagmentVo() != null) {
            query += SearchUtil.addConstraint("o", "couponManagment.id", "=", couponManagmentItemRoomTypesVo.getCouponManagmentVo().getId());
        }

        if (couponManagmentItemRoomTypesVo.getRoomTypeVo() != null) {
            query += SearchUtil.addConstraint("o", "roomType.id", "=", couponManagmentItemRoomTypesVo.getRoomTypeVo().getId());
        }


        return entityManager.createQuery(query).getResultList();
    }

    private void findRoomType(CouponManagmentItemRoomType couponManagmentItemRoomType) {
        RoomType loadedRoomType = roomTypeService.findById(couponManagmentItemRoomType.getRoomType().getId());

        if (loadedRoomType == null) {
            return;
        }
        couponManagmentItemRoomType.setRoomType(loadedRoomType);
    }

    private void findCouponManagment(CouponManagmentItemRoomType couponManagmentItemRoomType) {
        CouponManagment loadedCouponManagment = null;
        if (couponManagmentItemRoomType.getCouponManagment() != null && couponManagmentItemRoomType.getCouponManagment().getId() != null)
            loadedCouponManagment = couponManagmentService.findById(couponManagmentItemRoomType.getCouponManagment().getId());

        if (loadedCouponManagment == null) {
            return;
        }
        couponManagmentItemRoomType.setCouponManagment(loadedCouponManagment);
    }



    @Override
    @Transactional
    public void delete(List<CouponManagmentItemRoomType> couponManagmentItemRoomTypes) {
        if (ListUtil.isNotEmpty(couponManagmentItemRoomTypes)) {
            couponManagmentItemRoomTypes.forEach(e -> couponManagmentItemRoomTypeDao.delete(e));
        }
    }

    @Override
    public void update(List<CouponManagmentItemRoomType> couponManagmentItemRoomTypes) {
        if (ListUtil.isNotEmpty(couponManagmentItemRoomTypes)) {
            couponManagmentItemRoomTypes.forEach(e -> couponManagmentItemRoomTypeDao.save(e));
        }
    }

    @Override
    public List<CouponManagmentItemRoomType> findByRoomTypeId(Long id) {
        return couponManagmentItemRoomTypeDao.findByRoomTypeId(id);
    }

    @Override
    @Transactional
    public int deleteByRoomTypeId(Long id) {
        return couponManagmentItemRoomTypeDao.deleteByRoomTypeId(id);
    }

    @Override
    public List<CouponManagmentItemRoomType> findByCouponManagmentId(Long id) {
        return couponManagmentItemRoomTypeDao.findByCouponManagmentId(id);
    }

    @Override
    @Transactional
    public int deleteByCouponManagmentId(Long id) {
        return couponManagmentItemRoomTypeDao.deleteByCouponManagmentId(id);
    }

    @Override
    public CouponManagmentItemRoomType archiver(CouponManagmentItemRoomType couponManagmentItemRoomType) {
        if (couponManagmentItemRoomType.getArchive() == null) {
            couponManagmentItemRoomType.setArchive(false);
        }
        couponManagmentItemRoomType.setArchive(true);
        couponManagmentItemRoomType.setDateArchivage(new Date());
        couponManagmentItemRoomTypeDao.save(couponManagmentItemRoomType);
        return couponManagmentItemRoomType;

    }

    @Override
    public CouponManagmentItemRoomType desarchiver(CouponManagmentItemRoomType couponManagmentItemRoomType) {
        if (couponManagmentItemRoomType.getArchive() == null) {
            couponManagmentItemRoomType.setArchive(false);
        }
        couponManagmentItemRoomType.setArchive(false);
        couponManagmentItemRoomTypeDao.save(couponManagmentItemRoomType);
        return couponManagmentItemRoomType;
    }
    
}
