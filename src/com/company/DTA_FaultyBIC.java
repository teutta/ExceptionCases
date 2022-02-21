package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class DTA_FaultyBIC {
    public static void main(String[] args) throws Exception {
        String path = "C:\\Users\\teuta\\IdeaProjects\\generateMT\\src\\test\\resources\\mtFiles\\DTA_FaultyBIC_Template.txt";
        String dtaFile = readFileAsString(path);
        //System.out.println(mtFile);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("HHmmyyMMdd");
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyMMdd");
        SimpleDateFormat nameConventionForMT1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat nameConventionForMT2 = new SimpleDateFormat("HHmmss");


        Random rnd = new Random();
        int twoDigitNumber = rnd.nextInt(10,99);
        int threeDigitNumber = rnd.nextInt(100,999);
        int totalSum = rnd.nextInt(1,99);
        String finalString = dtaFile
                .replaceAll("<Insert_Date>", formatter1.format(calendar.getTime()))
                .replaceAll("<Insert_Two_Digit_Number>", String.valueOf(twoDigitNumber))
                .replaceAll("<Total_Sum>",String.valueOf(totalSum));

        String newFilePath = "C:\\Users\\teuta\\IdeaProjects\\generateMT\\src\\test\\resources\\mtFiles\\generatedPayments\\IncomingPayments\\BIC Fail\\DTAZV_FaultyBIC_dtazv." + nameConventionForMT1.format(calendar.getTime()) + "." + nameConventionForMT2.format(calendar.getTime()) + "."+threeDigitNumber+".mt";

        File outputFile = new File(newFilePath);
        FileWriter fw = new FileWriter(outputFile);
        fw.write(finalString);
        fw.close();


    }
    public static String readFileAsString(String file) throws Exception
    {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

}
