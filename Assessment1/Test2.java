package com.company;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;


public class Test2 {

    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
        BufferedReader in = new BufferedReader(reader);
        String line;
        while((line = in.readLine()) != null){
            StringTokenizer str = new StringTokenizer(line, "~");
            int years = 0, loan_amount = 0, down_Payment = 0, rate_of_interest = 0;
            double monthly_payment = 0.00, monthly_rate = 0.00, num_of_periods = 0;
            while(str.hasMoreTokens()){
                String token = str.nextToken();
                int number = Integer.parseInt(token);
                if(loan_amount==0){
                    loan_amount = number;
                }else if(years==0&&loan_amount!=0){
                    years = number;
                }else if(rate_of_interest==0&&loan_amount!=0&&years!=0){
                    rate_of_interest = number;
                }else{
                    down_Payment = number;
                }
                monthly_rate = (((double) (rate_of_interest))/(12))/(100);
                num_of_periods = (double) years * 12;
                loan_amount = loan_amount-down_Payment;
                monthly_payment = (monthly_rate*loan_amount)/(1-Math.pow((1+monthly_rate),num_of_periods*-1));
            }
            System.out.print("$");
            System.out.printf("%.2f",monthly_payment);
            System.out.print("~");
            double interest_paid = (monthly_payment*num_of_periods) - (double) loan_amount;
            System.out.print("$");
            System.out.println((int)Math.round(interest_paid));
        }
    }
}