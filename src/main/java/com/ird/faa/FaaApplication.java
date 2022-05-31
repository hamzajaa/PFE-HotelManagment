package com.ird.faa;

import com.ird.faa.bean.Chercheur;
import com.ird.faa.security.bean.Permission;
import com.ird.faa.security.bean.Role;
import com.ird.faa.security.bean.User;
import com.ird.faa.security.common.AuthoritiesConstants;
import com.ird.faa.security.service.facade.RoleService;
import com.ird.faa.security.service.facade.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SpringBootApplication
public class FaaApplication {
    public static ConfigurableApplicationContext ctx;

    public static void main(String[] args) {
        ctx = SpringApplication.run(FaaApplication.class, args)

        ;
    }

    public static ConfigurableApplicationContext getCtx() {
        return ctx;
    }

    @Bean
    public CommandLineRunner demo(UserService userService, RoleService roleService
    ) {

        return (args) -> {
            if (true) {
                Map<String, String> etats = new HashMap<>();
                etats.put("Initialisé", "initialise");
                etats.put("En cours", "encours");
                etats.put("Terminé", "termine");


                // Role chercheur
                Chercheur userForChercheur = new Chercheur("chercheur");

                Role roleForChercheur = new Role();
                roleForChercheur.setAuthority(AuthoritiesConstants.CHERCHEUR);
                List<Permission> permissionsForChercheur = new ArrayList<>();
                addPermissionForChercheur(permissionsForChercheur);
                roleForChercheur.setPermissions(permissionsForChercheur);
                if (userForChercheur.getRoles() == null)
                    userForChercheur.setRoles(new ArrayList<>());

                userForChercheur.getRoles().add(roleForChercheur);
                userService.save(userForChercheur);

                // Role admin
                User userForAdmin = new User("admin");

                Role roleForAdmin = new Role();
                roleForAdmin.setAuthority(AuthoritiesConstants.ADMIN);
                List<Permission> permissionsForAdmin = new ArrayList<>();
                addPermissionForAdmin(permissionsForAdmin);
                roleForAdmin.setPermissions(permissionsForAdmin);
                if (userForAdmin.getRoles() == null)
                    userForAdmin.setRoles(new ArrayList<>());

                userForAdmin.getRoles().add(roleForAdmin);
                userService.save(userForAdmin);

                // Role employee
                User userForEmployee = new User("employee");

                Role roleForEmployee = new Role();
                roleForEmployee.setAuthority(AuthoritiesConstants.EMPLOYEE);
                List<Permission> permissionsForEmployee = new ArrayList<>();
                addPermissionForEmployee(permissionsForEmployee);
                roleForEmployee.setPermissions(permissionsForEmployee);
                if (userForEmployee.getRoles() == null)
                    userForEmployee.setRoles(new ArrayList<>());

                userForEmployee.getRoles().add(roleForEmployee);
                userService.save(userForEmployee);


                // Role guest
                User userForGuest = new User("guest");

                Role roleForGuest = new Role();
                roleForGuest.setAuthority(AuthoritiesConstants.GUEST);
                List<Permission> permissionsForGuest = new ArrayList<>();
                addPermissionForGuest(permissionsForGuest);
                roleForGuest.setPermissions(permissionsForGuest);
                if (userForGuest.getRoles() == null)
                    userForGuest.setRoles(new ArrayList<>());

                userForGuest.getRoles().add(roleForGuest);
                userService.save(userForGuest);
            }
        };
    }

    private static void addPermissionForChercheur(List
                                                          <Permission> permissions) {
        permissions.add(new Permission("Floor.edit"));
        permissions.add(new Permission("Floor.list"));
        permissions.add(new Permission("Floor.view"));
        permissions.add(new Permission("Floor.add"));
        permissions.add(new Permission("Floor.delete"));
        permissions.add(new Permission("Expenses.edit"));
        permissions.add(new Permission("Expenses.list"));
        permissions.add(new Permission("Expenses.view"));
        permissions.add(new Permission("Expenses.add"));
        permissions.add(new Permission("Expenses.delete"));
        permissions.add(new Permission("TypePayment.edit"));
        permissions.add(new Permission("TypePayment.list"));
        permissions.add(new Permission("TypePayment.view"));
        permissions.add(new Permission("TypePayment.add"));
        permissions.add(new Permission("TypePayment.delete"));
        permissions.add(new Permission("HouseKeeping.edit"));
        permissions.add(new Permission("HouseKeeping.list"));
        permissions.add(new Permission("HouseKeeping.view"));
        permissions.add(new Permission("HouseKeeping.add"));
        permissions.add(new Permission("HouseKeeping.delete"));
        permissions.add(new Permission("Booking.edit"));
        permissions.add(new Permission("Booking.list"));
        permissions.add(new Permission("Booking.view"));
        permissions.add(new Permission("Booking.add"));
        permissions.add(new Permission("Booking.delete"));
        permissions.add(new Permission("PriceManager.edit"));
        permissions.add(new Permission("PriceManager.list"));
        permissions.add(new Permission("PriceManager.view"));
        permissions.add(new Permission("PriceManager.add"));
        permissions.add(new Permission("PriceManager.delete"));
        permissions.add(new Permission("Employee.edit"));
        permissions.add(new Permission("Employee.list"));
        permissions.add(new Permission("Employee.view"));
        permissions.add(new Permission("Employee.add"));
        permissions.add(new Permission("Employee.delete"));
        permissions.add(new Permission("BookingRoomState.edit"));
        permissions.add(new Permission("BookingRoomState.list"));
        permissions.add(new Permission("BookingRoomState.view"));
        permissions.add(new Permission("BookingRoomState.add"));
        permissions.add(new Permission("BookingRoomState.delete"));
        permissions.add(new Permission("Departement.edit"));
        permissions.add(new Permission("Departement.list"));
        permissions.add(new Permission("Departement.view"));
        permissions.add(new Permission("Departement.add"));
        permissions.add(new Permission("Departement.delete"));
        permissions.add(new Permission("CouponManagment.edit"));
        permissions.add(new Permission("CouponManagment.list"));
        permissions.add(new Permission("CouponManagment.view"));
        permissions.add(new Permission("CouponManagment.add"));
        permissions.add(new Permission("CouponManagment.delete"));
        permissions.add(new Permission("ExpensesCategory.edit"));
        permissions.add(new Permission("ExpensesCategory.list"));
        permissions.add(new Permission("ExpensesCategory.view"));
        permissions.add(new Permission("ExpensesCategory.add"));
        permissions.add(new Permission("ExpensesCategory.delete"));
        permissions.add(new Permission("Guest.edit"));
        permissions.add(new Permission("Guest.list"));
        permissions.add(new Permission("Guest.view"));
        permissions.add(new Permission("Guest.add"));
        permissions.add(new Permission("Guest.delete"));
        permissions.add(new Permission("Room.edit"));
        permissions.add(new Permission("Room.list"));
        permissions.add(new Permission("Room.view"));
        permissions.add(new Permission("Room.add"));
        permissions.add(new Permission("Room.delete"));
        permissions.add(new Permission("Amenity.edit"));
        permissions.add(new Permission("Amenity.list"));
        permissions.add(new Permission("Amenity.view"));
        permissions.add(new Permission("Amenity.add"));
        permissions.add(new Permission("Amenity.delete"));
        permissions.add(new Permission("BookingStatus.edit"));
        permissions.add(new Permission("BookingStatus.list"));
        permissions.add(new Permission("BookingStatus.view"));
        permissions.add(new Permission("BookingStatus.add"));
        permissions.add(new Permission("BookingStatus.delete"));
        permissions.add(new Permission("PaidService.edit"));
        permissions.add(new Permission("PaidService.list"));
        permissions.add(new Permission("PaidService.view"));
        permissions.add(new Permission("PaidService.add"));
        permissions.add(new Permission("PaidService.delete"));
        permissions.add(new Permission("BookingItemRoom.edit"));
        permissions.add(new Permission("BookingItemRoom.list"));
        permissions.add(new Permission("BookingItemRoom.view"));
        permissions.add(new Permission("BookingItemRoom.add"));
        permissions.add(new Permission("BookingItemRoom.delete"));
        permissions.add(new Permission("Grade.edit"));
        permissions.add(new Permission("Grade.list"));
        permissions.add(new Permission("Grade.view"));
        permissions.add(new Permission("Grade.add"));
        permissions.add(new Permission("Grade.delete"));
        permissions.add(new Permission("City.edit"));
        permissions.add(new Permission("City.list"));
        permissions.add(new Permission("City.view"));
        permissions.add(new Permission("City.add"));
        permissions.add(new Permission("City.delete"));
        permissions.add(new Permission("PaymentStatus.edit"));
        permissions.add(new Permission("PaymentStatus.list"));
        permissions.add(new Permission("PaymentStatus.view"));
        permissions.add(new Permission("PaymentStatus.add"));
        permissions.add(new Permission("PaymentStatus.delete"));
        permissions.add(new Permission("CouponType.edit"));
        permissions.add(new Permission("CouponType.list"));
        permissions.add(new Permission("CouponType.view"));
        permissions.add(new Permission("CouponType.add"));
        permissions.add(new Permission("CouponType.delete"));
        permissions.add(new Permission("PriceType.edit"));
        permissions.add(new Permission("PriceType.list"));
        permissions.add(new Permission("PriceType.view"));
        permissions.add(new Permission("PriceType.add"));
        permissions.add(new Permission("PriceType.delete"));
        permissions.add(new Permission("Payment.edit"));
        permissions.add(new Permission("Payment.list"));
        permissions.add(new Permission("Payment.view"));
        permissions.add(new Permission("Payment.add"));
        permissions.add(new Permission("Payment.delete"));
        permissions.add(new Permission("HouseKeepingStatut.edit"));
        permissions.add(new Permission("HouseKeepingStatut.list"));
        permissions.add(new Permission("HouseKeepingStatut.view"));
        permissions.add(new Permission("HouseKeepingStatut.add"));
        permissions.add(new Permission("HouseKeepingStatut.delete"));
        permissions.add(new Permission("Country.edit"));
        permissions.add(new Permission("Country.list"));
        permissions.add(new Permission("Country.view"));
        permissions.add(new Permission("Country.add"));
        permissions.add(new Permission("Country.delete"));
        permissions.add(new Permission("Chercheur.edit"));
        permissions.add(new Permission("Chercheur.list"));
        permissions.add(new Permission("Chercheur.view"));
        permissions.add(new Permission("Chercheur.add"));
        permissions.add(new Permission("Chercheur.delete"));
        permissions.add(new Permission("RoomType.edit"));
        permissions.add(new Permission("RoomType.list"));
        permissions.add(new Permission("RoomType.view"));
        permissions.add(new Permission("RoomType.add"));
        permissions.add(new Permission("RoomType.delete"));
    }

    private static void addPermissionForAdmin(List
                                                      <Permission> permissions) {
        permissions.add(new Permission("Floor.edit"));
        permissions.add(new Permission("Floor.list"));
        permissions.add(new Permission("Floor.view"));
        permissions.add(new Permission("Floor.add"));
        permissions.add(new Permission("Floor.delete"));
        permissions.add(new Permission("Expenses.edit"));
        permissions.add(new Permission("Expenses.list"));
        permissions.add(new Permission("Expenses.view"));
        permissions.add(new Permission("Expenses.add"));
        permissions.add(new Permission("Expenses.delete"));
        permissions.add(new Permission("TypePayment.edit"));
        permissions.add(new Permission("TypePayment.list"));
        permissions.add(new Permission("TypePayment.view"));
        permissions.add(new Permission("TypePayment.add"));
        permissions.add(new Permission("TypePayment.delete"));
        permissions.add(new Permission("HouseKeeping.edit"));
        permissions.add(new Permission("HouseKeeping.list"));
        permissions.add(new Permission("HouseKeeping.view"));
        permissions.add(new Permission("HouseKeeping.add"));
        permissions.add(new Permission("HouseKeeping.delete"));
        permissions.add(new Permission("Booking.edit"));
        permissions.add(new Permission("Booking.list"));
        permissions.add(new Permission("Booking.view"));
        permissions.add(new Permission("Booking.add"));
        permissions.add(new Permission("Booking.delete"));
        permissions.add(new Permission("PriceManager.edit"));
        permissions.add(new Permission("PriceManager.list"));
        permissions.add(new Permission("PriceManager.view"));
        permissions.add(new Permission("PriceManager.add"));
        permissions.add(new Permission("PriceManager.delete"));
        permissions.add(new Permission("Employee.edit"));
        permissions.add(new Permission("Employee.list"));
        permissions.add(new Permission("Employee.view"));
        permissions.add(new Permission("Employee.add"));
        permissions.add(new Permission("Employee.delete"));
        permissions.add(new Permission("BookingRoomState.edit"));
        permissions.add(new Permission("BookingRoomState.list"));
        permissions.add(new Permission("BookingRoomState.view"));
        permissions.add(new Permission("BookingRoomState.add"));
        permissions.add(new Permission("BookingRoomState.delete"));
        permissions.add(new Permission("Departement.edit"));
        permissions.add(new Permission("Departement.list"));
        permissions.add(new Permission("Departement.view"));
        permissions.add(new Permission("Departement.add"));
        permissions.add(new Permission("Departement.delete"));
        permissions.add(new Permission("CouponManagment.edit"));
        permissions.add(new Permission("CouponManagment.list"));
        permissions.add(new Permission("CouponManagment.view"));
        permissions.add(new Permission("CouponManagment.add"));
        permissions.add(new Permission("CouponManagment.delete"));
        permissions.add(new Permission("ExpensesCategory.edit"));
        permissions.add(new Permission("ExpensesCategory.list"));
        permissions.add(new Permission("ExpensesCategory.view"));
        permissions.add(new Permission("ExpensesCategory.add"));
        permissions.add(new Permission("ExpensesCategory.delete"));
        permissions.add(new Permission("Guest.edit"));
        permissions.add(new Permission("Guest.list"));
        permissions.add(new Permission("Guest.view"));
        permissions.add(new Permission("Guest.add"));
        permissions.add(new Permission("Guest.delete"));
        permissions.add(new Permission("Room.edit"));
        permissions.add(new Permission("Room.list"));
        permissions.add(new Permission("Room.view"));
        permissions.add(new Permission("Room.add"));
        permissions.add(new Permission("Room.delete"));
        permissions.add(new Permission("Amenity.edit"));
        permissions.add(new Permission("Amenity.list"));
        permissions.add(new Permission("Amenity.view"));
        permissions.add(new Permission("Amenity.add"));
        permissions.add(new Permission("Amenity.delete"));
        permissions.add(new Permission("BookingStatus.edit"));
        permissions.add(new Permission("BookingStatus.list"));
        permissions.add(new Permission("BookingStatus.view"));
        permissions.add(new Permission("BookingStatus.add"));
        permissions.add(new Permission("BookingStatus.delete"));
        permissions.add(new Permission("PaidService.edit"));
        permissions.add(new Permission("PaidService.list"));
        permissions.add(new Permission("PaidService.view"));
        permissions.add(new Permission("PaidService.add"));
        permissions.add(new Permission("PaidService.delete"));
        permissions.add(new Permission("BookingItemRoom.edit"));
        permissions.add(new Permission("BookingItemRoom.list"));
        permissions.add(new Permission("BookingItemRoom.view"));
        permissions.add(new Permission("BookingItemRoom.add"));
        permissions.add(new Permission("BookingItemRoom.delete"));
        permissions.add(new Permission("Grade.edit"));
        permissions.add(new Permission("Grade.list"));
        permissions.add(new Permission("Grade.view"));
        permissions.add(new Permission("Grade.add"));
        permissions.add(new Permission("Grade.delete"));
        permissions.add(new Permission("City.edit"));
        permissions.add(new Permission("City.list"));
        permissions.add(new Permission("City.view"));
        permissions.add(new Permission("City.add"));
        permissions.add(new Permission("City.delete"));
        permissions.add(new Permission("PaymentStatus.edit"));
        permissions.add(new Permission("PaymentStatus.list"));
        permissions.add(new Permission("PaymentStatus.view"));
        permissions.add(new Permission("PaymentStatus.add"));
        permissions.add(new Permission("PaymentStatus.delete"));
        permissions.add(new Permission("CouponType.edit"));
        permissions.add(new Permission("CouponType.list"));
        permissions.add(new Permission("CouponType.view"));
        permissions.add(new Permission("CouponType.add"));
        permissions.add(new Permission("CouponType.delete"));
        permissions.add(new Permission("PriceType.edit"));
        permissions.add(new Permission("PriceType.list"));
        permissions.add(new Permission("PriceType.view"));
        permissions.add(new Permission("PriceType.add"));
        permissions.add(new Permission("PriceType.delete"));
        permissions.add(new Permission("Payment.edit"));
        permissions.add(new Permission("Payment.list"));
        permissions.add(new Permission("Payment.view"));
        permissions.add(new Permission("Payment.add"));
        permissions.add(new Permission("Payment.delete"));
        permissions.add(new Permission("HouseKeepingStatut.edit"));
        permissions.add(new Permission("HouseKeepingStatut.list"));
        permissions.add(new Permission("HouseKeepingStatut.view"));
        permissions.add(new Permission("HouseKeepingStatut.add"));
        permissions.add(new Permission("HouseKeepingStatut.delete"));
        permissions.add(new Permission("Country.edit"));
        permissions.add(new Permission("Country.list"));
        permissions.add(new Permission("Country.view"));
        permissions.add(new Permission("Country.add"));
        permissions.add(new Permission("Country.delete"));
        permissions.add(new Permission("Chercheur.edit"));
        permissions.add(new Permission("Chercheur.list"));
        permissions.add(new Permission("Chercheur.view"));
        permissions.add(new Permission("Chercheur.add"));
        permissions.add(new Permission("Chercheur.delete"));
        permissions.add(new Permission("RoomType.edit"));
        permissions.add(new Permission("RoomType.list"));
        permissions.add(new Permission("RoomType.view"));
        permissions.add(new Permission("RoomType.add"));
        permissions.add(new Permission("RoomType.delete"));
    }

    private static void addPermissionForEmployee(List
                                                         <Permission> permissions) {
        permissions.add(new Permission("Floor.edit"));
        permissions.add(new Permission("Floor.list"));
        permissions.add(new Permission("Floor.view"));
        permissions.add(new Permission("Floor.add"));
        permissions.add(new Permission("Floor.delete"));
        permissions.add(new Permission("Expenses.edit"));
        permissions.add(new Permission("Expenses.list"));
        permissions.add(new Permission("Expenses.view"));
        permissions.add(new Permission("Expenses.add"));
        permissions.add(new Permission("Expenses.delete"));
        permissions.add(new Permission("TypePayment.edit"));
        permissions.add(new Permission("TypePayment.list"));
        permissions.add(new Permission("TypePayment.view"));
        permissions.add(new Permission("TypePayment.add"));
        permissions.add(new Permission("TypePayment.delete"));
        permissions.add(new Permission("HouseKeeping.edit"));
        permissions.add(new Permission("HouseKeeping.list"));
        permissions.add(new Permission("HouseKeeping.view"));
        permissions.add(new Permission("HouseKeeping.add"));
        permissions.add(new Permission("HouseKeeping.delete"));
        permissions.add(new Permission("Booking.edit"));
        permissions.add(new Permission("Booking.list"));
        permissions.add(new Permission("Booking.view"));
        permissions.add(new Permission("Booking.add"));
        permissions.add(new Permission("Booking.delete"));
        permissions.add(new Permission("PriceManager.edit"));
        permissions.add(new Permission("PriceManager.list"));
        permissions.add(new Permission("PriceManager.view"));
        permissions.add(new Permission("PriceManager.add"));
        permissions.add(new Permission("PriceManager.delete"));
        permissions.add(new Permission("Employee.edit"));
        permissions.add(new Permission("Employee.list"));
        permissions.add(new Permission("Employee.view"));
        permissions.add(new Permission("Employee.add"));
        permissions.add(new Permission("Employee.delete"));
        permissions.add(new Permission("BookingRoomState.edit"));
        permissions.add(new Permission("BookingRoomState.list"));
        permissions.add(new Permission("BookingRoomState.view"));
        permissions.add(new Permission("BookingRoomState.add"));
        permissions.add(new Permission("BookingRoomState.delete"));
        permissions.add(new Permission("Departement.edit"));
        permissions.add(new Permission("Departement.list"));
        permissions.add(new Permission("Departement.view"));
        permissions.add(new Permission("Departement.add"));
        permissions.add(new Permission("Departement.delete"));
        permissions.add(new Permission("CouponManagment.edit"));
        permissions.add(new Permission("CouponManagment.list"));
        permissions.add(new Permission("CouponManagment.view"));
        permissions.add(new Permission("CouponManagment.add"));
        permissions.add(new Permission("CouponManagment.delete"));
        permissions.add(new Permission("ExpensesCategory.edit"));
        permissions.add(new Permission("ExpensesCategory.list"));
        permissions.add(new Permission("ExpensesCategory.view"));
        permissions.add(new Permission("ExpensesCategory.add"));
        permissions.add(new Permission("ExpensesCategory.delete"));
        permissions.add(new Permission("Guest.edit"));
        permissions.add(new Permission("Guest.list"));
        permissions.add(new Permission("Guest.view"));
        permissions.add(new Permission("Guest.add"));
        permissions.add(new Permission("Guest.delete"));
        permissions.add(new Permission("Room.edit"));
        permissions.add(new Permission("Room.list"));
        permissions.add(new Permission("Room.view"));
        permissions.add(new Permission("Room.add"));
        permissions.add(new Permission("Room.delete"));
        permissions.add(new Permission("Amenity.edit"));
        permissions.add(new Permission("Amenity.list"));
        permissions.add(new Permission("Amenity.view"));
        permissions.add(new Permission("Amenity.add"));
        permissions.add(new Permission("Amenity.delete"));
        permissions.add(new Permission("BookingStatus.edit"));
        permissions.add(new Permission("BookingStatus.list"));
        permissions.add(new Permission("BookingStatus.view"));
        permissions.add(new Permission("BookingStatus.add"));
        permissions.add(new Permission("BookingStatus.delete"));
        permissions.add(new Permission("PaidService.edit"));
        permissions.add(new Permission("PaidService.list"));
        permissions.add(new Permission("PaidService.view"));
        permissions.add(new Permission("PaidService.add"));
        permissions.add(new Permission("PaidService.delete"));
        permissions.add(new Permission("BookingItemRoom.edit"));
        permissions.add(new Permission("BookingItemRoom.list"));
        permissions.add(new Permission("BookingItemRoom.view"));
        permissions.add(new Permission("BookingItemRoom.add"));
        permissions.add(new Permission("BookingItemRoom.delete"));
        permissions.add(new Permission("Grade.edit"));
        permissions.add(new Permission("Grade.list"));
        permissions.add(new Permission("Grade.view"));
        permissions.add(new Permission("Grade.add"));
        permissions.add(new Permission("Grade.delete"));
        permissions.add(new Permission("City.edit"));
        permissions.add(new Permission("City.list"));
        permissions.add(new Permission("City.view"));
        permissions.add(new Permission("City.add"));
        permissions.add(new Permission("City.delete"));
        permissions.add(new Permission("PaymentStatus.edit"));
        permissions.add(new Permission("PaymentStatus.list"));
        permissions.add(new Permission("PaymentStatus.view"));
        permissions.add(new Permission("PaymentStatus.add"));
        permissions.add(new Permission("PaymentStatus.delete"));
        permissions.add(new Permission("CouponType.edit"));
        permissions.add(new Permission("CouponType.list"));
        permissions.add(new Permission("CouponType.view"));
        permissions.add(new Permission("CouponType.add"));
        permissions.add(new Permission("CouponType.delete"));
        permissions.add(new Permission("PriceType.edit"));
        permissions.add(new Permission("PriceType.list"));
        permissions.add(new Permission("PriceType.view"));
        permissions.add(new Permission("PriceType.add"));
        permissions.add(new Permission("PriceType.delete"));
        permissions.add(new Permission("Payment.edit"));
        permissions.add(new Permission("Payment.list"));
        permissions.add(new Permission("Payment.view"));
        permissions.add(new Permission("Payment.add"));
        permissions.add(new Permission("Payment.delete"));
        permissions.add(new Permission("HouseKeepingStatut.edit"));
        permissions.add(new Permission("HouseKeepingStatut.list"));
        permissions.add(new Permission("HouseKeepingStatut.view"));
        permissions.add(new Permission("HouseKeepingStatut.add"));
        permissions.add(new Permission("HouseKeepingStatut.delete"));
        permissions.add(new Permission("Country.edit"));
        permissions.add(new Permission("Country.list"));
        permissions.add(new Permission("Country.view"));
        permissions.add(new Permission("Country.add"));
        permissions.add(new Permission("Country.delete"));
        permissions.add(new Permission("Chercheur.edit"));
        permissions.add(new Permission("Chercheur.list"));
        permissions.add(new Permission("Chercheur.view"));
        permissions.add(new Permission("Chercheur.add"));
        permissions.add(new Permission("Chercheur.delete"));
        permissions.add(new Permission("RoomType.edit"));
        permissions.add(new Permission("RoomType.list"));
        permissions.add(new Permission("RoomType.view"));
        permissions.add(new Permission("RoomType.add"));
        permissions.add(new Permission("RoomType.delete"));
    }

    private static void addPermissionForGuest(List
                                                      <Permission> permissions) {
        permissions.add(new Permission("Floor.edit"));
        permissions.add(new Permission("Floor.list"));
        permissions.add(new Permission("Floor.view"));
        permissions.add(new Permission("Floor.add"));
        permissions.add(new Permission("Floor.delete"));
        permissions.add(new Permission("Expenses.edit"));
        permissions.add(new Permission("Expenses.list"));
        permissions.add(new Permission("Expenses.view"));
        permissions.add(new Permission("Expenses.add"));
        permissions.add(new Permission("Expenses.delete"));
        permissions.add(new Permission("TypePayment.edit"));
        permissions.add(new Permission("TypePayment.list"));
        permissions.add(new Permission("TypePayment.view"));
        permissions.add(new Permission("TypePayment.add"));
        permissions.add(new Permission("TypePayment.delete"));
        permissions.add(new Permission("HouseKeeping.edit"));
        permissions.add(new Permission("HouseKeeping.list"));
        permissions.add(new Permission("HouseKeeping.view"));
        permissions.add(new Permission("HouseKeeping.add"));
        permissions.add(new Permission("HouseKeeping.delete"));
        permissions.add(new Permission("Booking.edit"));
        permissions.add(new Permission("Booking.list"));
        permissions.add(new Permission("Booking.view"));
        permissions.add(new Permission("Booking.add"));
        permissions.add(new Permission("Booking.delete"));
        permissions.add(new Permission("PriceManager.edit"));
        permissions.add(new Permission("PriceManager.list"));
        permissions.add(new Permission("PriceManager.view"));
        permissions.add(new Permission("PriceManager.add"));
        permissions.add(new Permission("PriceManager.delete"));
        permissions.add(new Permission("Employee.edit"));
        permissions.add(new Permission("Employee.list"));
        permissions.add(new Permission("Employee.view"));
        permissions.add(new Permission("Employee.add"));
        permissions.add(new Permission("Employee.delete"));
        permissions.add(new Permission("BookingRoomState.edit"));
        permissions.add(new Permission("BookingRoomState.list"));
        permissions.add(new Permission("BookingRoomState.view"));
        permissions.add(new Permission("BookingRoomState.add"));
        permissions.add(new Permission("BookingRoomState.delete"));
        permissions.add(new Permission("Departement.edit"));
        permissions.add(new Permission("Departement.list"));
        permissions.add(new Permission("Departement.view"));
        permissions.add(new Permission("Departement.add"));
        permissions.add(new Permission("Departement.delete"));
        permissions.add(new Permission("CouponManagment.edit"));
        permissions.add(new Permission("CouponManagment.list"));
        permissions.add(new Permission("CouponManagment.view"));
        permissions.add(new Permission("CouponManagment.add"));
        permissions.add(new Permission("CouponManagment.delete"));
        permissions.add(new Permission("ExpensesCategory.edit"));
        permissions.add(new Permission("ExpensesCategory.list"));
        permissions.add(new Permission("ExpensesCategory.view"));
        permissions.add(new Permission("ExpensesCategory.add"));
        permissions.add(new Permission("ExpensesCategory.delete"));
        permissions.add(new Permission("Guest.edit"));
        permissions.add(new Permission("Guest.list"));
        permissions.add(new Permission("Guest.view"));
        permissions.add(new Permission("Guest.add"));
        permissions.add(new Permission("Guest.delete"));
        permissions.add(new Permission("Room.edit"));
        permissions.add(new Permission("Room.list"));
        permissions.add(new Permission("Room.view"));
        permissions.add(new Permission("Room.add"));
        permissions.add(new Permission("Room.delete"));
        permissions.add(new Permission("Amenity.edit"));
        permissions.add(new Permission("Amenity.list"));
        permissions.add(new Permission("Amenity.view"));
        permissions.add(new Permission("Amenity.add"));
        permissions.add(new Permission("Amenity.delete"));
        permissions.add(new Permission("BookingStatus.edit"));
        permissions.add(new Permission("BookingStatus.list"));
        permissions.add(new Permission("BookingStatus.view"));
        permissions.add(new Permission("BookingStatus.add"));
        permissions.add(new Permission("BookingStatus.delete"));
        permissions.add(new Permission("PaidService.edit"));
        permissions.add(new Permission("PaidService.list"));
        permissions.add(new Permission("PaidService.view"));
        permissions.add(new Permission("PaidService.add"));
        permissions.add(new Permission("PaidService.delete"));
        permissions.add(new Permission("BookingItemRoom.edit"));
        permissions.add(new Permission("BookingItemRoom.list"));
        permissions.add(new Permission("BookingItemRoom.view"));
        permissions.add(new Permission("BookingItemRoom.add"));
        permissions.add(new Permission("BookingItemRoom.delete"));
        permissions.add(new Permission("Grade.edit"));
        permissions.add(new Permission("Grade.list"));
        permissions.add(new Permission("Grade.view"));
        permissions.add(new Permission("Grade.add"));
        permissions.add(new Permission("Grade.delete"));
        permissions.add(new Permission("City.edit"));
        permissions.add(new Permission("City.list"));
        permissions.add(new Permission("City.view"));
        permissions.add(new Permission("City.add"));
        permissions.add(new Permission("City.delete"));
        permissions.add(new Permission("PaymentStatus.edit"));
        permissions.add(new Permission("PaymentStatus.list"));
        permissions.add(new Permission("PaymentStatus.view"));
        permissions.add(new Permission("PaymentStatus.add"));
        permissions.add(new Permission("PaymentStatus.delete"));
        permissions.add(new Permission("CouponType.edit"));
        permissions.add(new Permission("CouponType.list"));
        permissions.add(new Permission("CouponType.view"));
        permissions.add(new Permission("CouponType.add"));
        permissions.add(new Permission("CouponType.delete"));
        permissions.add(new Permission("PriceType.edit"));
        permissions.add(new Permission("PriceType.list"));
        permissions.add(new Permission("PriceType.view"));
        permissions.add(new Permission("PriceType.add"));
        permissions.add(new Permission("PriceType.delete"));
        permissions.add(new Permission("Payment.edit"));
        permissions.add(new Permission("Payment.list"));
        permissions.add(new Permission("Payment.view"));
        permissions.add(new Permission("Payment.add"));
        permissions.add(new Permission("Payment.delete"));
        permissions.add(new Permission("HouseKeepingStatut.edit"));
        permissions.add(new Permission("HouseKeepingStatut.list"));
        permissions.add(new Permission("HouseKeepingStatut.view"));
        permissions.add(new Permission("HouseKeepingStatut.add"));
        permissions.add(new Permission("HouseKeepingStatut.delete"));
        permissions.add(new Permission("Country.edit"));
        permissions.add(new Permission("Country.list"));
        permissions.add(new Permission("Country.view"));
        permissions.add(new Permission("Country.add"));
        permissions.add(new Permission("Country.delete"));
        permissions.add(new Permission("Chercheur.edit"));
        permissions.add(new Permission("Chercheur.list"));
        permissions.add(new Permission("Chercheur.view"));
        permissions.add(new Permission("Chercheur.add"));
        permissions.add(new Permission("Chercheur.delete"));
        permissions.add(new Permission("RoomType.edit"));
        permissions.add(new Permission("RoomType.list"));
        permissions.add(new Permission("RoomType.view"));
        permissions.add(new Permission("RoomType.add"));
        permissions.add(new Permission("RoomType.delete"));
    }


}


