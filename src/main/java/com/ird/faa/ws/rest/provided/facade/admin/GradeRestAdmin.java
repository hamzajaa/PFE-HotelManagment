package  com.ird.faa.ws.rest.provided.facade.admin;

import com.ird.faa.service.admin.facade.GradeAdminService;

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
import com.ird.faa.bean.Grade;
import com.ird.faa.ws.rest.provided.converter.GradeConverter;
import com.ird.faa.ws.rest.provided.vo.GradeVo;

@Api("Manages grade services")
@RestController
@RequestMapping("api/admin/grade")
public class GradeRestAdmin {

@Autowired
private GradeAdminService gradeService;

@Autowired
private GradeConverter gradeConverter;


            @ApiOperation("Updates the specified  grade")
            @PutMapping("/")
            public  GradeVo update(@RequestBody  GradeVo  gradeVo){
            Grade grade = gradeConverter.toItem(gradeVo);
            grade = gradeService.update(grade);
            return gradeConverter.toVo(grade);
            }

    @ApiOperation("Finds a list of all grades")
    @GetMapping("/")
    public List<GradeVo> findAll(){
        return gradeConverter.toVo(gradeService.findAll());
    }

    @ApiOperation("Finds a grade with associated lists by id")
    @GetMapping("/detail/id/{id}")
    public GradeVo findByIdWithAssociatedList(@PathVariable Long id){
    return gradeConverter.toVo(gradeService.findByIdWithAssociatedList(id));
    }

    @ApiOperation("Search grade by a specific criteria")
    @PostMapping("/search")
    public List<GradeVo> findByCriteria(@RequestBody GradeVo gradeVo){
        return gradeConverter.toVo(gradeService.findByCriteria(gradeVo));
        }

            @ApiOperation("Finds a grade by id")
            @GetMapping("/id/{id}")
            public GradeVo findById(@PathVariable Long id){
            return gradeConverter.toVo(gradeService.findById(id));
            }

            @ApiOperation("Saves the specified  grade")
            @PostMapping("/")
            public GradeVo save(@RequestBody GradeVo gradeVo){
            Grade grade = gradeConverter.toItem(gradeVo);
            grade = gradeService.save(grade);
            return gradeConverter.toVo(grade);
            }

            @ApiOperation("Delete the specified grade")
            @DeleteMapping("/")
            public int delete(@RequestBody GradeVo gradeVo){
            Grade grade = gradeConverter.toItem(gradeVo);
            return gradeService.delete(grade);
            }

            @ApiOperation("Deletes a grade by id")
            @DeleteMapping("/id/{id}")
            public int deleteById(@PathVariable Long id){
            return gradeService.deleteById(id);
            }




            }
