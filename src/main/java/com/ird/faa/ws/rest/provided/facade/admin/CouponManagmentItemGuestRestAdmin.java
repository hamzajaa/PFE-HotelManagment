package com.ird.faa.ws.rest.provided.facade.admin;

import com.ird.faa.bean.CouponManagmentItemGuest;
import com.ird.faa.service.admin.facade.CouponManagmentItemGuestAdminService;
import com.ird.faa.ws.rest.provided.converter.CouponManagmentItemGuestConverter;
import com.ird.faa.ws.rest.provided.vo.CouponManagmentItemGuestVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("Manages couponManagmentItemGuest services")
@RestController
@RequestMapping("api/admin/couponManagmentItemGuest")
public class CouponManagmentItemGuestRestAdmin {

    @Autowired
    private CouponManagmentItemGuestAdminService couponManagmentItemGuestService;

    @Autowired
    private CouponManagmentItemGuestConverter couponManagmentItemGuestConverter;


    @ApiOperation("Updates the specified  couponManagmentItemGuest")
    @PutMapping("/")
    public CouponManagmentItemGuestVo update(@RequestBody CouponManagmentItemGuestVo couponManagmentItemGuestsVo) {
        CouponManagmentItemGuest couponManagmentItemGuest = couponManagmentItemGuestConverter.toItem(couponManagmentItemGuestsVo);
        couponManagmentItemGuest = couponManagmentItemGuestService.update(couponManagmentItemGuest);
        return couponManagmentItemGuestConverter.toVo(couponManagmentItemGuest);
    }

    @ApiOperation("Finds a list of all couponManagmentItemGuests")
    @GetMapping("/")
    public List<CouponManagmentItemGuestVo> findAll() {
        return couponManagmentItemGuestConverter.toVo(couponManagmentItemGuestService.findAll());
    }

    @ApiOperation("Finds a couponManagmentItemGuest with associated lists by id")
    @GetMapping("/detail/id/{id}")
    public CouponManagmentItemGuestVo findByIdWithAssociatedList(@PathVariable Long id) {
        return couponManagmentItemGuestConverter.toVo(couponManagmentItemGuestService.findByIdWithAssociatedList(id));
    }

    @ApiOperation("Search couponManagmentItemGuest by a specific criteria")
    @PostMapping("/search")
    public List<CouponManagmentItemGuestVo> findByCriteria(@RequestBody CouponManagmentItemGuestVo couponManagmentItemGuestsVo) {
        return couponManagmentItemGuestConverter.toVo(couponManagmentItemGuestService.findByCriteria(couponManagmentItemGuestsVo));
    }

    @ApiOperation("Finds a couponManagmentItemGuest by id")
    @GetMapping("/id/{id}")
    public CouponManagmentItemGuestVo findById(@PathVariable Long id) {
        return couponManagmentItemGuestConverter.toVo(couponManagmentItemGuestService.findById(id));
    }

    @ApiOperation("Saves the specified  couponManagmentItemGuest")
    @PostMapping("/")
    public CouponManagmentItemGuestVo save(@RequestBody CouponManagmentItemGuestVo couponManagmentItemGuestsVo) {
        CouponManagmentItemGuest couponManagmentItemGuest = couponManagmentItemGuestConverter.toItem(couponManagmentItemGuestsVo);
        couponManagmentItemGuest = couponManagmentItemGuestService.save(couponManagmentItemGuest);
        return couponManagmentItemGuestConverter.toVo(couponManagmentItemGuest);
    }

    @ApiOperation("Delete the specified couponManagmentItemGuest")
    @DeleteMapping("/")
    public int delete(@RequestBody CouponManagmentItemGuestVo couponManagmentItemGuestsVo) {
        CouponManagmentItemGuest couponManagmentItemGuest = couponManagmentItemGuestConverter.toItem(couponManagmentItemGuestsVo);
        return couponManagmentItemGuestService.delete(couponManagmentItemGuest);
    }

    @ApiOperation("Deletes a couponManagmentItemGuest by id")
    @DeleteMapping("/id/{id}")
    public int deleteById(@PathVariable Long id) {
        return couponManagmentItemGuestService.deleteById(id);
    }


    @ApiOperation("find by roomType id")
    @GetMapping("/roomType/id/{id}")
    public List<CouponManagmentItemGuest> findByGuestId(@PathVariable Long id) {
        return couponManagmentItemGuestService.findByGuestId(id);
    }

    @ApiOperation("delete by roomType id")
    @DeleteMapping("/roomType/id/{id}")
    public int deleteByGuestId(@PathVariable Long id) {
        return couponManagmentItemGuestService.deleteByGuestId(id);
    }

    @ApiOperation("find by paidService id")
    @GetMapping("/paidService/id/{id}")
    public List<CouponManagmentItemGuest> findByCouponManagmentId(@PathVariable Long id) {
        return couponManagmentItemGuestService.findByCouponManagmentId(id);
    }

    @ApiOperation("delete by paidService id")
    @DeleteMapping("/paidService/id/{id}")
    public int deleteByCouponManagmentId(@PathVariable Long id) {
        return couponManagmentItemGuestService.deleteByCouponManagmentId(id);
    }


    @PutMapping("/archiver/")
    public CouponManagmentItemGuestVo archiver(@RequestBody CouponManagmentItemGuestVo couponManagmentItemGuestsVo) {
        CouponManagmentItemGuest couponManagmentItemGuest = couponManagmentItemGuestService.archiver(couponManagmentItemGuestConverter.toItem(couponManagmentItemGuestsVo));
        return couponManagmentItemGuestConverter.toVo(couponManagmentItemGuest);
    }

    @PutMapping("/desarchiver/")
    public CouponManagmentItemGuestVo desarchiver(@RequestBody CouponManagmentItemGuestVo couponManagmentItemGuestsVo) {
        CouponManagmentItemGuest couponManagmentItemGuest = couponManagmentItemGuestService.desarchiver(couponManagmentItemGuestConverter.toItem(couponManagmentItemGuestsVo));
        return couponManagmentItemGuestConverter.toVo(couponManagmentItemGuest);
    }

}
