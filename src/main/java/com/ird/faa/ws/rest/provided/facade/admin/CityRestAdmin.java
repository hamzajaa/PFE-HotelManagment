package  com.ird.faa.ws.rest.provided.facade.admin;

import com.ird.faa.service.admin.facade.CityAdminService;

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
import com.ird.faa.bean.City;
import com.ird.faa.ws.rest.provided.converter.CityConverter;
import com.ird.faa.ws.rest.provided.vo.CityVo;

@Api("Manages city services")
@RestController
@RequestMapping("api/admin/city")
public class CityRestAdmin {

@Autowired
private CityAdminService cityService;

@Autowired
private CityConverter cityConverter;


            @ApiOperation("Updates the specified  city")
            @PutMapping("/")
            public  CityVo update(@RequestBody  CityVo  cityVo){
            City city = cityConverter.toItem(cityVo);
            city = cityService.update(city);
            return cityConverter.toVo(city);
            }

    @ApiOperation("Finds a list of all citys")
    @GetMapping("/")
    public List<CityVo> findAll(){
        return cityConverter.toVo(cityService.findAll());
    }

    @ApiOperation("Finds a city with associated lists by id")
    @GetMapping("/detail/id/{id}")
    public CityVo findByIdWithAssociatedList(@PathVariable Long id){
    return cityConverter.toVo(cityService.findByIdWithAssociatedList(id));
    }

    @ApiOperation("Search city by a specific criteria")
    @PostMapping("/search")
    public List<CityVo> findByCriteria(@RequestBody CityVo cityVo){
        return cityConverter.toVo(cityService.findByCriteria(cityVo));
        }

            @ApiOperation("Finds a city by id")
            @GetMapping("/id/{id}")
            public CityVo findById(@PathVariable Long id){
            return cityConverter.toVo(cityService.findById(id));
            }

            @ApiOperation("Saves the specified  city")
            @PostMapping("/")
            public CityVo save(@RequestBody CityVo cityVo){
            City city = cityConverter.toItem(cityVo);
            city = cityService.save(city);
            return cityConverter.toVo(city);
            }

            @ApiOperation("Delete the specified city")
            @DeleteMapping("/")
            public int delete(@RequestBody CityVo cityVo){
            City city = cityConverter.toItem(cityVo);
            return cityService.delete(city);
            }

            @ApiOperation("Deletes a city by id")
            @DeleteMapping("/id/{id}")
            public int deleteById(@PathVariable Long id){
            return cityService.deleteById(id);
            }
        @ApiOperation("find by country code")
        @GetMapping("/country/code/{code}")
        public List<City> findByCountryCode(@PathVariable String code){
        return cityService.findByCountryCode(code);
        }

        @ApiOperation("delete by country code")
        @DeleteMapping("/country/code/{code}")
        public int deleteByCountryCode(@PathVariable String code){
        return cityService.deleteByCountryCode(code);
        }

        @ApiOperation("find by country id")
        @GetMapping("/country/id/{id}")
        public List<City> findByCountryId(@PathVariable Long id){
        return cityService.findByCountryId(id);
        }

        @ApiOperation("delete by country id")
        @DeleteMapping("/country/id/{id}")
        public int deleteByCountryId(@PathVariable Long id){
        return cityService.deleteByCountryId(id);
        }





            }
