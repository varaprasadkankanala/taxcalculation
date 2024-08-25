package org.example.javatdd.service;

import org.example.javatdd.model.Employee;
import org.example.javatdd.model.Payslip;
import org.example.javatdd.model.WorkHistory;

import java.util.Date;
import java.util.function.Function;

public class EmployeeService {
    Function<Double,Integer>findTaxBracket= (ctc)      ->
    {

        int taxPercentage =0;
        if(ctc <=300000)
        {
            taxPercentage=1;
        }

        else if (ctc > 300000 &&ctc <=700000) {

            taxPercentage=5;
        }
        else if (ctc > 700000 &&ctc <=100000) {

            taxPercentage = 10;
        }
        return   taxPercentage;
        };
    Function<Employee, Payslip> salaryCal = (employee) ->
    {
       double perMonthWages   =  employee.salary/12;
       double perDayWages=  employee.salary/
                                             employee.currentMonthWorkHistory.noOfWorkingDays;
        double monthCTC =perMonthWages * employee.currentMonthWorkHistory.numberOFDaysLogin;

             int taxpercentage = findTaxBracket.apply(employee.salary);

             double totalTax = (employee.salary*taxpercentage)/100;
             double perMonthTax =totalTax/12;
             Payslip p1 = new Payslip();
             p1.actualAmount= monthCTC      -   perMonthTax;
             p1.monthCTC =monthCTC;
             p1.monthTax=perMonthTax;
             p1.payMonth= new Date();
             return  p1;
    };
      public  static  void  main(String[]args)
      {
          EmployeeService employeeService=new EmployeeService();
          Employee e = new Employee( "venkat");
          e.salary=500000;
          WorkHistory w = new WorkHistory();
          w.numberOFDaysLogin=10;
          w.noOfWorkingDays=20;
          w.noOfLeaves=10;
          w.salaryMonth =new Date();
          e.currentMonthWorkHistory=w;
            Payslip p2 =employeeService.salaryCal.apply(e);
          System.out.println("payslip"+p2.payMonth+" actual amount"
          + p2.actualAmount+"taxamount"+p2.monthTax+"monthctc"+p2.monthCTC );




      }



}
