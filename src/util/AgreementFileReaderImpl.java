package util;

import util.interfaces.AgreementFileReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class AgreementFileReaderImpl implements AgreementFileReader {
    private BufferedReader br;



    @Override
    public String readAgreementFile(String fileName) {
        StringBuilder sb = new StringBuilder();
        try{
            File f = new File(fileName);
            FileReader fr = new FileReader(f);
            br = new BufferedReader(fr);

            String contentLine;
            while((contentLine = br.readLine() ) != null){
                sb.append(contentLine);
            }

            return sb.toString();

        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
        finally {
            if(br != null){
                try{
                    br.close();

                }catch (Exception e){
                    e.printStackTrace();
                    System.out.println("Could not close resource!");
                }
            }
        }


    }
}
