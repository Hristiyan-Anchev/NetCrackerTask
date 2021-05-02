package domain;

import util.interfaces.AgreementNameCreator;

import java.util.List;
import java.util.Objects;

public class Agreement extends BaseProduct {


    //======================================================================================================================
//  deps
    private AgreementNameCreator agreementNameCreator;
    //======================================================================================================================
    private String signedBy;

    public Agreement(){
        super();
    }

    public Agreement(String signedBy, List<BaseProduct> childProducts,
                     AgreementNameCreator agreementNameCreator) {
        super(childProducts);
        this.agreementNameCreator = agreementNameCreator;
        this.signedBy = signedBy;
        this.setPredefinedName();
    }


    @Override
    public void setName(String name) {
        super.setName(name);

    }

    private void setPredefinedName(){
        super.setName(agreementNameCreator.createName());
    }

    @Override
    public BaseProduct addChild(BaseProduct newProduct) {

        if(!newProduct.hasParent() && !this.equals(newProduct) && !newProduct.isAgreement()){
            //if newProduct passes the check then it must be a valid Product to add
            var newProdCast = (Product)newProduct;
            newProdCast.setParent(this);
            super.addChild(newProdCast);
            return newProdCast;
        }

        //returns null indicating the product passed has not been added
        return null;
    }

    @Override
    public Boolean hasParent() {
        return false;
    }

    @Override
    public Boolean isAgreement() {
        return true;
    }

    public AgreementNameCreator getAgreementNameCreator() {
        return agreementNameCreator;
    }

    public void setAgreementNameCreator(AgreementNameCreator agreementNameCreator) {
        this.agreementNameCreator = agreementNameCreator;
    }

    public String getSignedBy() {
        return signedBy;
    }

    public void setSignedBy(String signedBy) {
        this.signedBy = signedBy;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Agreement)) return false;
        Agreement agreement = (Agreement) o;
        return getAgreementNameCreator().equals(agreement.getAgreementNameCreator()) && getSignedBy().equals(agreement.getSignedBy());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAgreementNameCreator(), getSignedBy());
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();

        sb.append(String.format("%s : Signed by - %s",super.getName(),this.signedBy));
        sb.append(System.lineSeparator());
        sb.append("Products ");
        sb.append(System.lineSeparator());


        for(BaseProduct cp : super.getChildProducts()){
            sb.append(String.format("%s",((Product)cp).toString()));
            sb.append("####");
            sb.append(System.lineSeparator());

        }

        return sb.toString();
    }
}
