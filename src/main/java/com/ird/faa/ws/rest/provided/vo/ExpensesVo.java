package  com.ird.faa.ws.rest.provided.vo;


public class ExpensesVo {

    private String id ;
    private String title ;
    private String amount ;



        private ExpensesCategoryVo expensesCategoryVo ;


    public ExpensesVo(){
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
        public String getAmount(){
        return this.amount;
        }

        public void setAmount(String amount){
        this.amount = amount;
        }



        public ExpensesCategoryVo getExpensesCategoryVo(){
        return this.expensesCategoryVo;
        }

        public void setExpensesCategoryVo(ExpensesCategoryVo expensesCategoryVo){
        this.expensesCategoryVo = expensesCategoryVo;
        }


            }
