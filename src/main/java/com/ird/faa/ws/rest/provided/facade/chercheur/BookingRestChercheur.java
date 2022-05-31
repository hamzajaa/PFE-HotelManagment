package  com.ird.faa.ws.rest.provided.facade.chercheur;

import com.ird.faa.service.chercheur.facade.BookingChercheurService;

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
import com.ird.faa.bean.Booking;
import com.ird.faa.ws.rest.provided.converter.BookingConverter;
import com.ird.faa.ws.rest.provided.vo.BookingVo;

@Api("Manages booking services")
@RestController
@RequestMapping("api/chercheur/booking")
public class BookingRestChercheur {

@Autowired
private BookingChercheurService bookingService;

@Autowired
private BookingConverter bookingConverter;


            @ApiOperation("Updates the specified  booking")
            @PutMapping("/")
            public  BookingVo update(@RequestBody  BookingVo  bookingVo){
            Booking booking = bookingConverter.toItem(bookingVo);
            booking = bookingService.update(booking);
            return bookingConverter.toVo(booking);
            }

    @ApiOperation("Finds a list of all bookings")
    @GetMapping("/")
    public List<BookingVo> findAll(){
        return bookingConverter.toVo(bookingService.findAll());
    }

    @ApiOperation("Finds a booking with associated lists by id")
    @GetMapping("/detail/id/{id}")
    public BookingVo findByIdWithAssociatedList(@PathVariable Long id){
    return bookingConverter.toVo(bookingService.findByIdWithAssociatedList(id));
    }

    @ApiOperation("Search booking by a specific criteria")
    @PostMapping("/search")
    public List<BookingVo> findByCriteria(@RequestBody BookingVo bookingVo){
        return bookingConverter.toVo(bookingService.findByCriteria(bookingVo));
        }

            @ApiOperation("Finds a booking by id")
            @GetMapping("/id/{id}")
            public BookingVo findById(@PathVariable Long id){
            return bookingConverter.toVo(bookingService.findById(id));
            }

            @ApiOperation("Saves the specified  booking")
            @PostMapping("/")
            public BookingVo save(@RequestBody BookingVo bookingVo){
            Booking booking = bookingConverter.toItem(bookingVo);
            booking = bookingService.save(booking);
            return bookingConverter.toVo(booking);
            }

            @ApiOperation("Delete the specified booking")
            @DeleteMapping("/")
            public int delete(@RequestBody BookingVo bookingVo){
            Booking booking = bookingConverter.toItem(bookingVo);
            return bookingService.delete(booking);
            }

            @ApiOperation("Deletes a booking by id")
            @DeleteMapping("/id/{id}")
            public int deleteById(@PathVariable Long id){
            return bookingService.deleteById(id);
            }
        @ApiOperation("find by guest id")
        @GetMapping("/guest/id/{id}")
        public List<Booking> findByGuestId(@PathVariable Long id){
        return bookingService.findByGuestId(id);
        }

        @ApiOperation("delete by guest id")
        @DeleteMapping("/guest/id/{id}")
        public int deleteByGuestId(@PathVariable Long id){
        return bookingService.deleteByGuestId(id);
        }

        @ApiOperation("find by paymentStatus code")
        @GetMapping("/paymentStatus/code/{code}")
        public List<Booking> findByPaymentStatusCode(@PathVariable String code){
        return bookingService.findByPaymentStatusCode(code);
        }

        @ApiOperation("delete by paymentStatus code")
        @DeleteMapping("/paymentStatus/code/{code}")
        public int deleteByPaymentStatusCode(@PathVariable String code){
        return bookingService.deleteByPaymentStatusCode(code);
        }

        @ApiOperation("find by paymentStatus id")
        @GetMapping("/paymentStatus/id/{id}")
        public List<Booking> findByPaymentStatusId(@PathVariable Long id){
        return bookingService.findByPaymentStatusId(id);
        }

        @ApiOperation("delete by paymentStatus id")
        @DeleteMapping("/paymentStatus/id/{id}")
        public int deleteByPaymentStatusId(@PathVariable Long id){
        return bookingService.deleteByPaymentStatusId(id);
        }

        @ApiOperation("find by bookingStatus code")
        @GetMapping("/bookingStatus/code/{code}")
        public List<Booking> findByBookingStatusCode(@PathVariable String code){
        return bookingService.findByBookingStatusCode(code);
        }

        @ApiOperation("delete by bookingStatus code")
        @DeleteMapping("/bookingStatus/code/{code}")
        public int deleteByBookingStatusCode(@PathVariable String code){
        return bookingService.deleteByBookingStatusCode(code);
        }

        @ApiOperation("find by bookingStatus id")
        @GetMapping("/bookingStatus/id/{id}")
        public List<Booking> findByBookingStatusId(@PathVariable Long id){
        return bookingService.findByBookingStatusId(id);
        }

        @ApiOperation("delete by bookingStatus id")
        @DeleteMapping("/bookingStatus/id/{id}")
        public int deleteByBookingStatusId(@PathVariable Long id){
        return bookingService.deleteByBookingStatusId(id);
        }

        @ApiOperation("find by priceManager id")
        @GetMapping("/priceManager/id/{id}")
        public List<Booking> findByPriceManagerId(@PathVariable Long id){
        return bookingService.findByPriceManagerId(id);
        }

        @ApiOperation("delete by priceManager id")
        @DeleteMapping("/priceManager/id/{id}")
        public int deleteByPriceManagerId(@PathVariable Long id){
        return bookingService.deleteByPriceManagerId(id);
        }





            }
