package  com.ird.faa.ws.rest.provided.facade.chercheur;

import com.ird.faa.service.chercheur.facade.AmenityChercheurService;

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
import com.ird.faa.bean.Amenity;
import com.ird.faa.ws.rest.provided.converter.AmenityConverter;
import com.ird.faa.ws.rest.provided.vo.AmenityVo;

@Api("Manages amenity services")
@RestController
@RequestMapping("api/chercheur/amenity")
public class AmenityRestChercheur {

@Autowired
private AmenityChercheurService amenityService;

@Autowired
private AmenityConverter amenityConverter;


            @ApiOperation("Updates the specified  amenity")
            @PutMapping("/")
            public  AmenityVo update(@RequestBody  AmenityVo  amenityVo){
            Amenity amenity = amenityConverter.toItem(amenityVo);
            amenity = amenityService.update(amenity);
            return amenityConverter.toVo(amenity);
            }

    @ApiOperation("Finds a list of all amenitys")
    @GetMapping("/")
    public List<AmenityVo> findAll(){
        return amenityConverter.toVo(amenityService.findAll());
    }

    @ApiOperation("Finds a amenity with associated lists by id")
    @GetMapping("/detail/id/{id}")
    public AmenityVo findByIdWithAssociatedList(@PathVariable Long id){
    return amenityConverter.toVo(amenityService.findByIdWithAssociatedList(id));
    }

    @ApiOperation("Search amenity by a specific criteria")
    @PostMapping("/search")
    public List<AmenityVo> findByCriteria(@RequestBody AmenityVo amenityVo){
        return amenityConverter.toVo(amenityService.findByCriteria(amenityVo));
        }

            @ApiOperation("Finds a amenity by id")
            @GetMapping("/id/{id}")
            public AmenityVo findById(@PathVariable Long id){
            return amenityConverter.toVo(amenityService.findById(id));
            }

            @ApiOperation("Saves the specified  amenity")
            @PostMapping("/")
            public AmenityVo save(@RequestBody AmenityVo amenityVo){
            Amenity amenity = amenityConverter.toItem(amenityVo);
            amenity = amenityService.save(amenity);
            return amenityConverter.toVo(amenity);
            }

            @ApiOperation("Delete the specified amenity")
            @DeleteMapping("/")
            public int delete(@RequestBody AmenityVo amenityVo){
            Amenity amenity = amenityConverter.toItem(amenityVo);
            return amenityService.delete(amenity);
            }

            @ApiOperation("Deletes a amenity by id")
            @DeleteMapping("/id/{id}")
            public int deleteById(@PathVariable Long id){
            return amenityService.deleteById(id);
            }




            }
