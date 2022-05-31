package  com.ird.faa.ws.rest.provided.facade.guest;

import com.ird.faa.service.guest.facade.PriceManagerGuestService;

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
import com.ird.faa.bean.PriceManager;
import com.ird.faa.ws.rest.provided.converter.PriceManagerConverter;
import com.ird.faa.ws.rest.provided.vo.PriceManagerVo;

@Api("Manages priceManager services")
@RestController
@RequestMapping("api/guest/priceManager")
public class PriceManagerRestGuest {

@Autowired
private PriceManagerGuestService priceManagerService;

@Autowired
private PriceManagerConverter priceManagerConverter;


            @ApiOperation("Updates the specified  priceManager")
            @PutMapping("/")
            public  PriceManagerVo update(@RequestBody  PriceManagerVo  priceManagerVo){
            PriceManager priceManager = priceManagerConverter.toItem(priceManagerVo);
            priceManager = priceManagerService.update(priceManager);
            return priceManagerConverter.toVo(priceManager);
            }

    @ApiOperation("Finds a list of all priceManagers")
    @GetMapping("/")
    public List<PriceManagerVo> findAll(){
        return priceManagerConverter.toVo(priceManagerService.findAll());
    }

    @ApiOperation("Finds a priceManager with associated lists by id")
    @GetMapping("/detail/id/{id}")
    public PriceManagerVo findByIdWithAssociatedList(@PathVariable Long id){
    return priceManagerConverter.toVo(priceManagerService.findByIdWithAssociatedList(id));
    }

    @ApiOperation("Search priceManager by a specific criteria")
    @PostMapping("/search")
    public List<PriceManagerVo> findByCriteria(@RequestBody PriceManagerVo priceManagerVo){
        return priceManagerConverter.toVo(priceManagerService.findByCriteria(priceManagerVo));
        }

            @ApiOperation("Finds a priceManager by id")
            @GetMapping("/id/{id}")
            public PriceManagerVo findById(@PathVariable Long id){
            return priceManagerConverter.toVo(priceManagerService.findById(id));
            }

            @ApiOperation("Saves the specified  priceManager")
            @PostMapping("/")
            public PriceManagerVo save(@RequestBody PriceManagerVo priceManagerVo){
            PriceManager priceManager = priceManagerConverter.toItem(priceManagerVo);
            priceManager = priceManagerService.save(priceManager);
            return priceManagerConverter.toVo(priceManager);
            }

            @ApiOperation("Delete the specified priceManager")
            @DeleteMapping("/")
            public int delete(@RequestBody PriceManagerVo priceManagerVo){
            PriceManager priceManager = priceManagerConverter.toItem(priceManagerVo);
            return priceManagerService.delete(priceManager);
            }

            @ApiOperation("Deletes a priceManager by id")
            @DeleteMapping("/id/{id}")
            public int deleteById(@PathVariable Long id){
            return priceManagerService.deleteById(id);
            }
        @ApiOperation("find by roomType shortCode")
        @GetMapping("/roomType/shortCode/{shortCode}")
        public List<PriceManager> findByRoomTypeShortCode(@PathVariable String shortCode){
        return priceManagerService.findByRoomTypeShortCode(shortCode);
        }

        @ApiOperation("delete by roomType shortCode")
        @DeleteMapping("/roomType/shortCode/{shortCode}")
        public int deleteByRoomTypeShortCode(@PathVariable String shortCode){
        return priceManagerService.deleteByRoomTypeShortCode(shortCode);
        }

        @ApiOperation("find by roomType id")
        @GetMapping("/roomType/id/{id}")
        public List<PriceManager> findByRoomTypeId(@PathVariable Long id){
        return priceManagerService.findByRoomTypeId(id);
        }

        @ApiOperation("delete by roomType id")
        @DeleteMapping("/roomType/id/{id}")
        public int deleteByRoomTypeId(@PathVariable Long id){
        return priceManagerService.deleteByRoomTypeId(id);
        }





            @PutMapping("/archiver/")
            public PriceManagerVo archiver(@RequestBody PriceManagerVo priceManagerVo){
                PriceManager priceManager = priceManagerService.archiver(priceManagerConverter.toItem(priceManagerVo));
                return priceManagerConverter.toVo(priceManager);
                }

            @PutMapping("/desarchiver/")
            public PriceManagerVo desarchiver(@RequestBody PriceManagerVo priceManagerVo){
                PriceManager priceManager = priceManagerService.desarchiver(priceManagerConverter.toItem(priceManagerVo));
                return priceManagerConverter.toVo(priceManager);}
            }
