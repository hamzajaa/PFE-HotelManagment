package  com.ird.faa.ws.rest.provided.facade.guest;

import com.ird.faa.service.guest.facade.CountryGuestService;

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
import com.ird.faa.bean.Country;
import com.ird.faa.ws.rest.provided.converter.CountryConverter;
import com.ird.faa.ws.rest.provided.vo.CountryVo;

@Api("Manages country services")
@RestController
@RequestMapping("api/guest/country")
public class CountryRestGuest {

@Autowired
private CountryGuestService countryService;

@Autowired
private CountryConverter countryConverter;


            @ApiOperation("Updates the specified  country")
            @PutMapping("/")
            public  CountryVo update(@RequestBody  CountryVo  countryVo){
            Country country = countryConverter.toItem(countryVo);
            country = countryService.update(country);
            return countryConverter.toVo(country);
            }

    @ApiOperation("Finds a list of all countrys")
    @GetMapping("/")
    public List<CountryVo> findAll(){
        return countryConverter.toVo(countryService.findAll());
    }

    @ApiOperation("Finds a country with associated lists by id")
    @GetMapping("/detail/id/{id}")
    public CountryVo findByIdWithAssociatedList(@PathVariable Long id){
    return countryConverter.toVo(countryService.findByIdWithAssociatedList(id));
    }

    @ApiOperation("Search country by a specific criteria")
    @PostMapping("/search")
    public List<CountryVo> findByCriteria(@RequestBody CountryVo countryVo){
        return countryConverter.toVo(countryService.findByCriteria(countryVo));
        }

            @ApiOperation("Finds a country by id")
            @GetMapping("/id/{id}")
            public CountryVo findById(@PathVariable Long id){
            return countryConverter.toVo(countryService.findById(id));
            }

            @ApiOperation("Saves the specified  country")
            @PostMapping("/")
            public CountryVo save(@RequestBody CountryVo countryVo){
            Country country = countryConverter.toItem(countryVo);
            country = countryService.save(country);
            return countryConverter.toVo(country);
            }

            @ApiOperation("Delete the specified country")
            @DeleteMapping("/")
            public int delete(@RequestBody CountryVo countryVo){
            Country country = countryConverter.toItem(countryVo);
            return countryService.delete(country);
            }

            @ApiOperation("Deletes a country by id")
            @DeleteMapping("/id/{id}")
            public int deleteById(@PathVariable Long id){
            return countryService.deleteById(id);
            }




            }
