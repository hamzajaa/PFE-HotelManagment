package  com.ird.faa.ws.rest.provided.facade.employee;

import com.ird.faa.service.employee.facade.BookingRoomStateEmployeeService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.ird.faa.bean.BookingRoomState;
import com.ird.faa.ws.rest.provided.converter.BookingRoomStateConverter;
import com.ird.faa.ws.rest.provided.vo.BookingRoomStateVo;

@Api("Manages bookingRoomState services")
@RestController
@RequestMapping("api/employee/bookingRoomState")
public class BookingRoomStateRestEmployee {

@Autowired
private BookingRoomStateEmployeeService bookingRoomStateService;

@Autowired
private BookingRoomStateConverter bookingRoomStateConverter;


            @ApiOperation("Updates the specified  bookingRoomState")
            @PutMapping("/")
            public  BookingRoomStateVo update(@RequestBody  BookingRoomStateVo  bookingRoomStateVo){
            BookingRoomState bookingRoomState = bookingRoomStateConverter.toItem(bookingRoomStateVo);
            bookingRoomState = bookingRoomStateService.update(bookingRoomState);
            return bookingRoomStateConverter.toVo(bookingRoomState);
            }

    @ApiOperation("Finds a list of all bookingRoomStates")
    @GetMapping("/")
    public List<BookingRoomStateVo> findAll(){
        return bookingRoomStateConverter.toVo(bookingRoomStateService.findAll());
    }

    @ApiOperation("Finds a bookingRoomState with associated lists by id")
    @GetMapping("/detail/id/{id}")
    public BookingRoomStateVo findByIdWithAssociatedList(@PathVariable Long id){
    return bookingRoomStateConverter.toVo(bookingRoomStateService.findByIdWithAssociatedList(id));
    }

    @ApiOperation("Search bookingRoomState by a specific criteria")
    @PostMapping("/search")
    public List<BookingRoomStateVo> findByCriteria(@RequestBody BookingRoomStateVo bookingRoomStateVo){
        return bookingRoomStateConverter.toVo(bookingRoomStateService.findByCriteria(bookingRoomStateVo));
        }

            @ApiOperation("Finds a bookingRoomState by id")
            @GetMapping("/id/{id}")
            public BookingRoomStateVo findById(@PathVariable Long id){
            return bookingRoomStateConverter.toVo(bookingRoomStateService.findById(id));
            }

            @ApiOperation("Saves the specified  bookingRoomState")
            @PostMapping("/")
            public BookingRoomStateVo save(@RequestBody BookingRoomStateVo bookingRoomStateVo){
            BookingRoomState bookingRoomState = bookingRoomStateConverter.toItem(bookingRoomStateVo);
            bookingRoomState = bookingRoomStateService.save(bookingRoomState);
            return bookingRoomStateConverter.toVo(bookingRoomState);
            }

            @ApiOperation("Delete the specified bookingRoomState")
            @DeleteMapping("/")
            public int delete(@RequestBody BookingRoomStateVo bookingRoomStateVo){
            BookingRoomState bookingRoomState = bookingRoomStateConverter.toItem(bookingRoomStateVo);
            return bookingRoomStateService.delete(bookingRoomState);
            }

            @ApiOperation("Deletes a bookingRoomState by id")
            @DeleteMapping("/id/{id}")
            public int deleteById(@PathVariable Long id){
            return bookingRoomStateService.deleteById(id);
            }




            @PutMapping("/archiver/")
            public BookingRoomStateVo archiver(@RequestBody BookingRoomStateVo bookingRoomStateVo){
                BookingRoomState bookingRoomState = bookingRoomStateService.archiver(bookingRoomStateConverter.toItem(bookingRoomStateVo));
                return bookingRoomStateConverter.toVo(bookingRoomState);
                }

            @PutMapping("/desarchiver/")
            public BookingRoomStateVo desarchiver(@RequestBody BookingRoomStateVo bookingRoomStateVo){
                BookingRoomState bookingRoomState = bookingRoomStateService.desarchiver(bookingRoomStateConverter.toItem(bookingRoomStateVo));
                return bookingRoomStateConverter.toVo(bookingRoomState);}
            }
