package  com.ird.faa.ws.rest.provided.facade.chercheur;

import com.ird.faa.service.chercheur.facade.TypePaymentChercheurService;

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
import com.ird.faa.bean.TypePayment;
import com.ird.faa.ws.rest.provided.converter.TypePaymentConverter;
import com.ird.faa.ws.rest.provided.vo.TypePaymentVo;

@Api("Manages typePayment services")
@RestController
@RequestMapping("api/chercheur/typePayment")
public class TypePaymentRestChercheur {

@Autowired
private TypePaymentChercheurService typePaymentService;

@Autowired
private TypePaymentConverter typePaymentConverter;


            @ApiOperation("Updates the specified  typePayment")
            @PutMapping("/")
            public  TypePaymentVo update(@RequestBody  TypePaymentVo  typePaymentVo){
            TypePayment typePayment = typePaymentConverter.toItem(typePaymentVo);
            typePayment = typePaymentService.update(typePayment);
            return typePaymentConverter.toVo(typePayment);
            }

    @ApiOperation("Finds a list of all typePayments")
    @GetMapping("/")
    public List<TypePaymentVo> findAll(){
        return typePaymentConverter.toVo(typePaymentService.findAll());
    }

    @ApiOperation("Finds a typePayment with associated lists by id")
    @GetMapping("/detail/id/{id}")
    public TypePaymentVo findByIdWithAssociatedList(@PathVariable Long id){
    return typePaymentConverter.toVo(typePaymentService.findByIdWithAssociatedList(id));
    }

    @ApiOperation("Search typePayment by a specific criteria")
    @PostMapping("/search")
    public List<TypePaymentVo> findByCriteria(@RequestBody TypePaymentVo typePaymentVo){
        return typePaymentConverter.toVo(typePaymentService.findByCriteria(typePaymentVo));
        }

            @ApiOperation("Finds a typePayment by id")
            @GetMapping("/id/{id}")
            public TypePaymentVo findById(@PathVariable Long id){
            return typePaymentConverter.toVo(typePaymentService.findById(id));
            }

            @ApiOperation("Saves the specified  typePayment")
            @PostMapping("/")
            public TypePaymentVo save(@RequestBody TypePaymentVo typePaymentVo){
            TypePayment typePayment = typePaymentConverter.toItem(typePaymentVo);
            typePayment = typePaymentService.save(typePayment);
            return typePaymentConverter.toVo(typePayment);
            }

            @ApiOperation("Delete the specified typePayment")
            @DeleteMapping("/")
            public int delete(@RequestBody TypePaymentVo typePaymentVo){
            TypePayment typePayment = typePaymentConverter.toItem(typePaymentVo);
            return typePaymentService.delete(typePayment);
            }

            @ApiOperation("Deletes a typePayment by id")
            @DeleteMapping("/id/{id}")
            public int deleteById(@PathVariable Long id){
            return typePaymentService.deleteById(id);
            }




            }
