package  com.ird.faa.ws.rest.provided.facade.guest;

import com.ird.faa.service.guest.facade.ExpensesCategoryGuestService;

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
import com.ird.faa.bean.ExpensesCategory;
import com.ird.faa.ws.rest.provided.converter.ExpensesCategoryConverter;
import com.ird.faa.ws.rest.provided.vo.ExpensesCategoryVo;

@Api("Manages expensesCategory services")
@RestController
@RequestMapping("api/guest/expensesCategory")
public class ExpensesCategoryRestGuest {

@Autowired
private ExpensesCategoryGuestService expensesCategoryService;

@Autowired
private ExpensesCategoryConverter expensesCategoryConverter;


            @ApiOperation("Updates the specified  expensesCategory")
            @PutMapping("/")
            public  ExpensesCategoryVo update(@RequestBody  ExpensesCategoryVo  expensesCategoryVo){
            ExpensesCategory expensesCategory = expensesCategoryConverter.toItem(expensesCategoryVo);
            expensesCategory = expensesCategoryService.update(expensesCategory);
            return expensesCategoryConverter.toVo(expensesCategory);
            }

    @ApiOperation("Finds a list of all expensesCategorys")
    @GetMapping("/")
    public List<ExpensesCategoryVo> findAll(){
        return expensesCategoryConverter.toVo(expensesCategoryService.findAll());
    }

    @ApiOperation("Finds a expensesCategory with associated lists by id")
    @GetMapping("/detail/id/{id}")
    public ExpensesCategoryVo findByIdWithAssociatedList(@PathVariable Long id){
    return expensesCategoryConverter.toVo(expensesCategoryService.findByIdWithAssociatedList(id));
    }

    @ApiOperation("Search expensesCategory by a specific criteria")
    @PostMapping("/search")
    public List<ExpensesCategoryVo> findByCriteria(@RequestBody ExpensesCategoryVo expensesCategoryVo){
        return expensesCategoryConverter.toVo(expensesCategoryService.findByCriteria(expensesCategoryVo));
        }

            @ApiOperation("Finds a expensesCategory by id")
            @GetMapping("/id/{id}")
            public ExpensesCategoryVo findById(@PathVariable Long id){
            return expensesCategoryConverter.toVo(expensesCategoryService.findById(id));
            }

            @ApiOperation("Saves the specified  expensesCategory")
            @PostMapping("/")
            public ExpensesCategoryVo save(@RequestBody ExpensesCategoryVo expensesCategoryVo){
            ExpensesCategory expensesCategory = expensesCategoryConverter.toItem(expensesCategoryVo);
            expensesCategory = expensesCategoryService.save(expensesCategory);
            return expensesCategoryConverter.toVo(expensesCategory);
            }

            @ApiOperation("Delete the specified expensesCategory")
            @DeleteMapping("/")
            public int delete(@RequestBody ExpensesCategoryVo expensesCategoryVo){
            ExpensesCategory expensesCategory = expensesCategoryConverter.toItem(expensesCategoryVo);
            return expensesCategoryService.delete(expensesCategory);
            }

            @ApiOperation("Deletes a expensesCategory by id")
            @DeleteMapping("/id/{id}")
            public int deleteById(@PathVariable Long id){
            return expensesCategoryService.deleteById(id);
            }




            @PutMapping("/archiver/")
            public ExpensesCategoryVo archiver(@RequestBody ExpensesCategoryVo expensesCategoryVo){
                ExpensesCategory expensesCategory = expensesCategoryService.archiver(expensesCategoryConverter.toItem(expensesCategoryVo));
                return expensesCategoryConverter.toVo(expensesCategory);
                }

            @PutMapping("/desarchiver/")
            public ExpensesCategoryVo desarchiver(@RequestBody ExpensesCategoryVo expensesCategoryVo){
                ExpensesCategory expensesCategory = expensesCategoryService.desarchiver(expensesCategoryConverter.toItem(expensesCategoryVo));
                return expensesCategoryConverter.toVo(expensesCategory);}
            }
