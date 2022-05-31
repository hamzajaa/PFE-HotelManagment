package  com.ird.faa.ws.rest.provided.facade.guest;

import com.ird.faa.service.guest.facade.HouseKeepingGuestService;

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
import com.ird.faa.bean.HouseKeeping;
import com.ird.faa.ws.rest.provided.converter.HouseKeepingConverter;
import com.ird.faa.ws.rest.provided.vo.HouseKeepingVo;

@Api("Manages houseKeeping services")
@RestController
@RequestMapping("api/guest/houseKeeping")
public class HouseKeepingRestGuest {

@Autowired
private HouseKeepingGuestService houseKeepingService;

@Autowired
private HouseKeepingConverter houseKeepingConverter;


            @ApiOperation("Updates the specified  houseKeeping")
            @PutMapping("/")
            public  HouseKeepingVo update(@RequestBody  HouseKeepingVo  houseKeepingVo){
            HouseKeeping houseKeeping = houseKeepingConverter.toItem(houseKeepingVo);
            houseKeeping = houseKeepingService.update(houseKeeping);
            return houseKeepingConverter.toVo(houseKeeping);
            }

    @ApiOperation("Finds a list of all houseKeepings")
    @GetMapping("/")
    public List<HouseKeepingVo> findAll(){
        return houseKeepingConverter.toVo(houseKeepingService.findAll());
    }

    @ApiOperation("Finds a houseKeeping with associated lists by id")
    @GetMapping("/detail/id/{id}")
    public HouseKeepingVo findByIdWithAssociatedList(@PathVariable Long id){
    return houseKeepingConverter.toVo(houseKeepingService.findByIdWithAssociatedList(id));
    }

    @ApiOperation("Search houseKeeping by a specific criteria")
    @PostMapping("/search")
    public List<HouseKeepingVo> findByCriteria(@RequestBody HouseKeepingVo houseKeepingVo){
        return houseKeepingConverter.toVo(houseKeepingService.findByCriteria(houseKeepingVo));
        }

            @ApiOperation("Finds a houseKeeping by id")
            @GetMapping("/id/{id}")
            public HouseKeepingVo findById(@PathVariable Long id){
            return houseKeepingConverter.toVo(houseKeepingService.findById(id));
            }

            @ApiOperation("Saves the specified  houseKeeping")
            @PostMapping("/")
            public HouseKeepingVo save(@RequestBody HouseKeepingVo houseKeepingVo){
            HouseKeeping houseKeeping = houseKeepingConverter.toItem(houseKeepingVo);
            houseKeeping = houseKeepingService.save(houseKeeping);
            return houseKeepingConverter.toVo(houseKeeping);
            }

            @ApiOperation("Delete the specified houseKeeping")
            @DeleteMapping("/")
            public int delete(@RequestBody HouseKeepingVo houseKeepingVo){
            HouseKeeping houseKeeping = houseKeepingConverter.toItem(houseKeepingVo);
            return houseKeepingService.delete(houseKeeping);
            }

            @ApiOperation("Deletes a houseKeeping by id")
            @DeleteMapping("/id/{id}")
            public int deleteById(@PathVariable Long id){
            return houseKeepingService.deleteById(id);
            }
        @ApiOperation("find by room roomNumber")
        @GetMapping("/room/roomNumber/{roomNumber}")
        public List<HouseKeeping> findByRoomRoomNumber(@PathVariable Long roomNumber){
        return houseKeepingService.findByRoomRoomNumber(roomNumber);
        }

        @ApiOperation("delete by room roomNumber")
        @DeleteMapping("/room/roomNumber/{roomNumber}")
        public int deleteByRoomRoomNumber(@PathVariable Long roomNumber){
        return houseKeepingService.deleteByRoomRoomNumber(roomNumber);
        }

        @ApiOperation("find by room id")
        @GetMapping("/room/id/{id}")
        public List<HouseKeeping> findByRoomId(@PathVariable Long id){
        return houseKeepingService.findByRoomId(id);
        }

        @ApiOperation("delete by room id")
        @DeleteMapping("/room/id/{id}")
        public int deleteByRoomId(@PathVariable Long id){
        return houseKeepingService.deleteByRoomId(id);
        }

        @ApiOperation("find by roomType shortCode")
        @GetMapping("/roomType/shortCode/{shortCode}")
        public List<HouseKeeping> findByRoomTypeShortCode(@PathVariable String shortCode){
        return houseKeepingService.findByRoomTypeShortCode(shortCode);
        }

        @ApiOperation("delete by roomType shortCode")
        @DeleteMapping("/roomType/shortCode/{shortCode}")
        public int deleteByRoomTypeShortCode(@PathVariable String shortCode){
        return houseKeepingService.deleteByRoomTypeShortCode(shortCode);
        }

        @ApiOperation("find by roomType id")
        @GetMapping("/roomType/id/{id}")
        public List<HouseKeeping> findByRoomTypeId(@PathVariable Long id){
        return houseKeepingService.findByRoomTypeId(id);
        }

        @ApiOperation("delete by roomType id")
        @DeleteMapping("/roomType/id/{id}")
        public int deleteByRoomTypeId(@PathVariable Long id){
        return houseKeepingService.deleteByRoomTypeId(id);
        }

        @ApiOperation("find by floor id")
        @GetMapping("/floor/id/{id}")
        public List<HouseKeeping> findByFloorId(@PathVariable Long id){
        return houseKeepingService.findByFloorId(id);
        }

        @ApiOperation("delete by floor id")
        @DeleteMapping("/floor/id/{id}")
        public int deleteByFloorId(@PathVariable Long id){
        return houseKeepingService.deleteByFloorId(id);
        }

        @ApiOperation("find by houseKeepingStatus name")
        @GetMapping("/houseKeepingStatus/name/{name}")
        public List<HouseKeeping> findByHouseKeepingStatusName(@PathVariable String name){
        return houseKeepingService.findByHouseKeepingStatusName(name);
        }

        @ApiOperation("delete by houseKeepingStatus name")
        @DeleteMapping("/houseKeepingStatus/name/{name}")
        public int deleteByHouseKeepingStatusName(@PathVariable String name){
        return houseKeepingService.deleteByHouseKeepingStatusName(name);
        }

        @ApiOperation("find by houseKeepingStatus id")
        @GetMapping("/houseKeepingStatus/id/{id}")
        public List<HouseKeeping> findByHouseKeepingStatusId(@PathVariable Long id){
        return houseKeepingService.findByHouseKeepingStatusId(id);
        }

        @ApiOperation("delete by houseKeepingStatus id")
        @DeleteMapping("/houseKeepingStatus/id/{id}")
        public int deleteByHouseKeepingStatusId(@PathVariable Long id){
        return houseKeepingService.deleteByHouseKeepingStatusId(id);
        }

        @ApiOperation("find by employee cin")
        @GetMapping("/employee/cin/{cin}")
        public List<HouseKeeping> findByEmployeeCin(@PathVariable String cin){
        return houseKeepingService.findByEmployeeCin(cin);
        }

        @ApiOperation("delete by employee cin")
        @DeleteMapping("/employee/cin/{cin}")
        public int deleteByEmployeeCin(@PathVariable String cin){
        return houseKeepingService.deleteByEmployeeCin(cin);
        }

        @ApiOperation("find by employee id")
        @GetMapping("/employee/id/{id}")
        public List<HouseKeeping> findByEmployeeId(@PathVariable Long id){
        return houseKeepingService.findByEmployeeId(id);
        }

        @ApiOperation("delete by employee id")
        @DeleteMapping("/employee/id/{id}")
        public int deleteByEmployeeId(@PathVariable Long id){
        return houseKeepingService.deleteByEmployeeId(id);
        }





            @PutMapping("/archiver/")
            public HouseKeepingVo archiver(@RequestBody HouseKeepingVo houseKeepingVo){
                HouseKeeping houseKeeping = houseKeepingService.archiver(houseKeepingConverter.toItem(houseKeepingVo));
                return houseKeepingConverter.toVo(houseKeeping);
                }

            @PutMapping("/desarchiver/")
            public HouseKeepingVo desarchiver(@RequestBody HouseKeepingVo houseKeepingVo){
                HouseKeeping houseKeeping = houseKeepingService.desarchiver(houseKeepingConverter.toItem(houseKeepingVo));
                return houseKeepingConverter.toVo(houseKeeping);}
            }
