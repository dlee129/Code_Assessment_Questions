package com.company;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;


public class Test3 {

    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
        BufferedReader in = new BufferedReader(reader);
        String line;
        while((line = in.readLine()) != null){
            ArrayList<String>Port_name = new ArrayList<String>();
            ArrayList<Integer>Port_quantity = new ArrayList<Integer>();
            ArrayList<Integer>Port_price = new ArrayList<Integer>();
            ArrayList<String>Bench_name = new ArrayList<String>();
            ArrayList<Integer>Bench_quantity = new ArrayList<Integer>();
            ArrayList<Integer>Bench_price = new ArrayList<Integer>();
            String []portfolios = line.split("\\|");
            ArrayList<String>Combined_Assets = new ArrayList<String>();
            ArrayList<Double>Percentage = new ArrayList<Double>();

            String []PORT = portfolios[0].split("\\;|\\:");

            for(int i = 1; i < PORT.length; i++){
                String []assets = PORT[i].split("\\,");
                Port_name.add(assets[0]);
                Port_quantity.add(Integer.parseInt(assets[1]));
                Port_price.add(Integer.parseInt(assets[2]));
            }

            int PORT_NAV = 0;
            for(int i = 0; i <Port_name.size();i++){
                PORT_NAV = PORT_NAV + Port_quantity.get(i)*Port_price.get(i);
            }

            String []BENCH = portfolios[1].split("\\:|\\;");
            for(int i = 1; i < BENCH.length; i++){
                String []assets = BENCH[i].split("\\,");
                Bench_name.add(assets[0]);
                Bench_quantity.add(Integer.parseInt(assets[1]));
                Bench_price.add(Integer.parseInt(assets[2]));
            }


            int BENCH_NAV = 0;
            for(int i = 0; i <Bench_name.size();i++){
                BENCH_NAV = BENCH_NAV + Bench_quantity.get(i)*Bench_price.get(i);
            }



            for(int i = 0; i < Port_name.size();i++) {
                String Port_asset = Port_name.get(i);
                if (Bench_name.contains(Port_asset)) {
                    int index = Bench_name.indexOf(Port_asset);
                    Combined_Assets.add(Port_asset);
                    double Bench_percent = (((double) Bench_price.get(index) * Bench_quantity.get(index)) * 100) / BENCH_NAV;
                    double Port_percent = (((double) Port_price.get(i) * Port_quantity.get(i)) * 100) / PORT_NAV;
                    Percentage.add(Port_percent - Bench_percent);
                }
                else{
                    Combined_Assets.add(Port_asset);
                    double Port_percent = (((double) Port_price.get(i) * Port_quantity.get(i)) * 100) / PORT_NAV;
                    Percentage.add(Port_percent);
                }
            }
            for(int i = 0; i < Bench_name.size();i++) {
                String Bench_asset = Bench_name.get(i);
                if (!Combined_Assets.contains(Bench_asset)) {
                    Combined_Assets.add(Bench_asset);
                    double Bench_percent = (((double) Bench_price.get(i) * Bench_quantity.get(i)) * 100) / BENCH_NAV;
                    Percentage.add(-1*Bench_percent);
                }
                else{

                }
            }


            Combined_Assets.trimToSize();
            Percentage.trimToSize();
            for(int i = 0; i < Combined_Assets.size(); i++){
                if(i+1 == Combined_Assets.size()){
                    System.out.print(Combined_Assets.get(i)+":"+Percentage.get(i));
                }else{
                    System.out.print(Combined_Assets.get(i)+":"+Percentage.get(i)+", ");
                }
            }
        }7
    }
}

//PORT:AXN,10,10;BGT,20,30;CXZ,10,30|BENCH:AXN,50,10;BGT,30,30;DFG,30,20