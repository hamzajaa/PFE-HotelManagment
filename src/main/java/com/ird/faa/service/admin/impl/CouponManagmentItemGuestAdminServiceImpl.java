package com.ird.faa.service.admin.impl;

import com.ird.faa.bean.CouponManagment;
import com.ird.faa.bean.CouponManagmentItemGuest;
import com.ird.faa.bean.Guest;
import com.ird.faa.dao.CouponManagmentItemGuestDao;
import com.ird.faa.service.admin.facade.CouponManagmentAdminService;
import com.ird.faa.service.admin.facade.CouponManagmentItemGuestAdminService;
import com.ird.faa.service.admin.facade.GuestAdminService;
import com.ird.faa.service.core.facade.ArchivableService;
import com.ird.faa.service.core.impl.AbstractServiceImpl;
import com.ird.faa.service.util.ListUtil;
import com.ird.faa.service.util.SearchUtil;
import com.ird.faa.ws.rest.provided.vo.CouponManagmentItemGuestVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CouponManagmentItemGuestAdminServiceImpl extends AbstractServiceImpl<CouponManagmentItemGuest> implements CouponManagmentItemGuestAdminService {

    @Autowired
    private CouponManagmentItemGuestDao couponManagmentItemGuestDao;

    @Autowired
    private ArchivableService<CouponManagmentItemGuest> archivableService;

    @Autowired
    private GuestAdminService guestService;

    @Autowired
    private CouponManagmentAdminService couponManagmentService;

    @Autowired
    private EntityManager entityManager;


    @Override
    public List<CouponManagmentItemGuest> findAll() {
        return couponManagmentItemGuestDao.findAll();
    }

    @Override
    public CouponManagmentItemGuest findById(Long id) {
        if (id == null) return null;
        return couponManagmentItemGuestDao.getOne(id);
    }

    @Override
    public CouponManagmentItemGuest findByIdWithAssociatedList(Long id) {
        return findById(id);
    }

    @Override
    @Transactional
    public int deleteById(Long id) {
        int res = 0;
        if (couponManagmentItemGuestDao.findById(id).isPresent()) {
            couponManagmentItemGuestDao.deleteById(id);
            res = 1;
        }
        return res;
    }

    @Override
    public CouponManagmentItemGuest save(CouponManagmentItemGuest couponManagmentItemGuest) {
        prepareSave(couponManagmentItemGuest);


        findGuest(couponManagmentItemGuest);
        findCouponManagment(couponManagmentItemGuest);

        return couponManagmentItemGuestDao.save(couponManagmentItemGuest);
    }

    private void prepareSave(CouponManagmentItemGuest couponManagmentItemGuest) {
        couponManagmentItemGuest.setDateCreation(new Date());
        if (couponManagmentItemGuest.getDateArchivage() == null)
            couponManagmentItemGuest.setDateArchivage(new Date());
        if (couponManagmentItemGuest.getArchive() == null)
            couponManagmentItemGuest.setArchive(false);


    }

    @Override
    public List<CouponManagmentItemGuest> save(List<CouponManagmentItemGuest> couponManagmentItemGuests) {
        List<CouponManagmentItemGuest> list = new ArrayList<>();
        for (CouponManagmentItemGuest couponManagmentItemGuest : couponManagmentItemGuests) {
            list.add(save(couponManagmentItemGuest));
        }
        return list;
    }

    @Override
    public CouponManagmentItemGuest update(CouponManagmentItemGuest couponManagmentItemGuest) {
        CouponManagmentItemGuest foundedCouponManagmentItemGuest = findById(couponManagmentItemGuest.getId());
        if (foundedCouponManagmentItemGuest == null) return null;
        else {
            archivableService.prepare(couponManagmentItemGuest);
            return couponManagmentItemGuestDao.save(couponManagmentItemGuest);
        }
    }

    @Override
    @Transactional
    public int delete(CouponManagmentItemGuest couponManagmentItemGuest) {
        if (couponManagmentItemGuest.getId() == null) return -1;
        CouponManagmentItemGuest foundedCouponManagmentItemGuest = findById(couponManagmentItemGuest.getId());
        if (foundedCouponManagmentItemGuest == null) return -1;
        couponManagmentItemGuestDao.delete(foundedCouponManagmentItemGuest);
        return 1;
    }

    public List<CouponManagmentItemGuest> findByCriteria(CouponManagmentItemGuestVo couponManagmentItemGuestsVo) {
        String query = "SELECT o FROM CouponManagmentItemGuest o where 1=1 ";

        query += SearchUtil.addConstraint("o", "id", "=", couponManagmentItemGuestsVo.getId());
        query += SearchUtil.addConstraint("o", "archive", "=", couponManagmentItemGuestsVo.getArchive());
        query += SearchUtil.addConstraintDate("o", "dateArchivage", "=", couponManagmentItemGuestsVo.getDateArchivage());
        query += SearchUtil.addConstraintDate("o", "dateCreation", "=", couponManagmentItemGuestsVo.getDateCreation());
        query += SearchUtil.addConstraintMinMaxDate("o", "dateArchivage", couponManagmentItemGuestsVo.getDateArchivageMin(), couponManagmentItemGuestsVo.getDateArchivageMax());
        query += SearchUtil.addConstraintMinMaxDate("o", "dateCreation", couponManagmentItemGuestsVo.getDateCreationMin(), couponManagmentItemGuestsVo.getDateCreationMax());
        if (couponManagmentItemGuestsVo.getCouponManagmentVo() != null) {
            query += SearchUtil.addConstraint("o", "couponManagment.id", "=", couponManagmentItemGuestsVo.getCouponManagmentVo().getId());
        }

        if (couponManagmentItemGuestsVo.getGuestVo() != null) {
            query += SearchUtil.addConstraint("o", "guest.id", "=", couponManagmentItemGuestsVo.getGuestVo().getId());
        }


        return entityManager.createQuery(query).getResultList();
    }

    private void findGuest(CouponManagmentItemGuest couponManagmentItemGuest) {
        Guest loadedGuest = guestService.findById(couponManagmentItemGuest.getGuest().getId());

        if (loadedGuest == null) {
            return;
        }
        couponManagmentItemGuest.setGuest(loadedGuest);
    }

    private void findCouponManagment(CouponManagmentItemGuest couponManagmentItemGuest) {
        CouponManagment loadedCouponManagment = null;
        if (couponManagmentItemGuest.getCouponManagment() != null && couponManagmentItemGuest.getCouponManagment().getId() != null)
            loadedCouponManagment = couponManagmentService.findById(couponManagmentItemGuest.getCouponManagment().getId());

        if (loadedCouponManagment == null) {
            return;
        }
        couponManagmentItemGuest.setCouponManagment(loadedCouponManagment);
    }



    @Override
    @Transactional
    public void delete(List<CouponManagmentItemGuest> couponManagmentItemGuests) {
        if (ListUtil.isNotEmpty(couponManagmentItemGuests)) {
            couponManagmentItemGuests.forEach(e -> couponManagmentItemGuestDao.delete(e));
        }
    }

    @Override
    public void update(List<CouponManagmentItemGuest> couponManagmentItemGuests) {
        if (ListUtil.isNotEmpty(couponManagmentItemGuests)) {
            couponManagmentItemGuests.forEach(e -> couponManagmentItemGuestDao.save(e));
        }
    }

    @Override
    public List<CouponManagmentItemGuest> findByGuestId(Long id) {
        return couponManagmentItemGuestDao.findByGuestId(id);
    }

    @Override
    @Transactional
    public int deleteByGuestId(Long id) {
        return couponManagmentItemGuestDao.deleteByGuestId(id);
    }

    @Override
    public List<CouponManagmentItemGuest> findByCouponManagmentId(Long id) {
        return couponManagmentItemGuestDao.findByCouponManagmentId(id);
    }

    @Override
    @Transactional
    public int deleteByCouponManagmentId(Long id) {
        return couponManagmentItemGuestDao.deleteByCouponManagmentId(id);
    }

    @Override
    public CouponManagmentItemGuest archiver(CouponManagmentItemGuest couponManagmentItemGuest) {
        if (couponManagmentItemGuest.getArchive() == null) {
            couponManagmentItemGuest.setArchive(false);
        }
        couponManagmentItemGuest.setArchive(true);
        couponManagmentItemGuest.setDateArchivage(new Date());
        couponManagmentItemGuestDao.save(couponManagmentItemGuest);
        return couponManagmentItemGuest;

    }

    @Override
    public CouponManagmentItemGuest desarchiver(CouponManagmentItemGuest couponManagmentItemGuest) {
        if (couponManagmentItemGuest.getArchive() == null) {
            couponManagmentItemGuest.setArchive(false);
        }
        couponManagmentItemGuest.setArchive(false);
        couponManagmentItemGuestDao.save(couponManagmentItemGuest);
        return couponManagmentItemGuest;
    }
    
}
