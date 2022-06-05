package com.ird.faa.service.admin.impl;

import com.ird.faa.bean.PaidService;
import com.ird.faa.bean.PaidServiceItemType;
import com.ird.faa.bean.RoomType;
import com.ird.faa.dao.PaidServiceItemTypeDao;
import com.ird.faa.service.admin.facade.PaidServiceAdminService;
import com.ird.faa.service.admin.facade.PaidServiceItemTypeAdminService;
import com.ird.faa.service.admin.facade.RoomTypeAdminService;
import com.ird.faa.service.core.facade.ArchivableService;
import com.ird.faa.service.core.impl.AbstractServiceImpl;
import com.ird.faa.service.util.ListUtil;
import com.ird.faa.service.util.SearchUtil;
import com.ird.faa.ws.rest.provided.vo.PaidServiceItemTypeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PaidServiceItemTypeAdminServiceImpl extends AbstractServiceImpl<PaidServiceItemType> implements PaidServiceItemTypeAdminService {

    @Autowired
    private PaidServiceItemTypeDao paidServiceItemTypeDao;

    @Autowired
    private ArchivableService<PaidServiceItemType> archivableService;

    @Autowired
    private RoomTypeAdminService roomTypeService;

    @Autowired
    private PaidServiceAdminService paidServiceService;

    @Autowired
    private EntityManager entityManager;


    @Override
    public List<PaidServiceItemType> findAll() {
        return paidServiceItemTypeDao.findAll();
    }

    @Override
    public PaidServiceItemType findById(Long id) {
        if (id == null) return null;
        return paidServiceItemTypeDao.getOne(id);
    }

    @Override
    public PaidServiceItemType findByIdWithAssociatedList(Long id) {
        return findById(id);
    }

    @Override
    @Transactional
    public int deleteById(Long id) {
        int res = 0;
        if (paidServiceItemTypeDao.findById(id).isPresent()) {
            paidServiceItemTypeDao.deleteById(id);
            res = 1;
        }
        return res;
    }

    @Override
    public PaidServiceItemType save(PaidServiceItemType paidServiceItemType) {
        prepareSave(paidServiceItemType);


        findRoomType(paidServiceItemType);
        findPaidService(paidServiceItemType);

        return paidServiceItemTypeDao.save(paidServiceItemType);
    }

    private void prepareSave(PaidServiceItemType paidServiceItemType) {
        paidServiceItemType.setDateCreation(new Date());
        if (paidServiceItemType.getDateArchivage() == null)
            paidServiceItemType.setDateArchivage(new Date());
        if (paidServiceItemType.getArchive() == null)
            paidServiceItemType.setArchive(false);


    }

    @Override
    public List<PaidServiceItemType> save(List<PaidServiceItemType> paidServiceItemTypes) {
        List<PaidServiceItemType> list = new ArrayList<>();
        for (PaidServiceItemType paidServiceItemType : paidServiceItemTypes) {
            list.add(save(paidServiceItemType));
        }
        return list;
    }

    @Override
    public PaidServiceItemType update(PaidServiceItemType paidServiceItemType) {
        PaidServiceItemType foundedPaidServiceItemType = findById(paidServiceItemType.getId());
        if (foundedPaidServiceItemType == null) return null;
        else {
            archivableService.prepare(paidServiceItemType);
            return paidServiceItemTypeDao.save(paidServiceItemType);
        }
    }

    @Override
    @Transactional
    public int delete(PaidServiceItemType paidServiceItemType) {
        if (paidServiceItemType.getId() == null) return -1;
        PaidServiceItemType foundedPaidServiceItemType = findById(paidServiceItemType.getId());
        if (foundedPaidServiceItemType == null) return -1;
        paidServiceItemTypeDao.delete(foundedPaidServiceItemType);
        return 1;
    }

    public List<PaidServiceItemType> findByCriteria(PaidServiceItemTypeVo paidServiceItemTypesVo) {
        String query = "SELECT o FROM PaidServiceItemType o where 1=1 ";

        query += SearchUtil.addConstraint("o", "id", "=", paidServiceItemTypesVo.getId());
        query += SearchUtil.addConstraint("o", "archive", "=", paidServiceItemTypesVo.getArchive());
        query += SearchUtil.addConstraintDate("o", "dateArchivage", "=", paidServiceItemTypesVo.getDateArchivage());
        query += SearchUtil.addConstraintDate("o", "dateCreation", "=", paidServiceItemTypesVo.getDateCreation());
        query += SearchUtil.addConstraintMinMaxDate("o", "dateArchivage", paidServiceItemTypesVo.getDateArchivageMin(), paidServiceItemTypesVo.getDateArchivageMax());
        query += SearchUtil.addConstraintMinMaxDate("o", "dateCreation", paidServiceItemTypesVo.getDateCreationMin(), paidServiceItemTypesVo.getDateCreationMax());
        if (paidServiceItemTypesVo.getPaidServiceVo() != null) {
            query += SearchUtil.addConstraint("o", "paidService.id", "=", paidServiceItemTypesVo.getPaidServiceVo().getId());
        }

        if (paidServiceItemTypesVo.getRoomTypeVo() != null) {
            query += SearchUtil.addConstraint("o", "roomType.id", "=", paidServiceItemTypesVo.getRoomTypeVo().getId());
        }


        return entityManager.createQuery(query).getResultList();
    }

    private void findRoomType(PaidServiceItemType paidServiceItemType) {
        RoomType loadedRoomType = roomTypeService.findByIdOrShortCode(paidServiceItemType.getRoomType());

        if (loadedRoomType == null) {
            return;
        }
        paidServiceItemType.setRoomType(loadedRoomType);
    }

    private void findPaidService(PaidServiceItemType paidServiceItemType) {
        PaidService loadedPaidService = null;
        if (paidServiceItemType.getPaidService() != null && paidServiceItemType.getPaidService().getId() != null)
            loadedPaidService = paidServiceService.findById(paidServiceItemType.getPaidService().getId());

        if (loadedPaidService == null) {
            return;
        }
        paidServiceItemType.setPaidService(loadedPaidService);
    }



    @Override
    @Transactional
    public void delete(List<PaidServiceItemType> paidServiceItemTypes) {
        if (ListUtil.isNotEmpty(paidServiceItemTypes)) {
            paidServiceItemTypes.forEach(e -> paidServiceItemTypeDao.delete(e));
        }
    }

    @Override
    public void update(List<PaidServiceItemType> paidServiceItemTypes) {
        if (ListUtil.isNotEmpty(paidServiceItemTypes)) {
            paidServiceItemTypes.forEach(e -> paidServiceItemTypeDao.save(e));
        }
    }

    @Override
    public List<PaidServiceItemType> findByRoomTypeId(Long id) {
        return paidServiceItemTypeDao.findByRoomTypeId(id);
    }

    @Override
    @Transactional
    public int deleteByRoomTypeId(Long id) {
        return paidServiceItemTypeDao.deleteByRoomTypeId(id);
    }

    @Override
    public List<PaidServiceItemType> findByPaidServiceId(Long id) {
        return paidServiceItemTypeDao.findByPaidServiceId(id);
    }

    @Override
    @Transactional
    public int deleteByPaidServiceId(Long id) {
        return paidServiceItemTypeDao.deleteByPaidServiceId(id);
    }

    @Override
    public PaidServiceItemType archiver(PaidServiceItemType paidServiceItemType) {
        if (paidServiceItemType.getArchive() == null) {
            paidServiceItemType.setArchive(false);
        }
        paidServiceItemType.setArchive(true);
        paidServiceItemType.setDateArchivage(new Date());
        paidServiceItemTypeDao.save(paidServiceItemType);
        return paidServiceItemType;

    }

    @Override
    public PaidServiceItemType desarchiver(PaidServiceItemType paidServiceItemType) {
        if (paidServiceItemType.getArchive() == null) {
            paidServiceItemType.setArchive(false);
        }
        paidServiceItemType.setArchive(false);
        paidServiceItemTypeDao.save(paidServiceItemType);
        return paidServiceItemType;
    }
}
