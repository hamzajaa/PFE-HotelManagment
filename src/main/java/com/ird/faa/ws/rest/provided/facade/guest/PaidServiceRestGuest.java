package  com.ird.faa.ws.rest.provided.facade.guest;

import com.ird.faa.service.guest.facade.PaidServiceGuestService;

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
import com.ird.faa.bean.PaidService;
import com.ird.faa.ws.rest.provided.converter.PaidServiceConverter;
import com.ird.faa.ws.rest.provided.vo.PaidServiceVo;

@Api("Manages paidService services")
@RestController
@RequestMapping("api/guest/paidService")
public class PaidServiceRestGuest {

@Autowired
private PaidServiceGuestService paidServiceService;

@Autowired
private PaidServiceConverter paidServiceConverter;


            @ApiOperation("Updates the specified  paidService")
            @PutMapping("/")
            public  PaidServiceVo update(@RequestBody  PaidServiceVo  paidServiceVo){
            PaidService paidService = paidServiceConverter.toItem(paidServiceVo);
            paidService = paidServiceService.update(paidService);
            return paidServiceConverter.toVo(paidService);
            }

    @ApiOperation("Finds a list of all paidServices")
    @GetMapping("/")
    public List<PaidServiceVo> findAll(){
        return paidServiceConverter.toVo(paidServiceService.findAll());
    }

    @ApiOperation("Finds a paidService with associated lists by id")
    @GetMapping("/detail/id/{id}")
    public PaidServiceVo findByIdWithAssociatedList(@PathVariable Long id){
    return paidServiceConverter.toVo(paidServiceService.findByIdWithAssociatedList(id));
    }

    @ApiOperation("Search paidService by a specific criteria")
    @PostMapping("/search")
    public List<PaidServiceVo> findByCriteria(@RequestBody PaidServiceVo paidServiceVo){
        return paidServiceConverter.toVo(paidServiceService.findByCriteria(paidServiceVo));
        }

            @ApiOperation("Finds a paidService by id")
            @GetMapping("/id/{id}")
            public PaidServiceVo findById(@PathVariable Long id){
            return paidServiceConverter.toVo(paidServiceService.findById(id));
            }

            @ApiOperation("Saves the specified  paidService")
            @PostMapping("/")
            public PaidServiceVo save(@RequestBody PaidServiceVo paidServiceVo){
            PaidService paidService = paidServiceConverter.toItem(paidServiceVo);
            paidService = paidServiceService.save(paidService);
            return paidServiceConverter.toVo(paidService);
            }

            @ApiOperation("Delete the specified paidService")
            @DeleteMapping("/")
            public int delete(@RequestBody PaidServiceVo paidServiceVo){
            PaidService paidService = paidServiceConverter.toItem(paidServiceVo);
            return paidServiceService.delete(paidService);
            }

            @ApiOperation("Deletes a paidService by id")
            @DeleteMapping("/id/{id}")
            public int deleteById(@PathVariable Long id){
            return paidServiceService.deleteById(id);
            }
        @ApiOperation("find by priceType code")
        @GetMapping("/priceType/code/{code}")
        public List<PaidService> findByPriceTypeCode(@PathVariable String code){
        return paidServiceService.findByPriceTypeCode(code);
        }

        @ApiOperation("delete by priceType code")
        @DeleteMapping("/priceType/code/{code}")
        public int deleteByPriceTypeCode(@PathVariable String code){
        return paidServiceService.deleteByPriceTypeCode(code);
        }

        @ApiOperation("find by priceType id")
        @GetMapping("/priceType/id/{id}")
        public List<PaidService> findByPriceTypeId(@PathVariable Long id){
        return paidServiceService.findByPriceTypeId(id);
        }

        @ApiOperation("delete by priceType id")
        @DeleteMapping("/priceType/id/{id}")
        public int deleteByPriceTypeId(@PathVariable Long id){
        return paidServiceService.deleteByPriceTypeId(id);
        }





            @PutMapping("/archiver/")
            public PaidServiceVo archiver(@RequestBody PaidServiceVo paidServiceVo){
                PaidService paidService = paidServiceService.archiver(paidServiceConverter.toItem(paidServiceVo));
                return paidServiceConverter.toVo(paidService);
                }

            @PutMapping("/desarchiver/")
            public PaidServiceVo desarchiver(@RequestBody PaidServiceVo paidServiceVo){
                PaidService paidService = paidServiceService.desarchiver(paidServiceConverter.toItem(paidServiceVo));
                return paidServiceConverter.toVo(paidService);}
            }
