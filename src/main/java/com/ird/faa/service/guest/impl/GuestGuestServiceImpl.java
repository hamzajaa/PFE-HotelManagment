package com.ird.faa.service.guest.impl;

import java.util.List;
    import java.util.Date;

import java.util.ArrayList;

import com.ird.faa.bean.Expenses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import com.ird.faa.bean.Guest;
        import com.ird.faa.bean.Country;
import com.ird.faa.dao.GuestDao;
import com.ird.faa.service.guest.facade.GuestGuestService;
        import com.ird.faa.service.guest.facade.CountryGuestService;

import com.ird.faa.ws.rest.provided.vo.GuestVo;
import com.ird.faa.service.util.*;

    import com.ird.faa.service.core.facade.ArchivableService;
import com.ird.faa.service.core.impl.AbstractServiceImpl;

@Service
public class GuestGuestServiceImpl extends AbstractServiceImpl<Guest> implements GuestGuestService {

@Autowired
private GuestDao guestDao;

    @Autowired
    private ArchivableService<Guest> archivableService;
        @Autowired
        private CountryGuestService countryService ;


@Autowired
private EntityManager entityManager;

    @Override
    public Guest findByUsername(String username){
    return guestDao.findByUsername(username);
    }

@Override
public List<Guest> findAll(){
        return guestDao.findAll();
}

        @Override
        public List<Guest> findByCountryCode(String code){
        return guestDao.findByCountryCode(code);
        }

        @Override
        @Transactional
        public int deleteByCountryCode(String code){
        return guestDao.deleteByCountryCode(code);
        }

        @Override
        public List<Guest> findByCountryId(Long id){
        return guestDao.findByCountryId(id);
        }

        @Override
        @Transactional
        public int deleteByCountryId(Long id){
        return guestDao.deleteByCountryId(id);
        }


@Override
public Guest findById(Long id){
if(id==null) return null;
return guestDao.getOne(id);
}

@Override
public Guest findByIdWithAssociatedList(Long id){
    return findById(id);
}
    @Override
    public Guest archiver(Guest guest) {
    if (guest.getArchive() == null) {
    guest.setArchive(false);
    }
    guest.setArchive(true);
    guest.setDateArchivage(new Date());
    guestDao.save(guest);
    return guest;

    }

    @Override
    public Guest desarchiver(Guest guest) {
    if (guest.getArchive() == null) {
    guest.setArchive(false);
    }
    guest.setArchive(false);
    guestDao.save(guest);
    return guest;
    }




@Transactional
public int deleteById(Long id){
int res=0;
if(guestDao.findById(id).isPresent())  {
guestDao.deleteById(id);
res = 1;
}
return res;
}


@Override
public Guest update(Guest guest){
Guest foundedGuest = findById(guest.getId());
if(foundedGuest==null) return null;
else{
    archivableService.prepare(guest);
return  guestDao.save(guest);
}
}
    private void prepareSave(Guest guest){
        guest.setDateCreation(new Date());
        if(guest.getDateArchivage() == null)
        guest.setDateArchivage(new Date());
                guest.setCredentialsNonExpired(false);
                guest.setEnabled(false);
                guest.setAccountNonExpired(false);
                guest.setAccountNonLocked(false);
                guest.setPasswordChanged(false);
                    if(guest.getArchive() == null)
                guest.setArchive(false);




    }

@Override
public Guest save (Guest guest){
    prepareSave(guest);



    findCountry(guest);

    return guestDao.save(guest);


}

@Override
public List<Guest> save(List<Guest> guests){
List<Guest> list = new ArrayList<>();
for(Guest guest: guests){
list.add(save(guest));
}
return list;
}



@Override
@Transactional
public int delete(Guest guest){
    if(guest.getId()==null) return -1;
    Guest foundedGuest = findById(guest.getId());
    if(foundedGuest==null) return -1;
guestDao.delete(foundedGuest);
return 1;
}


public List<Guest> findByCriteria(GuestVo guestVo){

String query = "SELECT o FROM Guest o where 1=1 ";

            query += SearchUtil.addConstraint( "o", "id","=",guestVo.getId());
            query += SearchUtil.addConstraint( "o", "email","LIKE",guestVo.getEmail());
            query += SearchUtil.addConstraint( "o", "mobile","=",guestVo.getMobile());
            query += SearchUtil.addConstraint( "o", "credentialsNonExpired","=",guestVo.getCredentialsNonExpired());
            query += SearchUtil.addConstraint( "o", "enabled","=",guestVo.getEnabled());
            query += SearchUtil.addConstraint( "o", "accountNonExpired","=",guestVo.getAccountNonExpired());
            query += SearchUtil.addConstraint( "o", "accountNonLocked","=",guestVo.getAccountNonLocked());
            query += SearchUtil.addConstraint( "o", "passwordChanged","=",guestVo.getPasswordChanged());
        query += SearchUtil.addConstraintDate( "o", "createdAt","=",guestVo.getCreatedAt());
        query += SearchUtil.addConstraintDate( "o", "updatedAt","=",guestVo.getUpdatedAt());
            query += SearchUtil.addConstraint( "o", "username","LIKE",guestVo.getUsername());
            query += SearchUtil.addConstraint( "o", "password","LIKE",guestVo.getPassword());
            query += SearchUtil.addConstraint( "o", "prenom","LIKE",guestVo.getPrenom());
            query += SearchUtil.addConstraint( "o", "nom","LIKE",guestVo.getNom());
            query += SearchUtil.addConstraint( "o", "archive","=",guestVo.getArchive());
        query += SearchUtil.addConstraintDate( "o", "dateArchivage","=",guestVo.getDateArchivage());
        query += SearchUtil.addConstraintDate( "o", "dateCreation","=",guestVo.getDateCreation());
            query += SearchUtil.addConstraintMinMax("o","mobile",guestVo.getMobileMin(),guestVo.getMobileMax());
            query += SearchUtil.addConstraintMinMaxDate("o","createdAt",guestVo.getCreatedAtMin(),guestVo.getCreatedAtMax());
            query += SearchUtil.addConstraintMinMaxDate("o","updatedAt",guestVo.getUpdatedAtMin(),guestVo.getUpdatedAtMax());
            query += SearchUtil.addConstraintMinMaxDate("o","dateArchivage",guestVo.getDateArchivageMin(),guestVo.getDateArchivageMax());
            query += SearchUtil.addConstraintMinMaxDate("o","dateCreation",guestVo.getDateCreationMin(),guestVo.getDateCreationMax());
    if(guestVo.getCountryVo()!=null){
        query += SearchUtil.addConstraint( "o", "country.id","=",guestVo.getCountryVo().getId());
            query += SearchUtil.addConstraint( "o", "country.code","LIKE",guestVo.getCountryVo().getCode());
    }

return entityManager.createQuery(query).getResultList();
}

    private void findCountry(Guest guest){
        Country loadedCountry =countryService.findByIdOrCode(guest.getCountry());

    if(loadedCountry==null ) {
    return;
    }
    guest.setCountry(loadedCountry);
    }

@Override
@Transactional
public void delete(List<Guest> guests){
if(ListUtil.isNotEmpty(guests)){
guests.forEach(e->guestDao.delete(e));
}
}
@Override
public void update(List<Guest> guests){
if(ListUtil.isNotEmpty(guests)){
guests.forEach(e->guestDao.save(e));
}
}





    }
