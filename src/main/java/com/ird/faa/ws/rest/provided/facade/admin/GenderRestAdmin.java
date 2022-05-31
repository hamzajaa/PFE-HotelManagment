package com.ird.faa.ws.rest.provided.facade.admin;

import com.ird.faa.bean.Gender;
import com.ird.faa.service.admin.facade.GenderAdminService;
import com.ird.faa.ws.rest.provided.converter.GenderConverter;
import com.ird.faa.ws.rest.provided.vo.GenderVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("Manages gender services")
@RestController
@RequestMapping("api/admin/gender")
public class GenderRestAdmin {

    @Autowired
    private GenderAdminService genderService;

    @Autowired
    private GenderConverter genderConverter;


    @ApiOperation("Updates the specified  gender")
    @PutMapping("/")
    public GenderVo update(@RequestBody  GenderVo  genderVo){
        Gender gender = genderConverter.toItem(genderVo);
        gender = genderService.update(gender);
        return genderConverter.toVo(gender);
    }

    @ApiOperation("Finds a list of all genders")
    @GetMapping("/")
    public List<GenderVo> findAll(){
        return genderConverter.toVo(genderService.findAll());
    }

    @ApiOperation("Finds a gender with associated lists by id")
    @GetMapping("/detail/id/{id}")
    public GenderVo findByIdWithAssociatedList(@PathVariable Long id){
        return genderConverter.toVo(genderService.findByIdWithAssociatedList(id));
    }

    @ApiOperation("Search gender by a specific criteria")
    @PostMapping("/search")
    public List<GenderVo> findByCriteria(@RequestBody GenderVo genderVo){
        return genderConverter.toVo(genderService.findByCriteria(genderVo));
    }

    @ApiOperation("Finds a gender by id")
    @GetMapping("/id/{id}")
    public GenderVo findById(@PathVariable Long id){
        return genderConverter.toVo(genderService.findById(id));
    }

    @ApiOperation("Saves the specified  gender")
    @PostMapping("/")
    public GenderVo save(@RequestBody GenderVo genderVo){
        Gender gender = genderConverter.toItem(genderVo);
        gender = genderService.save(gender);
        return genderConverter.toVo(gender);
    }

    @ApiOperation("Delete the specified gender")
    @DeleteMapping("/")
    public int delete(@RequestBody GenderVo genderVo){
        Gender gender = genderConverter.toItem(genderVo);
        return genderService.delete(gender);
    }

    @ApiOperation("Deletes a gender by id")
    @DeleteMapping("/id/{id}")
    public int deleteById(@PathVariable Long id){
        return genderService.deleteById(id);
    }




}
