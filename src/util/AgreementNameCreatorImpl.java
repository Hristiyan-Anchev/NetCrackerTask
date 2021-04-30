package util;

import util.interfaces.AgreementNameCreator;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class AgreementNameCreatorImpl implements AgreementNameCreator {

    public AgreementNameCreatorImpl() {
    }

    @Override
    public String createName() {
        var name = String.format(
                "Agreement %s",
//                LocalDate.now().format(DateTimeFormatter.ofPattern("dd_MM_yyyy"))
                LocalDate.ofYearDay(1994,200)
                );


        return name;
    }
}
