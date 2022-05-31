package com.ird.faa.bean;

import javax.persistence.*;
import java.util.Objects;



@Entity
@Table(name = "expenses")
public class Expenses   {

@Id
    @SequenceGenerator(name="expenses_seq",sequenceName="expenses_seq",
    allocationSize=1, initialValue = 10000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="expenses_seq")
private Long id;

            @Column(length = 500)
            private String title;
            @Column(length = 500)
            private String amount;

    @ManyToOne
    private ExpensesCategory expensesCategory ;


public Expenses(){
super();
}


            public Long getId(){
            return this.id;
            }
            public void setId(Long id){
            this.id = id;
            }
            public String getTitle(){
            return this.title;
            }
            public void setTitle(String title){
            this.title = title;
            }
            public ExpensesCategory getExpensesCategory(){
            return this.expensesCategory;
            }
            public void setExpensesCategory(ExpensesCategory expensesCategory){
            this.expensesCategory = expensesCategory;
            }
            public String getAmount(){
            return this.amount;
            }
            public void setAmount(String amount){
            this.amount = amount;
            }

        @Override
        public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expenses expenses = (Expenses) o;
        return id != null && id.equals(expenses.id);
        }

        @Override
        public int hashCode() {
        return Objects.hash(id);
        }

}

