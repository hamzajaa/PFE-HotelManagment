package com.ird.faa.service.employee.impl;

import com.ird.faa.bean.PaidService;
import com.ird.faa.bean.PriceType;
import com.ird.faa.dao.PaidServiceDao;
import com.ird.faa.service.core.facade.ArchivableService;
import com.ird.faa.service.core.impl.AbstractServiceImpl;
import com.ird.faa.service.employee.facade.PaidServiceEmployeeService;
import com.ird.faa.service.employee.facade.PriceTypeEmployeeService;
import com.ird.faa.service.employee.facade.RoomTypeEmployeeService;
import com.ird.faa.service.util.ListUtil;
import com.ird.faa.service.util.SearchUtil;
import com.ird.faa.service.util.StringUtil;
import com.ird.faa.ws.rest.provided.vo.PaidServiceVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PaidServiceEmployeeServiceImpl extends AbstractServiceImpl<PaidService> implements PaidServiceEmployeeService {

    @Autowired
    private PaidServiceDao paidServiceDao;

    @Autowired
    private ArchivableService<PaidService> archivableService;
    @Autowired
    private RoomTypeEmployeeService roomTypeService;
    @Autowired
    private PriceTypeEmployeeService priceTypeService;


    @Autowired
    private EntityManager entityManager;


    @Override
    public List<PaidService> findAll() {
        return paidServiceDao.findAll();
    }

    @Override
    public List<PaidService> findByPriceTypeCode(String code) {
        return paidServiceDao.findByPriceTypeCode(code);
    }

    @Override
    @Transactional
    public int deleteByPriceTypeCode(String code) {
        return paidServiceDao.deleteByPriceTypeCode(code);
    }

    @Override
    public List<PaidService> findByPriceTypeId(Long id) {
        return paidServiceDao.findByPriceTypeId(id);
    }

    @Override
    @Transactional
    public int deleteByPriceTypeId(Long id) {
        return paidServiceDao.deleteByPriceTypeId(id);
    }

    @Override
    public PaidService findByCode(String code) {
        if (code == null) return null;
        return paidServiceDao.findByCode(code);
    }

    @Override
    @Transactional
    public int deleteByCode(String code) {
        return paidServiceDao.deleteByCode(code);
    }

    @Override
    public PaidService findByIdOrCode(PaidService paidService) {
        PaidService resultat = null;
        if (paidService != null) {
            if (StringUtil.isNotEmpty(paidService.getId())) {
                resultat = paidServiceDao.getOne(paidService.getId());
            } else if (StringUtil.isNotEmpty(paidService.getCode())) {
                resultat = paidServiceDao.findByCode(paidService.getCode());
            }
        }
        return resultat;
    }

    @Override
    public PaidService findById(Long id) {
        if (id == null) return null;
        return paidServiceDao.getOne(id);
    }

    @Override
    public PaidService findByIdWithAssociatedList(Long id) {
        PaidService paidService = findById(id);
//        findAssociatedLists(paidService);
        return paidService;
    }

    @Override
    public PaidService archiver(PaidService paidService) {
        if (paidService.getArchive() == null) {
            paidService.setArchive(false);
        }
        paidService.setArchive(true);
        paidService.setDateArchivage(new Date());
        paidServiceDao.save(paidService);
        return paidService;

    }

    @Override
    public PaidService desarchiver(PaidService paidService) {
        if (paidService.getArchive() == null) {
            paidService.setArchive(false);
        }
        paidService.setArchive(false);
        paidServiceDao.save(paidService);
        return paidService;
    }

    @Override
    public List<PaidService> findByCouponManagmentId(Long id) {
        return null;
    }

    @Override
    public void deleteByCouponManagmentId(Long id) {

    }


    //    private void findAssociatedLists(PaidService paidService){
//    if(paidService!=null && paidService.getId() != null) {
//            List<RoomType> roomTypes = roomTypeService.findByPaidServiceId(paidService.getId());
//            paidService.setRoomTypes(roomTypes);
//    }
//    }
    private void deleteAssociatedLists(Long id) {
        if (id != null) {
            roomTypeService.deleteByPaidServiceId(id);
        }
    }

//    private void updateAssociatedLists(PaidService paidService) {
//        if (paidService != null && paidService.getId() != null) {
//            List
//                    <List<RoomType>> resultRoomTypes = roomTypeService.getToBeSavedAndToBeDeleted(roomTypeService.findByPaidServiceId(paidService.getId()), paidService.getRoomTypes());
//            roomTypeService.delete(resultRoomTypes.get(1));
//            associateRoomType(paidService, resultRoomTypes.get(0));
//            roomTypeService.update(resultRoomTypes.get(0));
//
//        }
//    }

    @Transactional
    public int deleteById(Long id) {
        int res = 0;
        if (paidServiceDao.findById(id).isPresent()) {
            deleteAssociatedLists(id);
            paidServiceDao.deleteById(id);
            res = 1;
        }
        return res;
    }


    @Override
    public PaidService update(PaidService paidService) {
        PaidService foundedPaidService = findById(paidService.getId());
        if (foundedPaidService == null) return null;
        else {
            archivableService.prepare(paidService);
//            updateAssociatedLists(paidService);
            return paidServiceDao.save(paidService);
        }
    }

    private void prepareSave(PaidService paidService) {
        paidService.setDateCreation(new Date());
        if (paidService.getDateArchivage() == null)
            paidService.setDateArchivage(new Date());
        if (paidService.getActive() == null)
            paidService.setActive(false);
        if (paidService.getArchive() == null)
            paidService.setArchive(false);


    }

    @Override
    public PaidService save(PaidService paidService) {
        prepareSave(paidService);

        PaidService result = null;
        PaidService foundedPaidService = findByCode(paidService.getCode());
        if (foundedPaidService == null) {


            findPriceType(paidService);

            PaidService savedPaidService = paidServiceDao.save(paidService);

//            saveRoomTypes(savedPaidService, paidService.getRoomTypes());
            result = savedPaidService;
        }

        return result;
    }

    @Override
    public List<PaidService> save(List<PaidService> paidServices) {
        List<PaidService> list = new ArrayList<>();
        for (PaidService paidService : paidServices) {
            list.add(save(paidService));
        }
        return list;
    }


    @Override
    @Transactional
    public int delete(PaidService paidService) {
        if (paidService.getCode() == null) return -1;

        PaidService foundedPaidService = findByCode(paidService.getCode());
        if (foundedPaidService == null) return -1;
        paidServiceDao.delete(foundedPaidService);
        return 1;
    }


    public List<PaidService> findByCriteria(PaidServiceVo paidServiceVo) {

        String query = "SELECT o FROM PaidService o where 1=1 ";

        query += SearchUtil.addConstraint("o", "id", "=", paidServiceVo.getId());
        query += SearchUtil.addConstraint("o", "title", "LIKE", paidServiceVo.getTitle());
        query += SearchUtil.addConstraint("o", "code", "LIKE", paidServiceVo.getCode());
        query += SearchUtil.addConstraint("o", "price", "=", paidServiceVo.getPrice());
        query += SearchUtil.addConstraint("o", "description", "LIKE", paidServiceVo.getDescription());
        query += SearchUtil.addConstraint("o", "active", "=", paidServiceVo.getActive());
        query += SearchUtil.addConstraint("o", "archive", "=", paidServiceVo.getArchive());
        query += SearchUtil.addConstraintDate("o", "dateArchivage", "=", paidServiceVo.getDateArchivage());
        query += SearchUtil.addConstraintDate("o", "dateCreation", "=", paidServiceVo.getDateCreation());
        query += SearchUtil.addConstraintMinMax("o", "price", paidServiceVo.getPriceMin(), paidServiceVo.getPriceMax());
        query += SearchUtil.addConstraintMinMaxDate("o", "dateArchivage", paidServiceVo.getDateArchivageMin(), paidServiceVo.getDateArchivageMax());
        query += SearchUtil.addConstraintMinMaxDate("o", "dateCreation", paidServiceVo.getDateCreationMin(), paidServiceVo.getDateCreationMax());
        if (paidServiceVo.getPriceTypeVo() != null) {
            query += SearchUtil.addConstraint("o", "priceType.id", "=", paidServiceVo.getPriceTypeVo().getId());
            query += SearchUtil.addConstraint("o", "priceType.code", "LIKE", paidServiceVo.getPriceTypeVo().getCode());
        }

        return entityManager.createQuery(query).getResultList();
    }

//    private void saveRoomTypes(PaidService paidService, List<RoomType> roomTypes) {
//
//        if (ListUtil.isNotEmpty(paidService.getRoomTypes())) {
//            List<RoomType> savedRoomTypes = new ArrayList<>();
//            roomTypes.forEach(element -> {
//                element.setPaidService(paidService);
//                roomTypeService.save(element);
//            });
//            paidService.setRoomTypes(savedRoomTypes);
//        }
//    }

    private void findPriceType(PaidService paidService) {
        PriceType loadedPriceType = priceTypeService.findByIdOrCode(paidService.getPriceType());

        if (loadedPriceType == null) {
            return;
        }
        paidService.setPriceType(loadedPriceType);
    }

    @Override
    @Transactional
    public void delete(List<PaidService> paidServices) {
        if (ListUtil.isNotEmpty(paidServices)) {
            paidServices.forEach(e -> paidServiceDao.delete(e));
        }
    }

    @Override
    public void update(List<PaidService> paidServices) {
        if (ListUtil.isNotEmpty(paidServices)) {
            paidServices.forEach(e -> paidServiceDao.save(e));
        }
    }

//    private void associateRoomType(PaidService paidService, List<RoomType> roomType) {
//        if (ListUtil.isNotEmpty(roomType)) {
//            roomType.forEach(e -> e.setPaidService(paidService));
//        }
//    }


}
