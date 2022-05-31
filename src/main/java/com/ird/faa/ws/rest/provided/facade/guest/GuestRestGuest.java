package  com.ird.faa.ws.rest.provided.facade.guest;

import com.ird.faa.service.guest.facade.GuestGuestService;

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
import com.ird.faa.bean.Guest;
import com.ird.faa.ws.rest.provided.converter.GuestConverter;
import com.ird.faa.ws.rest.provided.vo.GuestVo;

@Api("Manages guest services")
@RestController
@RequestMapping("api/guest/guest")
public class GuestRestGuest {

@Autowired
private GuestGuestService guestService;

@Autowired
private GuestConverter guestConverter;


            @ApiOperation("Updates the specified  guest")
            @PutMapping("/")
            public  GuestVo update(@RequestBody  GuestVo  guestVo){
            Guest guest = guestConverter.toItem(guestVo);
            guest = guestService.update(guest);
            return guestConverter.toVo(guest);
            }

    @ApiOperation("Finds a list of all guests")
    @GetMapping("/")
    public List<GuestVo> findAll(){
        return guestConverter.toVo(guestService.findAll());
    }

    @ApiOperation("Finds a guest with associated lists by id")
    @GetMapping("/detail/id/{id}")
    public GuestVo findByIdWithAssociatedList(@PathVariable Long id){
    return guestConverter.toVo(guestService.findByIdWithAssociatedList(id));
    }

    @ApiOperation("Search guest by a specific criteria")
    @PostMapping("/search")
    public List<GuestVo> findByCriteria(@RequestBody GuestVo guestVo){
        return guestConverter.toVo(guestService.findByCriteria(guestVo));
        }

            @ApiOperation("Finds a guest by id")
            @GetMapping("/id/{id}")
            public GuestVo findById(@PathVariable Long id){
            return guestConverter.toVo(guestService.findById(id));
            }

            @ApiOperation("Saves the specified  guest")
            @PostMapping("/")
            public GuestVo save(@RequestBody GuestVo guestVo){
            Guest guest = guestConverter.toItem(guestVo);
            guest = guestService.save(guest);
            return guestConverter.toVo(guest);
            }

            @ApiOperation("Delete the specified guest")
            @DeleteMapping("/")
            public int delete(@RequestBody GuestVo guestVo){
            Guest guest = guestConverter.toItem(guestVo);
            return guestService.delete(guest);
            }

            @ApiOperation("Deletes a guest by id")
            @DeleteMapping("/id/{id}")
            public int deleteById(@PathVariable Long id){
            return guestService.deleteById(id);
            }
        @ApiOperation("find by country code")
        @GetMapping("/country/code/{code}")
        public List<Guest> findByCountryCode(@PathVariable String code){
        return guestService.findByCountryCode(code);
        }

        @ApiOperation("delete by country code")
        @DeleteMapping("/country/code/{code}")
        public int deleteByCountryCode(@PathVariable String code){
        return guestService.deleteByCountryCode(code);
        }

        @ApiOperation("find by country id")
        @GetMapping("/country/id/{id}")
        public List<Guest> findByCountryId(@PathVariable Long id){
        return guestService.findByCountryId(id);
        }

        @ApiOperation("delete by country id")
        @DeleteMapping("/country/id/{id}")
        public int deleteByCountryId(@PathVariable Long id){
        return guestService.deleteByCountryId(id);
        }





            @PutMapping("/archiver/")
            public GuestVo archiver(@RequestBody GuestVo guestVo){
                Guest guest = guestService.archiver(guestConverter.toItem(guestVo));
                return guestConverter.toVo(guest);
                }

            @PutMapping("/desarchiver/")
            public GuestVo desarchiver(@RequestBody GuestVo guestVo){
                Guest guest = guestService.desarchiver(guestConverter.toItem(guestVo));
                return guestConverter.toVo(guest);}
            }
