package com.ird.faa.ws.rest.provided.facade.admin;

import com.ird.faa.bean.RoomTypeItemAmenity;
import com.ird.faa.service.admin.facade.RoomTypeItemAmenityAdminService;
import com.ird.faa.ws.rest.provided.converter.RoomTypeItemAmenityConverter;
import com.ird.faa.ws.rest.provided.vo.RoomTypeItemAmenityVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("Manages roomTypeItemAmenity services")
@RestController
@RequestMapping("api/admin/roomTypeItemAmenity")
public class RoomTypeItemAmenityRestAdmin {

    @Autowired
    private RoomTypeItemAmenityAdminService roomTypeItemAmenityService;

    @Autowired
    private RoomTypeItemAmenityConverter roomTypeItemAmenityConverter;


    @ApiOperation("Updates the specified  roomTypeItemAmenity")
    @PutMapping("/")
    public RoomTypeItemAmenityVo update(@RequestBody RoomTypeItemAmenityVo roomTypeItemAmenityVo) {
        RoomTypeItemAmenity roomTypeItemAmenity = roomTypeItemAmenityConverter.toItem(roomTypeItemAmenityVo);
        roomTypeItemAmenity = roomTypeItemAmenityService.update(roomTypeItemAmenity);
        return roomTypeItemAmenityConverter.toVo(roomTypeItemAmenity);
    }

    @ApiOperation("Finds a list of all roomTypeItemAmenitys")
    @GetMapping("/")
    public List<RoomTypeItemAmenityVo> findAll() {
        return roomTypeItemAmenityConverter.toVo(roomTypeItemAmenityService.findAll());
    }

    @ApiOperation("Finds a roomTypeItemAmenity with associated lists by id")
    @GetMapping("/detail/id/{id}")
    public RoomTypeItemAmenityVo findByIdWithAssociatedList(@PathVariable Long id) {
        return roomTypeItemAmenityConverter.toVo(roomTypeItemAmenityService.findByIdWithAssociatedList(id));
    }

    @ApiOperation("Search roomTypeItemAmenity by a specific criteria")
    @PostMapping("/search")
    public List<RoomTypeItemAmenityVo> findByCriteria(@RequestBody RoomTypeItemAmenityVo roomTypeItemAmenityVo) {
        return roomTypeItemAmenityConverter.toVo(roomTypeItemAmenityService.findByCriteria(roomTypeItemAmenityVo));
    }

    @ApiOperation("Finds a roomTypeItemAmenity by id")
    @GetMapping("/id/{id}")
    public RoomTypeItemAmenityVo findById(@PathVariable Long id) {
        return roomTypeItemAmenityConverter.toVo(roomTypeItemAmenityService.findById(id));
    }

    @ApiOperation("Saves the specified  roomTypeItemAmenity")
    @PostMapping("/")
    public RoomTypeItemAmenityVo save(@RequestBody RoomTypeItemAmenityVo roomTypeItemAmenityVo) {
        RoomTypeItemAmenity roomTypeItemAmenity = roomTypeItemAmenityConverter.toItem(roomTypeItemAmenityVo);
        roomTypeItemAmenity = roomTypeItemAmenityService.save(roomTypeItemAmenity);
        return roomTypeItemAmenityConverter.toVo(roomTypeItemAmenity);
    }

    @ApiOperation("Delete the specified roomTypeItemAmenity")
    @DeleteMapping("/")
    public int delete(@RequestBody RoomTypeItemAmenityVo roomTypeItemAmenityVo) {
        RoomTypeItemAmenity roomTypeItemAmenity = roomTypeItemAmenityConverter.toItem(roomTypeItemAmenityVo);
        return roomTypeItemAmenityService.delete(roomTypeItemAmenity);
    }

    @ApiOperation("Deletes a roomTypeItemAmenity by id")
    @DeleteMapping("/id/{id}")
    public int deleteById(@PathVariable Long id) {
        return roomTypeItemAmenityService.deleteById(id);
    }
    

    @ApiOperation("find by amenity id")
    @GetMapping("/amenity/id/{id}")
    public List<RoomTypeItemAmenity> findByAmenityId(@PathVariable Long id) {
        return roomTypeItemAmenityService.findByAmenityId(id);
    }

    @ApiOperation("delete by amenity id")
    @DeleteMapping("/amenity/id/{id}")
    public int deleteByAmenityId(@PathVariable Long id) {
        return roomTypeItemAmenityService.deleteByAmenityId(id);
    }

    @ApiOperation("find by roomType id")
    @GetMapping("/roomType/id/{id}")
    public List<RoomTypeItemAmenity> findByRoomTypeId(@PathVariable Long id) {
        return roomTypeItemAmenityService.findByRoomTypeId(id);
    }

    @ApiOperation("delete by roomType id")
    @DeleteMapping("/roomType/id/{id}")
    public int deleteByRoomTypeId(@PathVariable Long id) {
        return roomTypeItemAmenityService.deleteByRoomTypeId(id);
    }
    

    @PutMapping("/archiver/")
    public RoomTypeItemAmenityVo archiver(@RequestBody RoomTypeItemAmenityVo roomTypeItemAmenityVo) {
        RoomTypeItemAmenity roomTypeItemAmenity = roomTypeItemAmenityService.archiver(roomTypeItemAmenityConverter.toItem(roomTypeItemAmenityVo));
        return roomTypeItemAmenityConverter.toVo(roomTypeItemAmenity);
    }

    @PutMapping("/desarchiver/")
    public RoomTypeItemAmenityVo desarchiver(@RequestBody RoomTypeItemAmenityVo roomTypeItemAmenityVo) {
        RoomTypeItemAmenity roomTypeItemAmenity = roomTypeItemAmenityService.desarchiver(roomTypeItemAmenityConverter.toItem(roomTypeItemAmenityVo));
        return roomTypeItemAmenityConverter.toVo(roomTypeItemAmenity);
    }
    
}
