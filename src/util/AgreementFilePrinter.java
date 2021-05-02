package util;

import domain.Agreement;
import util.interfaces.AgreementPrinter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AgreementFilePrinter implements AgreementPrinter {
    private BufferedWriter bufferedWriter;


    @Override
    public void printAgreement(Agreement agreement) {
       var  content = agreement.toString();

        try {
            File file = new File(String.format("%s.txt",agreement.getName()));
            FileWriter fw = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fw);
            bufferedWriter.write(content);

        }catch (IOException e){
            e.printStackTrace();
        }
        finally {
            try{
                if(bufferedWriter != null){
                    bufferedWriter.close();
                }
            }catch (Exception ex){
                ex.printStackTrace();
                System.out.println("Could not close resource!");
            }
        }

    }
}
