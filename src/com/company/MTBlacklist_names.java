package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class MTBlacklist_names {
    public static void main(String[] args) throws Exception {
        String path = "C:\\Users\\teuta\\Desktop\\Regression\\generateMT\\src\\test\\resources\\mtFiles\\MT103_BlackListNames_Template.txt";
        String mtFile = readFileAsString(path);
        //System.out.println(mtFile);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("HHmmyyMMdd");
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyMMdd");
        SimpleDateFormat nameConventionForMT1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat nameConventionForMT2 = new SimpleDateFormat("HHmmss");


        Random rnd = new Random();
        int number = rnd.nextInt(999);
        // this will convert any number sequence into 6 character.
        String ref = String.format("Ref%03d TK", number);



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

                System.out.println("Please input Recipient BIC:");
                String recipient;

                if(sc.hasNext("[a-zA-Z0-9]{11}")) {
                    recipient = sc.nextLine();
                    System.out.println("Your recipient BIC is: " + recipient.toUpperCase());
                    String regularRecipient = addChar(recipient.toUpperCase(), 'A', 8);
                    System.out.println("Please input Beneficiary BIC:");
                    String beneficiary = sc.nextLine();
                    System.out.println("Your beneficiary BIC is: " + beneficiary.toUpperCase());


                    String hcobClient = selectDebtor();
                    String clientDetails[] = hcobClient.split(",");
                    String debtorIban = clientDetails[0].replaceAll("\\s+","");
                    String name = clientDetails[1];
                    String address = clientDetails[2];
                    String country = clientDetails[3];
                    String city = clientDetails[4];
                    System.out.println("Name: "+name+" iban: "+debtorIban+" city:"+city+ "country" +country+ " address: "+address);

                    String finalString = mtFile
                            .replaceAll("<Insert_Time_Date>", formatter.format(calendar.getTime()))
                            .replaceAll("<Insert_Date>", formatter1.format(calendar.getTime()))
                            .replaceAll("<Insert_Ref>", ref)
                            .replaceAll("<Insert_Currency>", currency.toUpperCase())
                            .replaceAll("<Insert_Amount>", amount)
                            .replaceAll("<Receiver_BIC>", regularRecipient)
                            .replaceAll("<Receiver_BIC>", regularRecipient)
                            .replaceAll("<Beneficiary_BIC>", beneficiary.toUpperCase())
                            .replaceAll("<Debtor_Iban>",debtorIban)
                            .replaceAll("<Debtor_Name>",name)
                            .replaceAll("<Debtor_Country>",country)
                            .replaceAll("<Debtor_Address>",address)
                            .replaceAll("<Debtor_City>",city);


                    String newFilePath = "C:\\Users\\teuta\\Desktop\\Regression\\generateMT\\src\\test\\resources\\mtFiles\\generatedPayments\\IncomingPayments\\Blacklist Names\\MT103_FBE_" + nameConventionForMT1.format(calendar.getTime()) + "." + nameConventionForMT2.format(calendar.getTime()) + ".000.mt";
                    File outputFile = new File(newFilePath);
                    FileWriter fw = new FileWriter(outputFile);

                    fw.write(finalString);
                    fw.close();

                    String zippedFile = "C:\\Users\\teuta\\Desktop\\Regression\\generateMT\\src\\test\\resources\\mtFiles\\generatedPayments\\IncomingPayments\\Blacklist Names\\B2H.PAD.BTEB.AZVJ.EFXP.D" + nameConventionForMT1.format(calendar.getTime()) + ".T" + nameConventionForMT2.format(calendar.getTime()) + ".zip";

                    zipFile(newFilePath,zippedFile);

                }else{
                    System.out.println("Wrong BIC format, should consist of 11 characters!");
                }
            }else{
                System.out.println("Please input only three letters for Currency Code!");
            }
        }else{
            System.out.println("Your input is a wrong amount format, please use the 'comma' as a separator if using decimal values for your amount!");
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

    public static String selectDebtor()
    {
        List<String> debtorNames = new ArrayList<>();
        debtorNames.add("DE94 2105 0000 2200 0055 01,XXX,Hafenstraße 18a 24784,DE,Wesaterrönfeld ");
        debtorNames.add("DE24 2105 0000 2200 0055 00,ABCDE,Koenigstraße 4 24768,DE,Rendsburg ");
        debtorNames.add("DE40 2105 0000 2200 0055 03,Joker,Hafenstraße 18a 24784,DE,Wesaterrönfeld ");
        debtorNames.add("DE13 2105 0000 2200 0055 04,Catwomen,Hafenstraße 18a 24784,US,Wesaterrönfeld");
        debtorNames.add("DE83 2105 0000 2200 0055 05,Superwoman,Hafenstraße 18a 24784,DE,Wesaterrönfeld");
        debtorNames.add("DE92 2105 0000 0030 0000 10,Luke Skywalker,Martensdamm 4 24103,DE,Kiel ");
        debtorNames.add("DE67 2105 0000 0051 5008 01,Darth Varder,Martensdamm 4 24103,DE,Kiel ");
        debtorNames.add("DE40 2105 0000 0051 5008 02,Goofy,Martensdamm 4 24103,DE,Kiel");
        debtorNames.add("DE94 2105 0000 1100 3312 45,Donald Duck,Toyon Rd 5404 92115,CA,San Diego ");
        debtorNames.add("DE12 2105 0000 0796 6070 00,Robin Hood,Klinter Weg 98a 24768,DE,Rendsburg ");
        debtorNames.add("DE12 2105 0000 0796 6070 00,No mail confirmation,Klinter Weg 98a 24768,DE,Rendsburg ");
        debtorNames.add("DE12 2105 0000 0796 6070 00,Asterix,Klinter Weg 98a 24768,DE,Rendsburg ");
        debtorNames.add("DE12 2105 0000 0796 6070 00,Escrow Account,Klinter Weg 98a 24768,DE,Rendsburg ");
        int upperBound = 10;
        Random rand = new Random();
        int randomClient = rand.nextInt(upperBound);
        return debtorNames.get(randomClient);
    }
    public static void zipFile(String filePath, String fileDestination) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileDestination);
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        File fileToZip = new File(filePath);
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
        zipOut.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }
        zipOut.close();
        fis.close();
        fos.close();
    }
}
