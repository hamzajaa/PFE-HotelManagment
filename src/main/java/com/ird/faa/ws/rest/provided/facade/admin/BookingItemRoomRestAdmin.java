package com.ird.faa.ws.rest.provided.facade.admin;

import com.ird.faa.bean.BookingItemRoom;
import com.ird.faa.service.admin.facade.BookingItemRoomAdminService;
import com.ird.faa.ws.rest.provided.converter.BookingItemRoomConverter;
import com.ird.faa.ws.rest.provided.vo.BookingItemRoomVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("Manages bookingItemRoom services")
@RestController
@RequestMapping("api/admin/bookingItemRoom")
public class BookingItemRoomRestAdmin {

    @Autowired
    private BookingItemRoomAdminService bookingItemRoomService;

    @Autowired
    private BookingItemRoomConverter bookingItemRoomConverter;


    @ApiOperation("Updates the specified  bookingItemRoom")
    @PutMapping("/")
    public BookingItemRoomVo update(@RequestBody BookingItemRoomVo bookingItemRoomVo) {
        BookingItemRoom bookingItemRoom = bookingItemRoomConverter.toItem(bookingItemRoomVo);
        bookingItemRoom = bookingItemRoomService.update(bookingItemRoom);
        return bookingItemRoomConverter.toVo(bookingItemRoom);
    }

    @ApiOperation("Finds a list of all bookingItemRooms")
    @GetMapping("/")
    public List<BookingItemRoomVo> findAll() {
        return bookingItemRoomConverter.toVo(bookingItemRoomService.findAll());
    }

    @ApiOperation("Finds a bookingItemRoom with associated lists by id")
    @GetMapping("/detail/id/{id}")
    public BookingItemRoomVo findByIdWithAssociatedList(@PathVariable Long id) {
        return bookingItemRoomConverter.toVo(bookingItemRoomService.findByIdWithAssociatedList(id));
    }

    @ApiOperation("Search bookingItemRoom by a specific criteria")
    @PostMapping("/search")
    public List<BookingItemRoomVo> findByCriteria(@RequestBody BookingItemRoomVo bookingItemRoomVo) {
        return bookingItemRoomConverter.toVo(bookingItemRoomService.findByCriteria(bookingItemRoomVo));
    }

    @ApiOperation("Finds a bookingItemRoom by id")
    @GetMapping("/id/{id}")
    public BookingItemRoomVo findById(@PathVariable Long id) {
        return bookingItemRoomConverter.toVo(bookingItemRoomService.findById(id));
    }

    @ApiOperation("Saves the specified  bookingItemRoom")
    @PostMapping("/")
    public BookingItemRoomVo save(@RequestBody BookingItemRoomVo bookingItemRoomVo) {
        BookingItemRoom bookingItemRoom = bookingItemRoomConverter.toItem(bookingItemRoomVo);
        bookingItemRoom = bookingItemRoomService.save(bookingItemRoom);
        return bookingItemRoomConverter.toVo(bookingItemRoom);
    }

    @ApiOperation("Delete the specified bookingItemRoom")
    @DeleteMapping("/")
    public int delete(@RequestBody BookingItemRoomVo bookingItemRoomVo) {
        BookingItemRoom bookingItemRoom = bookingItemRoomConverter.toItem(bookingItemRoomVo);
        return bookingItemRoomService.delete(bookingItemRoom);
    }

    @ApiOperation("Deletes a bookingItemRoom by id")
    @DeleteMapping("/id/{id}")
    public int deleteById(@PathVariable Long id) {
        return bookingItemRoomService.deleteById(id);
    }

    @ApiOperation("find by room roomNumber")
    @GetMapping("/room/roomNumber/{roomNumber}")
    public List<BookingItemRoom> findByRoomRoomNumber(@PathVariable Long roomNumber) {
        return bookingItemRoomService.findByRoomRoomNumber(roomNumber);
    }

    @ApiOperation("delete by room roomNumber")
    @DeleteMapping("/room/roomNumber/{roomNumber}")
    public int deleteByRoomRoomNumber(@PathVariable Long roomNumber) {
        return bookingItemRoomService.deleteByRoomRoomNumber(roomNumber);
    }

    @ApiOperation("find by room id")
    @GetMapping("/room/id/{id}")
    public List<BookingItemRoom> findByRoomId(@PathVariable Long id) {
        return bookingItemRoomService.findByRoomId(id);
    }

    @ApiOperation("delete by room id")
    @DeleteMapping("/room/id/{id}")
    public int deleteByRoomId(@PathVariable Long id) {
        return bookingItemRoomService.deleteByRoomId(id);
    }

    @ApiOperation("find by booking id")
    @GetMapping("/booking/id/{id}")
    public List<BookingItemRoom> findByBookingId(@PathVariable Long id) {
        return bookingItemRoomService.findByBookingId(id);
    }

    @ApiOperation("delete by booking id")
    @DeleteMapping("/booking/id/{id}")
    public int deleteByBookingId(@PathVariable Long id) {
        return bookingItemRoomService.deleteByBookingId(id);
    }

    @ApiOperation("find by bookingRoomState code")
    @GetMapping("/bookingRoomState/code/{code}")
    public List<BookingItemRoom> findByBookingRoomStateCode(@PathVariable String code) {
        return bookingItemRoomService.findByBookingRoomStateCode(code);
    }

    @ApiOperation("delete by bookingRoomState code")
    @DeleteMapping("/bookingRoomState/code/{code}")
    public int deleteByBookingRoomStateCode(@PathVariable String code) {
        return bookingItemRoomService.deleteByBookingRoomStateCode(code);
    }

    @ApiOperation("find by bookingRoomState id")
    @GetMapping("/bookingRoomState/id/{id}")
    public List<BookingItemRoom> findByBookingRoomStateId(@PathVariable Long id) {
        return bookingItemRoomService.findByBookingRoomStateId(id);
    }

    @ApiOperation("delete by bookingRoomState id")
    @DeleteMapping("/bookingRoomState/id/{id}")
    public int deleteByBookingRoomStateId(@PathVariable Long id) {
        return bookingItemRoomService.deleteByBookingRoomStateId(id);
    }


    @PutMapping("/archiver/")
    public BookingItemRoomVo archiver(@RequestBody BookingItemRoomVo bookingItemRoomVo) {
        BookingItemRoom bookingItemRoom = bookingItemRoomService.archiver(bookingItemRoomConverter.toItem(bookingItemRoomVo));
        return bookingItemRoomConverter.toVo(bookingItemRoom);
    }

    @PutMapping("/desarchiver/")
    public BookingItemRoomVo desarchiver(@RequestBody BookingItemRoomVo bookingItemRoomVo) {
        BookingItemRoom bookingItemRoom = bookingItemRoomService.desarchiver(bookingItemRoomConverter.toItem(bookingItemRoomVo));
        return bookingItemRoomConverter.toVo(bookingItemRoom);
    }
}
