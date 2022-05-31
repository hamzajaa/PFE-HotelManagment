package  com.ird.faa.ws.rest.provided.facade.guest;

import com.ird.faa.service.guest.facade.CouponManagmentGuestService;

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
import com.ird.faa.bean.CouponManagment;
import com.ird.faa.ws.rest.provided.converter.CouponManagmentConverter;
import com.ird.faa.ws.rest.provided.vo.CouponManagmentVo;

@Api("Manages couponManagment services")
@RestController
@RequestMapping("api/guest/couponManagment")
public class CouponManagmentRestGuest {

@Autowired
private CouponManagmentGuestService couponManagmentService;

@Autowired
private CouponManagmentConverter couponManagmentConverter;


            @ApiOperation("Updates the specified  couponManagment")
            @PutMapping("/")
            public  CouponManagmentVo update(@RequestBody  CouponManagmentVo  couponManagmentVo){
            CouponManagment couponManagment = couponManagmentConverter.toItem(couponManagmentVo);
            couponManagment = couponManagmentService.update(couponManagment);
            return couponManagmentConverter.toVo(couponManagment);
            }

    @ApiOperation("Finds a list of all couponManagments")
    @GetMapping("/")
    public List<CouponManagmentVo> findAll(){
        return couponManagmentConverter.toVo(couponManagmentService.findAll());
    }

    @ApiOperation("Finds a couponManagment with associated lists by id")
    @GetMapping("/detail/id/{id}")
    public CouponManagmentVo findByIdWithAssociatedList(@PathVariable Long id){
    return couponManagmentConverter.toVo(couponManagmentService.findByIdWithAssociatedList(id));
    }

    @ApiOperation("Search couponManagment by a specific criteria")
    @PostMapping("/search")
    public List<CouponManagmentVo> findByCriteria(@RequestBody CouponManagmentVo couponManagmentVo){
        return couponManagmentConverter.toVo(couponManagmentService.findByCriteria(couponManagmentVo));
        }

            @ApiOperation("Finds a couponManagment by id")
            @GetMapping("/id/{id}")
            public CouponManagmentVo findById(@PathVariable Long id){
            return couponManagmentConverter.toVo(couponManagmentService.findById(id));
            }

            @ApiOperation("Saves the specified  couponManagment")
            @PostMapping("/")
            public CouponManagmentVo save(@RequestBody CouponManagmentVo couponManagmentVo){
            CouponManagment couponManagment = couponManagmentConverter.toItem(couponManagmentVo);
            couponManagment = couponManagmentService.save(couponManagment);
            return couponManagmentConverter.toVo(couponManagment);
            }

            @ApiOperation("Delete the specified couponManagment")
            @DeleteMapping("/")
            public int delete(@RequestBody CouponManagmentVo couponManagmentVo){
            CouponManagment couponManagment = couponManagmentConverter.toItem(couponManagmentVo);
            return couponManagmentService.delete(couponManagment);
            }

            @ApiOperation("Deletes a couponManagment by id")
            @DeleteMapping("/id/{id}")
            public int deleteById(@PathVariable Long id){
            return couponManagmentService.deleteById(id);
            }
        @ApiOperation("find by couponType code")
        @GetMapping("/couponType/code/{code}")
        public List<CouponManagment> findByCouponTypeCode(@PathVariable String code){
        return couponManagmentService.findByCouponTypeCode(code);
        }

        @ApiOperation("delete by couponType code")
        @DeleteMapping("/couponType/code/{code}")
        public int deleteByCouponTypeCode(@PathVariable String code){
        return couponManagmentService.deleteByCouponTypeCode(code);
        }

        @ApiOperation("find by couponType id")
        @GetMapping("/couponType/id/{id}")
        public List<CouponManagment> findByCouponTypeId(@PathVariable Long id){
        return couponManagmentService.findByCouponTypeId(id);
        }

        @ApiOperation("delete by couponType id")
        @DeleteMapping("/couponType/id/{id}")
        public int deleteByCouponTypeId(@PathVariable Long id){
        return couponManagmentService.deleteByCouponTypeId(id);
        }





            }
