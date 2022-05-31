package  com.ird.faa.ws.rest.provided.facade.employee;

import com.ird.faa.service.employee.facade.PriceTypeEmployeeService;

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
import com.ird.faa.bean.PriceType;
import com.ird.faa.ws.rest.provided.converter.PriceTypeConverter;
import com.ird.faa.ws.rest.provided.vo.PriceTypeVo;

@Api("Manages priceType services")
@RestController
@RequestMapping("api/employee/priceType")
public class PriceTypeRestEmployee {

@Autowired
private PriceTypeEmployeeService priceTypeService;

@Autowired
private PriceTypeConverter priceTypeConverter;


            @ApiOperation("Updates the specified  priceType")
            @PutMapping("/")
            public  PriceTypeVo update(@RequestBody  PriceTypeVo  priceTypeVo){
            PriceType priceType = priceTypeConverter.toItem(priceTypeVo);
            priceType = priceTypeService.update(priceType);
            return priceTypeConverter.toVo(priceType);
            }

    @ApiOperation("Finds a list of all priceTypes")
    @GetMapping("/")
    public List<PriceTypeVo> findAll(){
        return priceTypeConverter.toVo(priceTypeService.findAll());
    }

    @ApiOperation("Finds a priceType with associated lists by id")
    @GetMapping("/detail/id/{id}")
    public PriceTypeVo findByIdWithAssociatedList(@PathVariable Long id){
    return priceTypeConverter.toVo(priceTypeService.findByIdWithAssociatedList(id));
    }

    @ApiOperation("Search priceType by a specific criteria")
    @PostMapping("/search")
    public List<PriceTypeVo> findByCriteria(@RequestBody PriceTypeVo priceTypeVo){
        return priceTypeConverter.toVo(priceTypeService.findByCriteria(priceTypeVo));
        }

            @ApiOperation("Finds a priceType by id")
            @GetMapping("/id/{id}")
            public PriceTypeVo findById(@PathVariable Long id){
            return priceTypeConverter.toVo(priceTypeService.findById(id));
            }

            @ApiOperation("Saves the specified  priceType")
            @PostMapping("/")
            public PriceTypeVo save(@RequestBody PriceTypeVo priceTypeVo){
            PriceType priceType = priceTypeConverter.toItem(priceTypeVo);
            priceType = priceTypeService.save(priceType);
            return priceTypeConverter.toVo(priceType);
            }

            @ApiOperation("Delete the specified priceType")
            @DeleteMapping("/")
            public int delete(@RequestBody PriceTypeVo priceTypeVo){
            PriceType priceType = priceTypeConverter.toItem(priceTypeVo);
            return priceTypeService.delete(priceType);
            }

            @ApiOperation("Deletes a priceType by id")
            @DeleteMapping("/id/{id}")
            public int deleteById(@PathVariable Long id){
            return priceTypeService.deleteById(id);
            }




            @PutMapping("/archiver/")
            public PriceTypeVo archiver(@RequestBody PriceTypeVo priceTypeVo){
                PriceType priceType = priceTypeService.archiver(priceTypeConverter.toItem(priceTypeVo));
                return priceTypeConverter.toVo(priceType);
                }

            @PutMapping("/desarchiver/")
            public PriceTypeVo desarchiver(@RequestBody PriceTypeVo priceTypeVo){
                PriceType priceType = priceTypeService.desarchiver(priceTypeConverter.toItem(priceTypeVo));
                return priceTypeConverter.toVo(priceType);}
            }
