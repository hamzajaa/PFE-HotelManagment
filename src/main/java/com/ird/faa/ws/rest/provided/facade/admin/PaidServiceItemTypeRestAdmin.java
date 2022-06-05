package com.ird.faa.ws.rest.provided.facade.admin;

import com.ird.faa.bean.PaidServiceItemType;
import com.ird.faa.service.admin.facade.PaidServiceItemTypeAdminService;
import com.ird.faa.ws.rest.provided.converter.PaidServiceItemTypeConverter;
import com.ird.faa.ws.rest.provided.vo.PaidServiceItemTypeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("Manages paidServiceItemType services")
@RestController
@RequestMapping("api/admin/paidServiceItemType")
public class PaidServiceItemTypeRestAdmin {

    @Autowired
    private PaidServiceItemTypeAdminService paidServiceItemTypeService;

    @Autowired
    private PaidServiceItemTypeConverter paidServiceItemTypeConverter;


    @ApiOperation("Updates the specified  paidServiceItemType")
    @PutMapping("/")
    public PaidServiceItemTypeVo update(@RequestBody PaidServiceItemTypeVo paidServiceItemTypesVo) {
        PaidServiceItemType paidServiceItemType = paidServiceItemTypeConverter.toItem(paidServiceItemTypesVo);
        paidServiceItemType = paidServiceItemTypeService.update(paidServiceItemType);
        return paidServiceItemTypeConverter.toVo(paidServiceItemType);
    }

    @ApiOperation("Finds a list of all paidServiceItemTypes")
    @GetMapping("/")
    public List<PaidServiceItemTypeVo> findAll() {
        return paidServiceItemTypeConverter.toVo(paidServiceItemTypeService.findAll());
    }

    @ApiOperation("Finds a paidServiceItemType with associated lists by id")
    @GetMapping("/detail/id/{id}")
    public PaidServiceItemTypeVo findByIdWithAssociatedList(@PathVariable Long id) {
        return paidServiceItemTypeConverter.toVo(paidServiceItemTypeService.findByIdWithAssociatedList(id));
    }

    @ApiOperation("Search paidServiceItemType by a specific criteria")
    @PostMapping("/search")
    public List<PaidServiceItemTypeVo> findByCriteria(@RequestBody PaidServiceItemTypeVo paidServiceItemTypesVo) {
        return paidServiceItemTypeConverter.toVo(paidServiceItemTypeService.findByCriteria(paidServiceItemTypesVo));
    }

    @ApiOperation("Finds a paidServiceItemType by id")
    @GetMapping("/id/{id}")
    public PaidServiceItemTypeVo findById(@PathVariable Long id) {
        return paidServiceItemTypeConverter.toVo(paidServiceItemTypeService.findById(id));
    }

    @ApiOperation("Saves the specified  paidServiceItemType")
    @PostMapping("/")
    public PaidServiceItemTypeVo save(@RequestBody PaidServiceItemTypeVo paidServiceItemTypesVo) {
        PaidServiceItemType paidServiceItemType = paidServiceItemTypeConverter.toItem(paidServiceItemTypesVo);
        paidServiceItemType = paidServiceItemTypeService.save(paidServiceItemType);
        return paidServiceItemTypeConverter.toVo(paidServiceItemType);
    }

    @ApiOperation("Delete the specified paidServiceItemType")
    @DeleteMapping("/")
    public int delete(@RequestBody PaidServiceItemTypeVo paidServiceItemTypesVo) {
        PaidServiceItemType paidServiceItemType = paidServiceItemTypeConverter.toItem(paidServiceItemTypesVo);
        return paidServiceItemTypeService.delete(paidServiceItemType);
    }

    @ApiOperation("Deletes a paidServiceItemType by id")
    @DeleteMapping("/id/{id}")
    public int deleteById(@PathVariable Long id) {
        return paidServiceItemTypeService.deleteById(id);
    }
    

    @ApiOperation("find by roomType id")
    @GetMapping("/roomType/id/{id}")
    public List<PaidServiceItemType> findByRoomTypeId(@PathVariable Long id) {
        return paidServiceItemTypeService.findByRoomTypeId(id);
    }

    @ApiOperation("delete by roomType id")
    @DeleteMapping("/roomType/id/{id}")
    public int deleteByRoomTypeId(@PathVariable Long id) {
        return paidServiceItemTypeService.deleteByRoomTypeId(id);
    }

    @ApiOperation("find by paidService id")
    @GetMapping("/paidService/id/{id}")
    public List<PaidServiceItemType> findByPaidServiceId(@PathVariable Long id) {
        return paidServiceItemTypeService.findByPaidServiceId(id);
    }

    @ApiOperation("delete by paidService id")
    @DeleteMapping("/paidService/id/{id}")
    public int deleteByPaidServiceId(@PathVariable Long id) {
        return paidServiceItemTypeService.deleteByPaidServiceId(id);
    }
    

    @PutMapping("/archiver/")
    public PaidServiceItemTypeVo archiver(@RequestBody PaidServiceItemTypeVo paidServiceItemTypesVo) {
        PaidServiceItemType paidServiceItemType = paidServiceItemTypeService.archiver(paidServiceItemTypeConverter.toItem(paidServiceItemTypesVo));
        return paidServiceItemTypeConverter.toVo(paidServiceItemType);
    }

    @PutMapping("/desarchiver/")
    public PaidServiceItemTypeVo desarchiver(@RequestBody PaidServiceItemTypeVo paidServiceItemTypesVo) {
        PaidServiceItemType paidServiceItemType = paidServiceItemTypeService.desarchiver(paidServiceItemTypeConverter.toItem(paidServiceItemTypesVo));
        return paidServiceItemTypeConverter.toVo(paidServiceItemType);
    }
    
}
