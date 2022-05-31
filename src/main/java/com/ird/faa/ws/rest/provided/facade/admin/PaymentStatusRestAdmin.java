package  com.ird.faa.ws.rest.provided.facade.admin;

import com.ird.faa.service.admin.facade.PaymentStatusAdminService;

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
import com.ird.faa.bean.PaymentStatus;
import com.ird.faa.ws.rest.provided.converter.PaymentStatusConverter;
import com.ird.faa.ws.rest.provided.vo.PaymentStatusVo;

@Api("Manages paymentStatus services")
@RestController
@RequestMapping("api/admin/paymentStatus")
public class PaymentStatusRestAdmin {

@Autowired
private PaymentStatusAdminService paymentStatusService;

@Autowired
private PaymentStatusConverter paymentStatusConverter;


            @ApiOperation("Updates the specified  paymentStatus")
            @PutMapping("/")
            public  PaymentStatusVo update(@RequestBody  PaymentStatusVo  paymentStatusVo){
            PaymentStatus paymentStatus = paymentStatusConverter.toItem(paymentStatusVo);
            paymentStatus = paymentStatusService.update(paymentStatus);
            return paymentStatusConverter.toVo(paymentStatus);
            }

    @ApiOperation("Finds a list of all paymentStatuss")
    @GetMapping("/")
    public List<PaymentStatusVo> findAll(){
        return paymentStatusConverter.toVo(paymentStatusService.findAll());
    }

    @ApiOperation("Finds a paymentStatus with associated lists by id")
    @GetMapping("/detail/id/{id}")
    public PaymentStatusVo findByIdWithAssociatedList(@PathVariable Long id){
    return paymentStatusConverter.toVo(paymentStatusService.findByIdWithAssociatedList(id));
    }

    @ApiOperation("Search paymentStatus by a specific criteria")
    @PostMapping("/search")
    public List<PaymentStatusVo> findByCriteria(@RequestBody PaymentStatusVo paymentStatusVo){
        return paymentStatusConverter.toVo(paymentStatusService.findByCriteria(paymentStatusVo));
        }

            @ApiOperation("Finds a paymentStatus by id")
            @GetMapping("/id/{id}")
            public PaymentStatusVo findById(@PathVariable Long id){
            return paymentStatusConverter.toVo(paymentStatusService.findById(id));
            }

            @ApiOperation("Saves the specified  paymentStatus")
            @PostMapping("/")
            public PaymentStatusVo save(@RequestBody PaymentStatusVo paymentStatusVo){
            PaymentStatus paymentStatus = paymentStatusConverter.toItem(paymentStatusVo);
            paymentStatus = paymentStatusService.save(paymentStatus);
            return paymentStatusConverter.toVo(paymentStatus);
            }

            @ApiOperation("Delete the specified paymentStatus")
            @DeleteMapping("/")
            public int delete(@RequestBody PaymentStatusVo paymentStatusVo){
            PaymentStatus paymentStatus = paymentStatusConverter.toItem(paymentStatusVo);
            return paymentStatusService.delete(paymentStatus);
            }

            @ApiOperation("Deletes a paymentStatus by id")
            @DeleteMapping("/id/{id}")
            public int deleteById(@PathVariable Long id){
            return paymentStatusService.deleteById(id);
            }




            @PutMapping("/archiver/")
            public PaymentStatusVo archiver(@RequestBody PaymentStatusVo paymentStatusVo){
                PaymentStatus paymentStatus = paymentStatusService.archiver(paymentStatusConverter.toItem(paymentStatusVo));
                return paymentStatusConverter.toVo(paymentStatus);
                }

            @PutMapping("/desarchiver/")
            public PaymentStatusVo desarchiver(@RequestBody PaymentStatusVo paymentStatusVo){
                PaymentStatus paymentStatus = paymentStatusService.desarchiver(paymentStatusConverter.toItem(paymentStatusVo));
                return paymentStatusConverter.toVo(paymentStatus);}
            }
