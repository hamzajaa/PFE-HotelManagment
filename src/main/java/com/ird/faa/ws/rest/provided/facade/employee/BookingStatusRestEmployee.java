package  com.ird.faa.ws.rest.provided.facade.employee;

import com.ird.faa.service.employee.facade.BookingStatusEmployeeService;

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
import com.ird.faa.bean.BookingStatus;
import com.ird.faa.ws.rest.provided.converter.BookingStatusConverter;
import com.ird.faa.ws.rest.provided.vo.BookingStatusVo;

@Api("Manages bookingStatus services")
@RestController
@RequestMapping("api/employee/bookingStatus")
public class BookingStatusRestEmployee {

@Autowired
private BookingStatusEmployeeService bookingStatusService;

@Autowired
private BookingStatusConverter bookingStatusConverter;


            @ApiOperation("Updates the specified  bookingStatus")
            @PutMapping("/")
            public  BookingStatusVo update(@RequestBody  BookingStatusVo  bookingStatusVo){
            BookingStatus bookingStatus = bookingStatusConverter.toItem(bookingStatusVo);
            bookingStatus = bookingStatusService.update(bookingStatus);
            return bookingStatusConverter.toVo(bookingStatus);
            }

    @ApiOperation("Finds a list of all bookingStatuss")
    @GetMapping("/")
    public List<BookingStatusVo> findAll(){
        return bookingStatusConverter.toVo(bookingStatusService.findAll());
    }

    @ApiOperation("Finds a bookingStatus with associated lists by id")
    @GetMapping("/detail/id/{id}")
    public BookingStatusVo findByIdWithAssociatedList(@PathVariable Long id){
    return bookingStatusConverter.toVo(bookingStatusService.findByIdWithAssociatedList(id));
    }

    @ApiOperation("Search bookingStatus by a specific criteria")
    @PostMapping("/search")
    public List<BookingStatusVo> findByCriteria(@RequestBody BookingStatusVo bookingStatusVo){
        return bookingStatusConverter.toVo(bookingStatusService.findByCriteria(bookingStatusVo));
        }

            @ApiOperation("Finds a bookingStatus by id")
            @GetMapping("/id/{id}")
            public BookingStatusVo findById(@PathVariable Long id){
            return bookingStatusConverter.toVo(bookingStatusService.findById(id));
            }

            @ApiOperation("Saves the specified  bookingStatus")
            @PostMapping("/")
            public BookingStatusVo save(@RequestBody BookingStatusVo bookingStatusVo){
            BookingStatus bookingStatus = bookingStatusConverter.toItem(bookingStatusVo);
            bookingStatus = bookingStatusService.save(bookingStatus);
            return bookingStatusConverter.toVo(bookingStatus);
            }

            @ApiOperation("Delete the specified bookingStatus")
            @DeleteMapping("/")
            public int delete(@RequestBody BookingStatusVo bookingStatusVo){
            BookingStatus bookingStatus = bookingStatusConverter.toItem(bookingStatusVo);
            return bookingStatusService.delete(bookingStatus);
            }

            @ApiOperation("Deletes a bookingStatus by id")
            @DeleteMapping("/id/{id}")
            public int deleteById(@PathVariable Long id){
            return bookingStatusService.deleteById(id);
            }




            @PutMapping("/archiver/")
            public BookingStatusVo archiver(@RequestBody BookingStatusVo bookingStatusVo){
                BookingStatus bookingStatus = bookingStatusService.archiver(bookingStatusConverter.toItem(bookingStatusVo));
                return bookingStatusConverter.toVo(bookingStatus);
                }

            @PutMapping("/desarchiver/")
            public BookingStatusVo desarchiver(@RequestBody BookingStatusVo bookingStatusVo){
                BookingStatus bookingStatus = bookingStatusService.desarchiver(bookingStatusConverter.toItem(bookingStatusVo));
                return bookingStatusConverter.toVo(bookingStatus);}
            }
