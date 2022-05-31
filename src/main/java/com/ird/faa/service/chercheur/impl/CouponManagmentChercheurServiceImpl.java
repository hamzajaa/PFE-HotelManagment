package com.ird.faa.service.chercheur.impl;

import java.util.List;

import java.util.ArrayList;

import com.ird.faa.bean.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

import com.ird.faa.dao.CouponManagmentDao;
import com.ird.faa.service.chercheur.facade.CouponManagmentChercheurService;
import com.ird.faa.service.chercheur.facade.EmployeeChercheurService;
import com.ird.faa.service.chercheur.facade.RoomTypeChercheurService;
import com.ird.faa.service.chercheur.facade.CouponTypeChercheurService;
import com.ird.faa.service.chercheur.facade.PaidServiceChercheurService;

import com.ird.faa.ws.rest.provided.vo.CouponManagmentVo;
import com.ird.faa.service.util.*;

import com.ird.faa.service.core.impl.AbstractServiceImpl;

@Service
public class CouponManagmentChercheurServiceImpl extends AbstractServiceImpl<CouponManagment> implements CouponManagmentChercheurService {

    @Autowired
    private CouponManagmentDao couponManagmentDao;

    @Autowired
    private EmployeeChercheurService employeeService;
    @Autowired
    private RoomTypeChercheurService roomTypeService;
    @Autowired
    private CouponTypeChercheurService couponTypeService;
    @Autowired
    private PaidServiceChercheurService paidServiceService;


    @Autowired
    private EntityManager entityManager;


    @Override
    public List<CouponManagment> findAll() {
        String query = "SELECT o FROM CouponManagment o where 1=1 ";
        query += " ORDER BY o.couponFiPeriod";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<CouponManagment> findByCouponTypeCode(String code) {
        return couponManagmentDao.findByCouponTypeCode(code);
    }

    @Override
    @Transactional
    public int deleteByCouponTypeCode(String code) {
        return couponManagmentDao.deleteByCouponTypeCode(code);
    }

    @Override
    public List<CouponManagment> findByCouponTypeId(Long id) {
        return couponManagmentDao.findByCouponTypeId(id);
    }

    @Override
    @Transactional
    public int deleteByCouponTypeId(Long id) {
        return couponManagmentDao.deleteByCouponTypeId(id);
    }


    @Override
    public CouponManagment findById(Long id) {
        if (id == null) return null;
        return couponManagmentDao.getOne(id);
    }

    @Override
    public CouponManagment findByIdWithAssociatedList(Long id) {
        CouponManagment couponManagment = findById(id);
        findAssociatedLists(couponManagment);
        return couponManagment;
    }

    private void findAssociatedLists(CouponManagment couponManagment) {
        if (couponManagment != null && couponManagment.getId() != null) {
            List<Employee> employees = employeeService.findByCouponManagmentId(couponManagment.getId());
            couponManagment.setEmployees(employees);
            List<RoomType> roomTypes = roomTypeService.findByCouponManagmentId(couponManagment.getId());
            couponManagment.setRoomTypes(roomTypes);
            List<PaidService> paidServices = paidServiceService.findByCouponManagmentId(couponManagment.getId());
            couponManagment.setPaidServices(paidServices);
        }
    }

    private void deleteAssociatedLists(Long id) {
        if (id != null) {
            employeeService.deleteByCouponManagmentId(id);
            roomTypeService.deleteByCouponManagmentId(id);
            paidServiceService.deleteByCouponManagmentId(id);
        }
    }

    private void updateAssociatedLists(CouponManagment couponManagment) {
        if (couponManagment != null && couponManagment.getId() != null) {
            List
                    <List<Employee>> resultEmployees = employeeService.getToBeSavedAndToBeDeleted(employeeService.findByCouponManagmentId(couponManagment.getId()), couponManagment.getEmployees());
            employeeService.delete(resultEmployees.get(1));
            associateEmployee(couponManagment, resultEmployees.get(0));
            employeeService.update(resultEmployees.get(0));

            List
                    <List<RoomType>> resultRoomTypes = roomTypeService.getToBeSavedAndToBeDeleted(roomTypeService.findByCouponManagmentId(couponManagment.getId()), couponManagment.getRoomTypes());
            roomTypeService.delete(resultRoomTypes.get(1));
            associateRoomType(couponManagment, resultRoomTypes.get(0));
            roomTypeService.update(resultRoomTypes.get(0));

            List
                    <List<PaidService>> resultPaidServices = paidServiceService.getToBeSavedAndToBeDeleted(paidServiceService.findByCouponManagmentId(couponManagment.getId()), couponManagment.getPaidServices());
            paidServiceService.delete(resultPaidServices.get(1));
            associatePaidService(couponManagment, resultPaidServices.get(0));
            paidServiceService.update(resultPaidServices.get(0));

        }
    }

    @Transactional
    public int deleteById(Long id) {
        int res = 0;
        if (couponManagmentDao.findById(id).isPresent()) {
            deleteAssociatedLists(id);
            couponManagmentDao.deleteById(id);
            res = 1;
        }
        return res;
    }


    @Override
    public CouponManagment update(CouponManagment couponManagment) {
        CouponManagment foundedCouponManagment = findById(couponManagment.getId());
        if (foundedCouponManagment == null) return null;
        else {
            updateAssociatedLists(couponManagment);
            return couponManagmentDao.save(couponManagment);
        }
    }

    @Override
    public CouponManagment save(CouponManagment couponManagment) {

        CouponManagment result = null;


        findCouponType(couponManagment);

        CouponManagment savedCouponManagment = couponManagmentDao.save(couponManagment);

        saveEmployees(savedCouponManagment, couponManagment.getEmployees());
        saveRoomTypes(savedCouponManagment, couponManagment.getRoomTypes());
        savePaidServices(savedCouponManagment, couponManagment.getPaidServices());
        result = savedCouponManagment;

        return result;
    }

    @Override
    public List<CouponManagment> save(List<CouponManagment> couponManagments) {
        List<CouponManagment> list = new ArrayList<>();
        for (CouponManagment couponManagment : couponManagments) {
            list.add(save(couponManagment));
        }
        return list;
    }


    @Override
    @Transactional
    public int delete(CouponManagment couponManagment) {
        if (couponManagment.getId() == null) return -1;
        CouponManagment foundedCouponManagment = findById(couponManagment.getId());
        if (foundedCouponManagment == null) return -1;
        couponManagmentDao.delete(foundedCouponManagment);
        return 1;
    }


    public List<CouponManagment> findByCriteria(CouponManagmentVo couponManagmentVo) {

        String query = "SELECT o FROM CouponManagment o where 1=1 ";

        query += SearchUtil.addConstraint("o", "id", "=", couponManagmentVo.getId());
        query += SearchUtil.addConstraint("o", "title", "LIKE", couponManagmentVo.getTitle());
        query += SearchUtil.addConstraint("o", "description", "LIKE", couponManagmentVo.getDescription());
        query += SearchUtil.addConstraint("o", "image", "LIKE", couponManagmentVo.getImage());
        query += SearchUtil.addConstraintDate("o", "couponDePeriod", "=", couponManagmentVo.getCouponDePeriod());
        query += SearchUtil.addConstraintDate("o", "couponFiPeriod", "=", couponManagmentVo.getCouponFiPeriod());
        query += SearchUtil.addConstraint("o", "couponCode", "LIKE", couponManagmentVo.getCouponCode());
        query += SearchUtil.addConstraint("o", "couponValue", "=", couponManagmentVo.getCouponValue());
        query += SearchUtil.addConstraint("o", "minAmount", "=", couponManagmentVo.getMinAmount());
        query += SearchUtil.addConstraint("o", "maxAmount", "=", couponManagmentVo.getMaxAmount());
        query += SearchUtil.addConstraint("o", "limitPerUser", "=", couponManagmentVo.getLimitPerUser());
        query += SearchUtil.addConstraint("o", "limitPerCoupon", "=", couponManagmentVo.getLimitPerCoupon());
        query += SearchUtil.addConstraintMinMaxDate("o", "couponDePeriod", couponManagmentVo.getCouponDePeriodMin(), couponManagmentVo.getCouponDePeriodMax());
        query += SearchUtil.addConstraintMinMaxDate("o", "couponFiPeriod", couponManagmentVo.getCouponFiPeriodMin(), couponManagmentVo.getCouponFiPeriodMax());
        query += SearchUtil.addConstraintMinMax("o", "couponValue", couponManagmentVo.getCouponValueMin(), couponManagmentVo.getCouponValueMax());
        query += SearchUtil.addConstraintMinMax("o", "minAmount", couponManagmentVo.getMinAmountMin(), couponManagmentVo.getMinAmountMax());
        query += SearchUtil.addConstraintMinMax("o", "maxAmount", couponManagmentVo.getMaxAmountMin(), couponManagmentVo.getMaxAmountMax());
        query += SearchUtil.addConstraintMinMax("o", "limitPerUser", couponManagmentVo.getLimitPerUserMin(), couponManagmentVo.getLimitPerUserMax());
        query += SearchUtil.addConstraintMinMax("o", "limitPerCoupon", couponManagmentVo.getLimitPerCouponMin(), couponManagmentVo.getLimitPerCouponMax());
        if (couponManagmentVo.getCouponTypeVo() != null) {
            query += SearchUtil.addConstraint("o", "couponType.id", "=", couponManagmentVo.getCouponTypeVo().getId());
            query += SearchUtil.addConstraint("o", "couponType.code", "LIKE", couponManagmentVo.getCouponTypeVo().getCode());
        }

        query += " ORDER BY o.couponFiPeriod";
        return entityManager.createQuery(query).getResultList();
    }

    private void saveEmployees(CouponManagment couponManagment, List<Employee> employees) {

        if (ListUtil.isNotEmpty(couponManagment.getEmployees())) {
            List<Employee> savedEmployees = new ArrayList<>();
            employees.forEach(element -> {
                element.setCouponManagment(couponManagment);
                employeeService.save(element);
            });
            couponManagment.setEmployees(savedEmployees);
        }
    }

    private void saveRoomTypes(CouponManagment couponManagment, List<RoomType> roomTypes) {

        if (ListUtil.isNotEmpty(couponManagment.getRoomTypes())) {
            List<RoomType> savedRoomTypes = new ArrayList<>();
            roomTypes.forEach(element -> {
                element.setCouponManagment(couponManagment);
                roomTypeService.save(element);
            });
            couponManagment.setRoomTypes(savedRoomTypes);
        }
    }

    private void savePaidServices(CouponManagment couponManagment, List<PaidService> paidServices) {

        if (ListUtil.isNotEmpty(couponManagment.getPaidServices())) {
            List<PaidService> savedPaidServices = new ArrayList<>();
            paidServices.forEach(element -> {
                element.setCouponManagment(couponManagment);
                paidServiceService.save(element);
            });
            couponManagment.setPaidServices(savedPaidServices);
        }
    }

    private void findCouponType(CouponManagment couponManagment) {
        CouponType loadedCouponType = couponTypeService.findByIdOrCode(couponManagment.getCouponType());

        if (loadedCouponType == null) {
            return;
        }
        couponManagment.setCouponType(loadedCouponType);
    }

    @Override
    @Transactional
    public void delete(List<CouponManagment> couponManagments) {
        if (ListUtil.isNotEmpty(couponManagments)) {
            couponManagments.forEach(e -> couponManagmentDao.delete(e));
        }
    }

    @Override
    public void update(List<CouponManagment> couponManagments) {
        if (ListUtil.isNotEmpty(couponManagments)) {
            couponManagments.forEach(e -> couponManagmentDao.save(e));
        }
    }

    private void associateEmployee(CouponManagment couponManagment, List<Employee> employee) {
        if (ListUtil.isNotEmpty(employee)) {
            employee.forEach(e -> e.setCouponManagment(couponManagment));
        }
    }

    private void associateRoomType(CouponManagment couponManagment, List<RoomType> roomType) {
        if (ListUtil.isNotEmpty(roomType)) {
            roomType.forEach(e -> e.setCouponManagment(couponManagment));
        }
    }

    private void associatePaidService(CouponManagment couponManagment, List<PaidService> paidService) {
        if (ListUtil.isNotEmpty(paidService)) {
            paidService.forEach(e -> e.setCouponManagment(couponManagment));
        }
    }


}
