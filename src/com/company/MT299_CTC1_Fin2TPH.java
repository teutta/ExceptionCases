package com.company;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class MT299_CTC1_Fin2TPH {
    public static void main(String[] args) throws Exception {
        String path = "C:\\Users\\HT\\Desktop\\Regression\\Regression\\generateMT\\src\\test\\resources\\mtFiles\\MT299_CTC1_Fin2TPH_Template.txt";
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

        Random rnd_1 = new Random();
        int number_1 = rnd_1.nextInt(999);
        String ref_1 = String.format("TK%06d", number_1);

        String finalString = mtFile
                .replaceAll("<Insert_Time_Date>", formatter.format(calendar.getTime()))
                .replaceAll("<Insert_Date>", formatter1.format(calendar.getTime()))
                .replaceAll("<Insert_Ref>", ref)
                .replaceAll("<Insert_Ref2>", ref_1);

        String newFilePath = "C:\\Users\\HT\\Desktop\\Regression\\Regression\\generateMT\\src\\test\\resources\\mtFiles\\generatedPayments\\IncomingPayments\\Fin2TPH\\MT299_FBE_" + nameConventionForMT1.format(calendar.getTime()) + "." + nameConventionForMT2.format(calendar.getTime()) + ".000.mt";
        File outputFile = new File(newFilePath);
        FileWriter fw = new FileWriter(outputFile);
        fw.write(finalString);
        fw.close();
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
