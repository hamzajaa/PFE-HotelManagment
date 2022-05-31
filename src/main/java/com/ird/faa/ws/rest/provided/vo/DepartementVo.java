package  com.ird.faa.ws.rest.provided.vo;

    import java.util.List;

public class DepartementVo {

    private String id ;
    private String libelle ;
    private String code ;




    private List<EmployeeVo> employeesVo ;

    public DepartementVo(){
    super();
    }

        public String getId(){
        return this.id;
        }

        public void setId(String id){
        this.id = id;
        }
        public String getLibelle(){
        return this.libelle;
        }

        public void setLibelle(String libelle){
        this.libelle = libelle;
        }
        public String getCode(){
        return this.code;
        }

        public void setCode(String code){
        this.code = code;
        }





        public List<EmployeeVo> getEmployeesVo(){
        return this.employeesVo;
        }

        public void setEmployeesVo(List<EmployeeVo> employeesVo){
            this.employeesVo = employeesVo;
            }

            }
