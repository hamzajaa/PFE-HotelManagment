package  com.ird.faa.ws.rest.provided.facade.chercheur;

import com.ird.faa.service.chercheur.facade.ExpensesChercheurService;

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
import com.ird.faa.bean.Expenses;
import com.ird.faa.ws.rest.provided.converter.ExpensesConverter;
import com.ird.faa.ws.rest.provided.vo.ExpensesVo;

@Api("Manages expenses services")
@RestController
@RequestMapping("api/chercheur/expenses")
public class ExpensesRestChercheur {

@Autowired
private ExpensesChercheurService expensesService;

@Autowired
private ExpensesConverter expensesConverter;


            @ApiOperation("Updates the specified  expenses")
            @PutMapping("/")
            public  ExpensesVo update(@RequestBody  ExpensesVo  expensesVo){
            Expenses expenses = expensesConverter.toItem(expensesVo);
            expenses = expensesService.update(expenses);
            return expensesConverter.toVo(expenses);
            }

    @ApiOperation("Finds a list of all expensess")
    @GetMapping("/")
    public List<ExpensesVo> findAll(){
        return expensesConverter.toVo(expensesService.findAll());
    }

    @ApiOperation("Finds a expenses with associated lists by id")
    @GetMapping("/detail/id/{id}")
    public ExpensesVo findByIdWithAssociatedList(@PathVariable Long id){
    return expensesConverter.toVo(expensesService.findByIdWithAssociatedList(id));
    }

    @ApiOperation("Search expenses by a specific criteria")
    @PostMapping("/search")
    public List<ExpensesVo> findByCriteria(@RequestBody ExpensesVo expensesVo){
        return expensesConverter.toVo(expensesService.findByCriteria(expensesVo));
        }

            @ApiOperation("Finds a expenses by id")
            @GetMapping("/id/{id}")
            public ExpensesVo findById(@PathVariable Long id){
            return expensesConverter.toVo(expensesService.findById(id));
            }

            @ApiOperation("Saves the specified  expenses")
            @PostMapping("/")
            public ExpensesVo save(@RequestBody ExpensesVo expensesVo){
            Expenses expenses = expensesConverter.toItem(expensesVo);
            expenses = expensesService.save(expenses);
            return expensesConverter.toVo(expenses);
            }

            @ApiOperation("Delete the specified expenses")
            @DeleteMapping("/")
            public int delete(@RequestBody ExpensesVo expensesVo){
            Expenses expenses = expensesConverter.toItem(expensesVo);
            return expensesService.delete(expenses);
            }

            @ApiOperation("Deletes a expenses by id")
            @DeleteMapping("/id/{id}")
            public int deleteById(@PathVariable Long id){
            return expensesService.deleteById(id);
            }
        @ApiOperation("find by expensesCategory id")
        @GetMapping("/expensesCategory/id/{id}")
        public List<Expenses> findByExpensesCategoryId(@PathVariable Long id){
        return expensesService.findByExpensesCategoryId(id);
        }

        @ApiOperation("delete by expensesCategory id")
        @DeleteMapping("/expensesCategory/id/{id}")
        public int deleteByExpensesCategoryId(@PathVariable Long id){
        return expensesService.deleteByExpensesCategoryId(id);
        }





            }
