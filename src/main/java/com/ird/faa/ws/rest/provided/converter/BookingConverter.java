package  com.ird.faa.ws.rest.provided.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ird.faa.service.util.*;


import com.ird.faa.bean.Booking;
import com.ird.faa.ws.rest.provided.vo.BookingVo;

@Component
public class BookingConverter extends AbstractConverter<Booking,BookingVo>{

        @Autowired
        private PaymentStatusConverter paymentStatusConverter ;
        @Autowired
        private PaymentConverter paymentConverter ;
        @Autowired
        private GuestConverter guestConverter ;
        @Autowired
        private PriceManagerConverter priceManagerConverter ;
        @Autowired
        private BookingStatusConverter bookingStatusConverter ;
        @Autowired
        private BookingItemRoomConverter bookingItemRoomConverter ;
    private Boolean guest;
    private Boolean paymentStatus;
    private Boolean bookingStatus;
    private Boolean priceManager;
        private Boolean bookingItemRooms;
        private Boolean payments;

public  BookingConverter(){
init(true);
}

@Override
public Booking toItem(BookingVo vo) {
if (vo == null) {
return null;
} else {
Booking item = new Booking();
        if(StringUtil.isNotEmpty(vo.getId()))
        item.setId(NumberUtil.toLong(vo.getId()));
        if(StringUtil.isNotEmpty(vo.getBookingNumber()))
        item.setBookingNumber(NumberUtil.toLong(vo.getBookingNumber()));
        if(StringUtil.isNotEmpty(vo.getCheckIn()))
        item.setCheckIn(DateUtil.parse(vo.getCheckIn()));
        if(StringUtil.isNotEmpty(vo.getCheckOut()))
        item.setCheckOut(DateUtil.parse(vo.getCheckOut()));
        if(StringUtil.isNotEmpty(vo.getBookingDate()))
        item.setBookingDate(DateUtil.parse(vo.getBookingDate()));
        if(StringUtil.isNotEmpty(vo.getTotal()))
        item.setTotal(NumberUtil.toBigDecimal(vo.getTotal()));
        if(StringUtil.isNotEmpty(vo.getTotalPay()))
        item.setTotalPay(NumberUtil.toBigDecimal(vo.getTotalPay()));
            if(vo.getArchive() != null)
            item.setArchive(vo.getArchive());
        if(StringUtil.isNotEmpty(vo.getDateArchivage()))
        item.setDateArchivage(DateUtil.parse(vo.getDateArchivage()));
        if(StringUtil.isNotEmpty(vo.getDateCreation()))
        item.setDateCreation(DateUtil.parse(vo.getDateCreation()));
    if(vo.getGuestVo()!=null && this.guest)
        item.setGuest(guestConverter.toItem(vo.getGuestVo())) ;
    if(vo.getPaymentStatusVo()!=null && this.paymentStatus)
        item.setPaymentStatus(paymentStatusConverter.toItem(vo.getPaymentStatusVo())) ;
    if(vo.getBookingStatusVo()!=null && this.bookingStatus)
        item.setBookingStatus(bookingStatusConverter.toItem(vo.getBookingStatusVo())) ;
    if(vo.getPriceManagerVo()!=null && this.priceManager)
        item.setPriceManager(priceManagerConverter.toItem(vo.getPriceManagerVo())) ;

    if(ListUtil.isNotEmpty(vo.getBookingItemRoomsVo()) && this.bookingItemRooms)
        item.setBookingItemRooms(bookingItemRoomConverter.toItem(vo.getBookingItemRoomsVo()));
    if(ListUtil.isNotEmpty(vo.getPaymentsVo()) && this.payments)
        item.setPayments(paymentConverter.toItem(vo.getPaymentsVo()));

return item;
}
}

@Override
public BookingVo toVo(Booking item) {
if (item == null) {
return null;
} else {
BookingVo vo = new BookingVo();
        if(item.getId()!=null)
        vo.setId(NumberUtil.toString(item.getId()));

        if(item.getBookingNumber()!=null)
        vo.setBookingNumber(NumberUtil.toString(item.getBookingNumber()));

        if(item.getCheckIn()!=null)
        vo.setCheckIn(DateUtil.formateDate(item.getCheckIn()));
        if(item.getCheckOut()!=null)
        vo.setCheckOut(DateUtil.formateDate(item.getCheckOut()));
        if(item.getBookingDate()!=null)
        vo.setBookingDate(DateUtil.formateDate(item.getBookingDate()));
        if(item.getTotal()!=null)
        vo.setTotal(NumberUtil.toString(item.getTotal()));

        if(item.getTotalPay()!=null)
        vo.setTotalPay(NumberUtil.toString(item.getTotalPay()));

        if(item.getArchive()!=null)
        vo.setArchive(item.getArchive());
        if(item.getDateArchivage()!=null)
        vo.setDateArchivage(DateUtil.formateDate(item.getDateArchivage()));
        if(item.getDateCreation()!=null)
        vo.setDateCreation(DateUtil.formateDate(item.getDateCreation()));
    if(item.getGuest()!=null && this.guest) {
        vo.setGuestVo(guestConverter.toVo(item.getGuest())) ;
    }
    if(item.getPaymentStatus()!=null && this.paymentStatus) {
        vo.setPaymentStatusVo(paymentStatusConverter.toVo(item.getPaymentStatus())) ;
    }
    if(item.getBookingStatus()!=null && this.bookingStatus) {
        vo.setBookingStatusVo(bookingStatusConverter.toVo(item.getBookingStatus())) ;
    }
    if(item.getPriceManager()!=null && this.priceManager) {
        vo.setPriceManagerVo(priceManagerConverter.toVo(item.getPriceManager())) ;
    }
        if(ListUtil.isNotEmpty(item.getBookingItemRooms()) && this.bookingItemRooms){
        bookingItemRoomConverter.init(true);
        bookingItemRoomConverter.setBooking(false);
        vo.setBookingItemRoomsVo(bookingItemRoomConverter.toVo(item.getBookingItemRooms()));
        bookingItemRoomConverter.setBooking(true);
        }
        if(ListUtil.isNotEmpty(item.getPayments()) && this.payments){
        paymentConverter.init(true);
        paymentConverter.setBooking(false);
        vo.setPaymentsVo(paymentConverter.toVo(item.getPayments()));
        paymentConverter.setBooking(true);
        }

return vo;
}
}

public void init(Boolean value) {
    guest = value;
    paymentStatus = value;
    bookingStatus = value;
    priceManager = value;
        bookingItemRooms = value;
        payments = value;
}


        public PaymentStatusConverter getPaymentStatusConverter(){
        return this.paymentStatusConverter;
        }
        public void setPaymentStatusConverter(PaymentStatusConverter paymentStatusConverter ){
        this.paymentStatusConverter = paymentStatusConverter;
        }
        public PaymentConverter getPaymentConverter(){
        return this.paymentConverter;
        }
        public void setPaymentConverter(PaymentConverter paymentConverter ){
        this.paymentConverter = paymentConverter;
        }
        public GuestConverter getGuestConverter(){
        return this.guestConverter;
        }
        public void setGuestConverter(GuestConverter guestConverter ){
        this.guestConverter = guestConverter;
        }
        public PriceManagerConverter getPriceManagerConverter(){
        return this.priceManagerConverter;
        }
        public void setPriceManagerConverter(PriceManagerConverter priceManagerConverter ){
        this.priceManagerConverter = priceManagerConverter;
        }
        public BookingStatusConverter getBookingStatusConverter(){
        return this.bookingStatusConverter;
        }
        public void setBookingStatusConverter(BookingStatusConverter bookingStatusConverter ){
        this.bookingStatusConverter = bookingStatusConverter;
        }
        public BookingItemRoomConverter getBookingItemRoomConverter(){
        return this.bookingItemRoomConverter;
        }
        public void setBookingItemRoomConverter(BookingItemRoomConverter bookingItemRoomConverter ){
        this.bookingItemRoomConverter = bookingItemRoomConverter;
        }

    public boolean  isGuest(){
    return this.guest;
    }
    public void  setGuest(boolean guest){
    this.guest = guest;
    }
    public boolean  isPaymentStatus(){
    return this.paymentStatus;
    }
    public void  setPaymentStatus(boolean paymentStatus){
    this.paymentStatus = paymentStatus;
    }
    public boolean  isBookingStatus(){
    return this.bookingStatus;
    }
    public void  setBookingStatus(boolean bookingStatus){
    this.bookingStatus = bookingStatus;
    }
    public boolean  isPriceManager(){
    return this.priceManager;
    }
    public void  setPriceManager(boolean priceManager){
    this.priceManager = priceManager;
    }

















        public Boolean  isBookingItemRooms(){
        return this.bookingItemRooms ;
        }
        public void  setBookingItemRooms(Boolean bookingItemRooms ){
        this.bookingItemRooms  = bookingItemRooms ;
        }



        public Boolean  isPayments(){
        return this.payments ;
        }
        public void  setPayments(Boolean payments ){
        this.payments  = payments ;
        }














}
