package  com.ird.faa.ws.rest.provided.facade.guest;

import com.ird.faa.service.guest.facade.EmployeeGuestService;

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
import com.ird.faa.bean.Employee;
import com.ird.faa.ws.rest.provided.converter.EmployeeConverter;
import com.ird.faa.ws.rest.provided.vo.EmployeeVo;

@Api("Manages employee services")
@RestController
@RequestMapping("api/guest/employee")
public class EmployeeRestGuest {

@Autowired
private EmployeeGuestService employeeService;

@Autowired
private EmployeeConverter employeeConverter;


            @ApiOperation("Updates the specified  employee")
            @PutMapping("/")
            public  EmployeeVo update(@RequestBody  EmployeeVo  employeeVo){
            Employee employee = employeeConverter.toItem(employeeVo);
            employee = employeeService.update(employee);
            return employeeConverter.toVo(employee);
            }

    @ApiOperation("Finds a list of all employees")
    @GetMapping("/")
    public List<EmployeeVo> findAll(){
        return employeeConverter.toVo(employeeService.findAll());
    }

    @ApiOperation("Finds a employee with associated lists by id")
    @GetMapping("/detail/id/{id}")
    public EmployeeVo findByIdWithAssociatedList(@PathVariable Long id){
    return employeeConverter.toVo(employeeService.findByIdWithAssociatedList(id));
    }

    @ApiOperation("Search employee by a specific criteria")
    @PostMapping("/search")
    public List<EmployeeVo> findByCriteria(@RequestBody EmployeeVo employeeVo){
        return employeeConverter.toVo(employeeService.findByCriteria(employeeVo));
        }

            @ApiOperation("Finds a employee by id")
            @GetMapping("/id/{id}")
            public EmployeeVo findById(@PathVariable Long id){
            return employeeConverter.toVo(employeeService.findById(id));
            }

            @ApiOperation("Saves the specified  employee")
            @PostMapping("/")
            public EmployeeVo save(@RequestBody EmployeeVo employeeVo){
            Employee employee = employeeConverter.toItem(employeeVo);
            employee = employeeService.save(employee);
            return employeeConverter.toVo(employee);
            }

            @ApiOperation("Delete the specified employee")
            @DeleteMapping("/")
            public int delete(@RequestBody EmployeeVo employeeVo){
            Employee employee = employeeConverter.toItem(employeeVo);
            return employeeService.delete(employee);
            }

            @ApiOperation("Deletes a employee by id")
            @DeleteMapping("/id/{id}")
            public int deleteById(@PathVariable Long id){
            return employeeService.deleteById(id);
            }
        @ApiOperation("find by departement code")
        @GetMapping("/departement/code/{code}")
        public List<Employee> findByDepartementCode(@PathVariable String code){
        return employeeService.findByDepartementCode(code);
        }

        @ApiOperation("delete by departement code")
        @DeleteMapping("/departement/code/{code}")
        public int deleteByDepartementCode(@PathVariable String code){
        return employeeService.deleteByDepartementCode(code);
        }

        @ApiOperation("find by departement id")
        @GetMapping("/departement/id/{id}")
        public List<Employee> findByDepartementId(@PathVariable Long id){
        return employeeService.findByDepartementId(id);
        }

        @ApiOperation("delete by departement id")
        @DeleteMapping("/departement/id/{id}")
        public int deleteByDepartementId(@PathVariable Long id){
        return employeeService.deleteByDepartementId(id);
        }

        @ApiOperation("find by grade code")
        @GetMapping("/grade/code/{code}")
        public List<Employee> findByGradeCode(@PathVariable String code){
        return employeeService.findByGradeCode(code);
        }

        @ApiOperation("delete by grade code")
        @DeleteMapping("/grade/code/{code}")
        public int deleteByGradeCode(@PathVariable String code){
        return employeeService.deleteByGradeCode(code);
        }

        @ApiOperation("find by grade id")
        @GetMapping("/grade/id/{id}")
        public List<Employee> findByGradeId(@PathVariable Long id){
        return employeeService.findByGradeId(id);
        }

        @ApiOperation("delete by grade id")
        @DeleteMapping("/grade/id/{id}")
        public int deleteByGradeId(@PathVariable Long id){
        return employeeService.deleteByGradeId(id);
        }

        @ApiOperation("find by country code")
        @GetMapping("/country/code/{code}")
        public List<Employee> findByCountryCode(@PathVariable String code){
        return employeeService.findByCountryCode(code);
        }

        @ApiOperation("delete by country code")
        @DeleteMapping("/country/code/{code}")
        public int deleteByCountryCode(@PathVariable String code){
        return employeeService.deleteByCountryCode(code);
        }

        @ApiOperation("find by country id")
        @GetMapping("/country/id/{id}")
        public List<Employee> findByCountryId(@PathVariable Long id){
        return employeeService.findByCountryId(id);
        }

        @ApiOperation("delete by country id")
        @DeleteMapping("/country/id/{id}")
        public int deleteByCountryId(@PathVariable Long id){
        return employeeService.deleteByCountryId(id);
        }

        @ApiOperation("find by city code")
        @GetMapping("/city/code/{code}")
        public List<Employee> findByCityCode(@PathVariable String code){
        return employeeService.findByCityCode(code);
        }

        @ApiOperation("delete by city code")
        @DeleteMapping("/city/code/{code}")
        public int deleteByCityCode(@PathVariable String code){
        return employeeService.deleteByCityCode(code);
        }

        @ApiOperation("find by city id")
        @GetMapping("/city/id/{id}")
        public List<Employee> findByCityId(@PathVariable Long id){
        return employeeService.findByCityId(id);
        }

        @ApiOperation("delete by city id")
        @DeleteMapping("/city/id/{id}")
        public int deleteByCityId(@PathVariable Long id){
        return employeeService.deleteByCityId(id);
        }





            @PutMapping("/archiver/")
            public EmployeeVo archiver(@RequestBody EmployeeVo employeeVo){
                Employee employee = employeeService.archiver(employeeConverter.toItem(employeeVo));
                return employeeConverter.toVo(employee);
                }

            @PutMapping("/desarchiver/")
            public EmployeeVo desarchiver(@RequestBody EmployeeVo employeeVo){
                Employee employee = employeeService.desarchiver(employeeConverter.toItem(employeeVo));
                return employeeConverter.toVo(employee);}
            }
