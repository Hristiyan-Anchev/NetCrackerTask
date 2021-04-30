import domain.Agreement;
import domain.BaseProduct;
import domain.Product;
import util.AgreementNameCreatorImpl;
import util.interfaces.AgreementNameCreator;

import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {
        AgreementNameCreator anc = new AgreementNameCreatorImpl();

        var prod1 = new Product("pd1",new BigDecimal(1),new ArrayList<>());
        var prod2 = new Product("pd2",new BigDecimal(2),new ArrayList<>());
        var prod3 = new Product("pd3",new BigDecimal(3),new ArrayList<>());
        var prod4 = new Product("pd4",new BigDecimal(4),new ArrayList<>());

        var agreement = new Agreement("Goshko",new ArrayList<>(),anc);

        BaseProduct res1 = agreement.addChild(prod1);
        BaseProduct res2 = prod1.addChild(prod2);
        BaseProduct res3 = agreement.addChild(prod2);



        BufferedWriter bfw = null;
        var myContent = agreement.toString();

        try {
            File file = new File(String.format("%s.txt",agreement.getName()));

            FileWriter fw = new FileWriter(file);
            bfw = new BufferedWriter(fw);
            bfw.write(myContent);

        }catch (IOException e){
            e.printStackTrace();
        }
        finally {
            try{
                if(bfw != null){
                    bfw.close();
                }
            }catch (Exception ex){
                System.out.println("KUR BATE !");
            }
        }





    }
}
