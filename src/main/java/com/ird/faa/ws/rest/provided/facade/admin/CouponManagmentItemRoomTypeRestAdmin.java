package com.ird.faa.ws.rest.provided.facade.admin;

import com.ird.faa.bean.CouponManagmentItemRoomType;
import com.ird.faa.service.admin.facade.CouponManagmentItemRoomTypeAdminService;
import com.ird.faa.ws.rest.provided.converter.CouponManagmentItemRoomTypeConverter;
import com.ird.faa.ws.rest.provided.vo.CouponManagmentItemRoomTypeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("Manages couponManagmentItemRoomType services")
@RestController
@RequestMapping("api/admin/couponManagmentItemRoomType")
public class CouponManagmentItemRoomTypeRestAdmin {

    @Autowired
    private CouponManagmentItemRoomTypeAdminService couponManagmentItemRoomTypeService;

    @Autowired
    private CouponManagmentItemRoomTypeConverter couponManagmentItemRoomTypeConverter;


    @ApiOperation("Updates the specified  couponManagmentItemRoomType")
    @PutMapping("/")
    public CouponManagmentItemRoomTypeVo update(@RequestBody CouponManagmentItemRoomTypeVo couponManagmentItemRoomTypesVo) {
        CouponManagmentItemRoomType couponManagmentItemRoomType = couponManagmentItemRoomTypeConverter.toItem(couponManagmentItemRoomTypesVo);
        couponManagmentItemRoomType = couponManagmentItemRoomTypeService.update(couponManagmentItemRoomType);
        return couponManagmentItemRoomTypeConverter.toVo(couponManagmentItemRoomType);
    }

    @ApiOperation("Finds a list of all couponManagmentItemRoomTypes")
    @GetMapping("/")
    public List<CouponManagmentItemRoomTypeVo> findAll() {
        return couponManagmentItemRoomTypeConverter.toVo(couponManagmentItemRoomTypeService.findAll());
    }

    @ApiOperation("Finds a couponManagmentItemRoomType with associated lists by id")
    @GetMapping("/detail/id/{id}")
    public CouponManagmentItemRoomTypeVo findByIdWithAssociatedList(@PathVariable Long id) {
        return couponManagmentItemRoomTypeConverter.toVo(couponManagmentItemRoomTypeService.findByIdWithAssociatedList(id));
    }

    @ApiOperation("Search couponManagmentItemRoomType by a specific criteria")
    @PostMapping("/search")
    public List<CouponManagmentItemRoomTypeVo> findByCriteria(@RequestBody CouponManagmentItemRoomTypeVo couponManagmentItemRoomTypesVo) {
        return couponManagmentItemRoomTypeConverter.toVo(couponManagmentItemRoomTypeService.findByCriteria(couponManagmentItemRoomTypesVo));
    }

    @ApiOperation("Finds a couponManagmentItemRoomType by id")
    @GetMapping("/id/{id}")
    public CouponManagmentItemRoomTypeVo findById(@PathVariable Long id) {
        return couponManagmentItemRoomTypeConverter.toVo(couponManagmentItemRoomTypeService.findById(id));
    }

    @ApiOperation("Saves the specified  couponManagmentItemRoomType")
    @PostMapping("/")
    public CouponManagmentItemRoomTypeVo save(@RequestBody CouponManagmentItemRoomTypeVo couponManagmentItemRoomTypesVo) {
        CouponManagmentItemRoomType couponManagmentItemRoomType = couponManagmentItemRoomTypeConverter.toItem(couponManagmentItemRoomTypesVo);
        couponManagmentItemRoomType = couponManagmentItemRoomTypeService.save(couponManagmentItemRoomType);
        return couponManagmentItemRoomTypeConverter.toVo(couponManagmentItemRoomType);
    }

    @ApiOperation("Delete the specified couponManagmentItemRoomType")
    @DeleteMapping("/")
    public int delete(@RequestBody CouponManagmentItemRoomTypeVo couponManagmentItemRoomTypesVo) {
        CouponManagmentItemRoomType couponManagmentItemRoomType = couponManagmentItemRoomTypeConverter.toItem(couponManagmentItemRoomTypesVo);
        return couponManagmentItemRoomTypeService.delete(couponManagmentItemRoomType);
    }

    @ApiOperation("Deletes a couponManagmentItemRoomType by id")
    @DeleteMapping("/id/{id}")
    public int deleteById(@PathVariable Long id) {
        return couponManagmentItemRoomTypeService.deleteById(id);
    }


    @ApiOperation("find by roomType id")
    @GetMapping("/roomType/id/{id}")
    public List<CouponManagmentItemRoomType> findByRoomTypeId(@PathVariable Long id) {
        return couponManagmentItemRoomTypeService.findByRoomTypeId(id);
    }

    @ApiOperation("delete by roomType id")
    @DeleteMapping("/roomType/id/{id}")
    public int deleteByRoomTypeId(@PathVariable Long id) {
        return couponManagmentItemRoomTypeService.deleteByRoomTypeId(id);
    }

    @ApiOperation("find by paidService id")
    @GetMapping("/paidService/id/{id}")
    public List<CouponManagmentItemRoomType> findByCouponManagmentId(@PathVariable Long id) {
        return couponManagmentItemRoomTypeService.findByCouponManagmentId(id);
    }

    @ApiOperation("delete by paidService id")
    @DeleteMapping("/paidService/id/{id}")
    public int deleteByCouponManagmentId(@PathVariable Long id) {
        return couponManagmentItemRoomTypeService.deleteByCouponManagmentId(id);
    }


    @PutMapping("/archiver/")
    public CouponManagmentItemRoomTypeVo archiver(@RequestBody CouponManagmentItemRoomTypeVo couponManagmentItemRoomTypesVo) {
        CouponManagmentItemRoomType couponManagmentItemRoomType = couponManagmentItemRoomTypeService.archiver(couponManagmentItemRoomTypeConverter.toItem(couponManagmentItemRoomTypesVo));
        return couponManagmentItemRoomTypeConverter.toVo(couponManagmentItemRoomType);
    }

    @PutMapping("/desarchiver/")
    public CouponManagmentItemRoomTypeVo desarchiver(@RequestBody CouponManagmentItemRoomTypeVo couponManagmentItemRoomTypesVo) {
        CouponManagmentItemRoomType couponManagmentItemRoomType = couponManagmentItemRoomTypeService.desarchiver(couponManagmentItemRoomTypeConverter.toItem(couponManagmentItemRoomTypesVo));
        return couponManagmentItemRoomTypeConverter.toVo(couponManagmentItemRoomType);
    }

}
