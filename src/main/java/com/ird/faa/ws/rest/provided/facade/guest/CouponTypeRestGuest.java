package  com.ird.faa.ws.rest.provided.facade.guest;

import com.ird.faa.service.guest.facade.CouponTypeGuestService;

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
import com.ird.faa.bean.CouponType;
import com.ird.faa.ws.rest.provided.converter.CouponTypeConverter;
import com.ird.faa.ws.rest.provided.vo.CouponTypeVo;

@Api("Manages couponType services")
@RestController
@RequestMapping("api/guest/couponType")
public class CouponTypeRestGuest {

@Autowired
private CouponTypeGuestService couponTypeService;

@Autowired
private CouponTypeConverter couponTypeConverter;


            @ApiOperation("Updates the specified  couponType")
            @PutMapping("/")
            public  CouponTypeVo update(@RequestBody  CouponTypeVo  couponTypeVo){
            CouponType couponType = couponTypeConverter.toItem(couponTypeVo);
            couponType = couponTypeService.update(couponType);
            return couponTypeConverter.toVo(couponType);
            }

    @ApiOperation("Finds a list of all couponTypes")
    @GetMapping("/")
    public List<CouponTypeVo> findAll(){
        return couponTypeConverter.toVo(couponTypeService.findAll());
    }

    @ApiOperation("Finds a couponType with associated lists by id")
    @GetMapping("/detail/id/{id}")
    public CouponTypeVo findByIdWithAssociatedList(@PathVariable Long id){
    return couponTypeConverter.toVo(couponTypeService.findByIdWithAssociatedList(id));
    }

    @ApiOperation("Search couponType by a specific criteria")
    @PostMapping("/search")
    public List<CouponTypeVo> findByCriteria(@RequestBody CouponTypeVo couponTypeVo){
        return couponTypeConverter.toVo(couponTypeService.findByCriteria(couponTypeVo));
        }

            @ApiOperation("Finds a couponType by id")
            @GetMapping("/id/{id}")
            public CouponTypeVo findById(@PathVariable Long id){
            return couponTypeConverter.toVo(couponTypeService.findById(id));
            }

            @ApiOperation("Saves the specified  couponType")
            @PostMapping("/")
            public CouponTypeVo save(@RequestBody CouponTypeVo couponTypeVo){
            CouponType couponType = couponTypeConverter.toItem(couponTypeVo);
            couponType = couponTypeService.save(couponType);
            return couponTypeConverter.toVo(couponType);
            }

            @ApiOperation("Delete the specified couponType")
            @DeleteMapping("/")
            public int delete(@RequestBody CouponTypeVo couponTypeVo){
            CouponType couponType = couponTypeConverter.toItem(couponTypeVo);
            return couponTypeService.delete(couponType);
            }

            @ApiOperation("Deletes a couponType by id")
            @DeleteMapping("/id/{id}")
            public int deleteById(@PathVariable Long id){
            return couponTypeService.deleteById(id);
            }




            @PutMapping("/archiver/")
            public CouponTypeVo archiver(@RequestBody CouponTypeVo couponTypeVo){
                CouponType couponType = couponTypeService.archiver(couponTypeConverter.toItem(couponTypeVo));
                return couponTypeConverter.toVo(couponType);
                }

            @PutMapping("/desarchiver/")
            public CouponTypeVo desarchiver(@RequestBody CouponTypeVo couponTypeVo){
                CouponType couponType = couponTypeService.desarchiver(couponTypeConverter.toItem(couponTypeVo));
                return couponTypeConverter.toVo(couponType);}
            }
