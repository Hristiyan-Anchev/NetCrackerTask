package util.interfaces;

import domain.Agreement;

public interface AgreementParser {
    Agreement parseAgreement(String content);

}
