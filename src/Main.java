import domain.Agreement;
import domain.BaseProduct;
import domain.Product;
import util.AgreementFilePrinter;
import util.AgreementFileReaderImpl;
import util.AgreementNameCreatorImpl;
import util.AgreementParserImpl;
import util.interfaces.AgreementFileReader;
import util.interfaces.AgreementNameCreator;
import util.interfaces.AgreementParser;
import util.interfaces.AgreementPrinter;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        AgreementNameCreator agreementNameCreator = new AgreementNameCreatorImpl();
        AgreementPrinter agreementPrinter = new AgreementFilePrinter();
        AgreementFileReader agreementFileReader = new AgreementFileReaderImpl();
        AgreementParser agreementParser = new AgreementParserImpl();

        //create test products
        var prod1 = new Product("pd1",new BigDecimal(1),new ArrayList<>());
        var prod2 = new Product("pd2",new BigDecimal(2),new ArrayList<>());
        var prod3 = new Product("pd3",new BigDecimal(3),new ArrayList<>());
        var prod4 = new Product("pd4",new BigDecimal(4),new ArrayList<>());
        var prod5 = new Product("pd5",new BigDecimal(5),new ArrayList<>());
        var prod6 = new Product("pd6",new BigDecimal(6),new ArrayList<>());
        var prod7 = new Product("pd7",new BigDecimal(7),new ArrayList<>());
        var prod8 = new Product("pd8",new BigDecimal(8),new ArrayList<>());


        var newAgreement = new Agreement("Josh",new ArrayList<>(),agreementNameCreator);

        prod1.addChild(prod2);
        prod2.addChild(prod3);
        prod2.addChild(prod4);


        newAgreement.addChild(prod5);
        prod5.addChild(prod6);

        prod6.addChild(prod7);
        prod6.addChild(prod8);

        newAgreement.addChild(prod1);

        //print agreement to file
        agreementPrinter.printAgreement(newAgreement);

        //read agreement file contents
        var agreementContent = agreementFileReader.readAgreementFile(newAgreement.getName() + ".txt");

        //parse file content to object
        Agreement agreementFromFile = agreementParser.parseAgreement(agreementContent);

        //assert if the agreement parsed from the file matches the content of  the newly created agreement
        System.out.printf("Agreement parsed correctly: %s%n",newAgreement.toString().equals(agreementFromFile.toString()));
        System.out.println("******************");
        System.out.println();
        System.out.printf("\tnewAgreement.toString() content - created in JAVA%n%n%s%n",newAgreement);
        System.out.printf("\tagreementFromFile.toString() content - parsed from file%n%n%s%n",agreementFromFile);




    }
}
