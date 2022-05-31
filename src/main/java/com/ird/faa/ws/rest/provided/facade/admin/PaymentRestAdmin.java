package  com.ird.faa.ws.rest.provided.facade.admin;

import com.ird.faa.service.admin.facade.PaymentAdminService;

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
import com.ird.faa.bean.Payment;
import com.ird.faa.ws.rest.provided.converter.PaymentConverter;
import com.ird.faa.ws.rest.provided.vo.PaymentVo;

@Api("Manages payment services")
@RestController
@RequestMapping("api/admin/payment")
public class PaymentRestAdmin {

@Autowired
private PaymentAdminService paymentService;

@Autowired
private PaymentConverter paymentConverter;


            @ApiOperation("Updates the specified  payment")
            @PutMapping("/")
            public  PaymentVo update(@RequestBody  PaymentVo  paymentVo){
            Payment payment = paymentConverter.toItem(paymentVo);
            payment = paymentService.update(payment);
            return paymentConverter.toVo(payment);
            }

    @ApiOperation("Finds a list of all payments")
    @GetMapping("/")
    public List<PaymentVo> findAll(){
        return paymentConverter.toVo(paymentService.findAll());
    }

    @ApiOperation("Finds a payment with associated lists by id")
    @GetMapping("/detail/id/{id}")
    public PaymentVo findByIdWithAssociatedList(@PathVariable Long id){
    return paymentConverter.toVo(paymentService.findByIdWithAssociatedList(id));
    }

    @ApiOperation("Search payment by a specific criteria")
    @PostMapping("/search")
    public List<PaymentVo> findByCriteria(@RequestBody PaymentVo paymentVo){
        return paymentConverter.toVo(paymentService.findByCriteria(paymentVo));
        }

            @ApiOperation("Finds a payment by id")
            @GetMapping("/id/{id}")
            public PaymentVo findById(@PathVariable Long id){
            return paymentConverter.toVo(paymentService.findById(id));
            }

            @ApiOperation("Saves the specified  payment")
            @PostMapping("/")
            public PaymentVo save(@RequestBody PaymentVo paymentVo){
            Payment payment = paymentConverter.toItem(paymentVo);
            payment = paymentService.save(payment);
            return paymentConverter.toVo(payment);
            }

            @ApiOperation("Delete the specified payment")
            @DeleteMapping("/")
            public int delete(@RequestBody PaymentVo paymentVo){
            Payment payment = paymentConverter.toItem(paymentVo);
            return paymentService.delete(payment);
            }

            @ApiOperation("Deletes a payment by id")
            @DeleteMapping("/id/{id}")
            public int deleteById(@PathVariable Long id){
            return paymentService.deleteById(id);
            }
        @ApiOperation("find by typePayment code")
        @GetMapping("/typePayment/code/{code}")
        public List<Payment> findByTypePaymentCode(@PathVariable String code){
        return paymentService.findByTypePaymentCode(code);
        }

        @ApiOperation("delete by typePayment code")
        @DeleteMapping("/typePayment/code/{code}")
        public int deleteByTypePaymentCode(@PathVariable String code){
        return paymentService.deleteByTypePaymentCode(code);
        }

        @ApiOperation("find by typePayment id")
        @GetMapping("/typePayment/id/{id}")
        public List<Payment> findByTypePaymentId(@PathVariable Long id){
        return paymentService.findByTypePaymentId(id);
        }

        @ApiOperation("delete by typePayment id")
        @DeleteMapping("/typePayment/id/{id}")
        public int deleteByTypePaymentId(@PathVariable Long id){
        return paymentService.deleteByTypePaymentId(id);
        }

        @ApiOperation("find by booking id")
        @GetMapping("/booking/id/{id}")
        public List<Payment> findByBookingId(@PathVariable Long id){
        return paymentService.findByBookingId(id);
        }

        @ApiOperation("delete by booking id")
        @DeleteMapping("/booking/id/{id}")
        public int deleteByBookingId(@PathVariable Long id){
        return paymentService.deleteByBookingId(id);
        }





            }
