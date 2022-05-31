package  com.ird.faa.ws.rest.provided.vo;

    import java.util.List;
    import java.util.Date;
    import javax.persistence.Temporal;
    import javax.persistence.TemporalType;
    import com.fasterxml.jackson.annotation.JsonFormat;
    import java.math.BigDecimal;

public class CouponManagmentVo {

    private String id ;
    private String title ;
    private String description ;
    private String image ;
    private String couponDePeriod ;
    private String couponFiPeriod ;
    private String couponCode ;
    private String couponValue ;
    private String minAmount ;
    private String maxAmount ;
    private String limitPerUser ;
    private String limitPerCoupon ;


            private String couponDePeriodMax ;
            private String couponDePeriodMin ;
            private String couponFiPeriodMax ;
            private String couponFiPeriodMin ;
            private String couponValueMax ;
            private String couponValueMin ;
            private String minAmountMax ;
            private String minAmountMin ;
            private String maxAmountMax ;
            private String maxAmountMin ;
            private String limitPerUserMax ;
            private String limitPerUserMin ;
            private String limitPerCouponMax ;
            private String limitPerCouponMin ;

        private CouponTypeVo couponTypeVo ;

    private List<EmployeeVo> employeesVo ;
    private List<RoomTypeVo> roomTypesVo ;
    private List<PaidServiceVo> paidServicesVo ;

    public CouponManagmentVo(){
    super();
    }

        public String getId(){
        return this.id;
        }

        public void setId(String id){
        this.id = id;
        }
        public String getTitle(){
        return this.title;
        }

        public void setTitle(String title){
        this.title = title;
        }
        public String getDescription(){
        return this.description;
        }

        public void setDescription(String description){
        this.description = description;
        }
        public String getImage(){
        return this.image;
        }

        public void setImage(String image){
        this.image = image;
        }
        public String getCouponDePeriod(){
        return this.couponDePeriod;
        }

        public void setCouponDePeriod(String couponDePeriod){
        this.couponDePeriod = couponDePeriod;
        }
        public String getCouponFiPeriod(){
        return this.couponFiPeriod;
        }

        public void setCouponFiPeriod(String couponFiPeriod){
        this.couponFiPeriod = couponFiPeriod;
        }
        public String getCouponCode(){
        return this.couponCode;
        }

        public void setCouponCode(String couponCode){
        this.couponCode = couponCode;
        }
        public String getCouponValue(){
        return this.couponValue;
        }

        public void setCouponValue(String couponValue){
        this.couponValue = couponValue;
        }
        public String getMinAmount(){
        return this.minAmount;
        }

        public void setMinAmount(String minAmount){
        this.minAmount = minAmount;
        }
        public String getMaxAmount(){
        return this.maxAmount;
        }

        public void setMaxAmount(String maxAmount){
        this.maxAmount = maxAmount;
        }
        public String getLimitPerUser(){
        return this.limitPerUser;
        }

        public void setLimitPerUser(String limitPerUser){
        this.limitPerUser = limitPerUser;
        }
        public String getLimitPerCoupon(){
        return this.limitPerCoupon;
        }

        public void setLimitPerCoupon(String limitPerCoupon){
        this.limitPerCoupon = limitPerCoupon;
        }


            public String getCouponDePeriodMax(){
            return this.couponDePeriodMax;
            }

            public String getCouponDePeriodMin(){
            return this.couponDePeriodMin;
            }

            public void setCouponDePeriodMax(String couponDePeriodMax){
            this.couponDePeriodMax = couponDePeriodMax;
            }

            public void setCouponDePeriodMin(String couponDePeriodMin){
            this.couponDePeriodMin = couponDePeriodMin;
            }

            public String getCouponFiPeriodMax(){
            return this.couponFiPeriodMax;
            }

            public String getCouponFiPeriodMin(){
            return this.couponFiPeriodMin;
            }

            public void setCouponFiPeriodMax(String couponFiPeriodMax){
            this.couponFiPeriodMax = couponFiPeriodMax;
            }

            public void setCouponFiPeriodMin(String couponFiPeriodMin){
            this.couponFiPeriodMin = couponFiPeriodMin;
            }

            public String getCouponValueMax(){
            return this.couponValueMax;
            }

            public String getCouponValueMin(){
            return this.couponValueMin;
            }

            public void setCouponValueMax(String couponValueMax){
            this.couponValueMax = couponValueMax;
            }

            public void setCouponValueMin(String couponValueMin){
            this.couponValueMin = couponValueMin;
            }

            public String getMinAmountMax(){
            return this.minAmountMax;
            }

            public String getMinAmountMin(){
            return this.minAmountMin;
            }

            public void setMinAmountMax(String minAmountMax){
            this.minAmountMax = minAmountMax;
            }

            public void setMinAmountMin(String minAmountMin){
            this.minAmountMin = minAmountMin;
            }

            public String getMaxAmountMax(){
            return this.maxAmountMax;
            }

            public String getMaxAmountMin(){
            return this.maxAmountMin;
            }

            public void setMaxAmountMax(String maxAmountMax){
            this.maxAmountMax = maxAmountMax;
            }

            public void setMaxAmountMin(String maxAmountMin){
            this.maxAmountMin = maxAmountMin;
            }

            public String getLimitPerUserMax(){
            return this.limitPerUserMax;
            }

            public String getLimitPerUserMin(){
            return this.limitPerUserMin;
            }

            public void setLimitPerUserMax(String limitPerUserMax){
            this.limitPerUserMax = limitPerUserMax;
            }

            public void setLimitPerUserMin(String limitPerUserMin){
            this.limitPerUserMin = limitPerUserMin;
            }

            public String getLimitPerCouponMax(){
            return this.limitPerCouponMax;
            }

            public String getLimitPerCouponMin(){
            return this.limitPerCouponMin;
            }

            public void setLimitPerCouponMax(String limitPerCouponMax){
            this.limitPerCouponMax = limitPerCouponMax;
            }

            public void setLimitPerCouponMin(String limitPerCouponMin){
            this.limitPerCouponMin = limitPerCouponMin;
            }


        public CouponTypeVo getCouponTypeVo(){
        return this.couponTypeVo;
        }

        public void setCouponTypeVo(CouponTypeVo couponTypeVo){
        this.couponTypeVo = couponTypeVo;
        }


        public List<EmployeeVo> getEmployeesVo(){
        return this.employeesVo;
        }

        public void setEmployeesVo(List<EmployeeVo> employeesVo){
            this.employeesVo = employeesVo;
            }

        public List<RoomTypeVo> getRoomTypesVo(){
        return this.roomTypesVo;
        }

        public void setRoomTypesVo(List<RoomTypeVo> roomTypesVo){
            this.roomTypesVo = roomTypesVo;
            }

        public List<PaidServiceVo> getPaidServicesVo(){
        return this.paidServicesVo;
        }

        public void setPaidServicesVo(List<PaidServiceVo> paidServicesVo){
            this.paidServicesVo = paidServicesVo;
            }

            }
