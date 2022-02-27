package com.company;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.Scanner;

public class MT291_CTC1_Fin2TPH {
    public static void main(String[] args) throws Exception {
        String path = "C:\\Users\\HT\\Desktop\\Regression\\Regression\\generateMT\\src\\test\\resources\\mtFiles\\MT291_CTC1_Fin2TPH_Template.txt";
        String mtFile = readFileAsString(path);
        //System.out.println(mtFile);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("HHmmyyMMdd");
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyMMdd");
        SimpleDateFormat nameConventionForMT1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat nameConventionForMT2 = new SimpleDateFormat("HHmmss");

        Random rnd = new Random();
        int number = rnd.nextInt(999);
        String ref = String.format("TK%06d", number);

        Scanner sc = new Scanner(System.in);
        System.out.println("Please input amount: ");
        String amount;
        if(sc.hasNext("^[0-9,]+$")) {
            amount = sc.nextLine();
            System.out.println("Your specified amount: " + amount);

            System.out.println("Please input currency: ");
            String currency;
            if(sc.hasNext("[a-zA-Z]{3}")) {
                currency = sc.nextLine();
                System.out.println("Your specified currency: " + currency);

                String finalString = mtFile
                        .replaceAll("<Insert_Time_Date>", formatter.format(calendar.getTime()))
                        .replaceAll("<Insert_Date>", formatter1.format(calendar.getTime()))
                        .replaceAll("<Insert_Ref>", ref)
                        .replaceAll("<Insert_Currency>", currency.toUpperCase())
                        .replaceAll("<Insert_Amount>", amount);

                String newFilePath = "C:\\Users\\HT\\Desktop\\Regression\\Regression\\generateMT\\src\\test\\resources\\mtFiles\\generatedPayments\\IncomingPayments\\Fin2TPH\\MT291_FBE_" + nameConventionForMT1.format(calendar.getTime()) + "." + nameConventionForMT2.format(calendar.getTime()) + ".000.mt";
                File outputFile = new File(newFilePath);
                FileWriter fw = new FileWriter(outputFile);
                fw.write(finalString);
                fw.close();
            }
        }else{
            System.out.println("Please input only three letters for Currency Code!");
        }
    }

    public static String readFileAsString(String file) throws Exception
    {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

    public static String addChar(String str, char ch, int position) {
        StringBuilder sb = new StringBuilder(str);
        sb.insert(position, ch);
        return sb.toString();
    }
}
