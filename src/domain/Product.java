package domain;

import java.math.BigDecimal;
import java.util.*;

public class Product extends BaseProduct{


    public Product(String name,BigDecimal price, List<BaseProduct> childProducts) {
        super(childProducts);
        super.setName(name);
        this.setPrice(price);
        this.parent = null;
    }

    //state
    private BaseProduct parent;
    private BigDecimal price;

    @Override
    public Boolean hasParent() {
        return this.parent != null;
    }

    @Override
    public Boolean isAgreement() {
        return false;
    }

    @Override
    public BaseProduct addChild(BaseProduct newProduct) {
        //if newProd passes then it is valid child Product to add to the current one
        if(!newProduct.isAgreement()){
            var newProdCast = (Product)newProduct;
            newProdCast.setParent(this);
            super.addChild(newProdCast);

            return newProduct;
        }

        return null;
    }

    public BaseProduct getParent() {
        return parent;
    }

    public void setParent(BaseProduct parent) {
        this.parent = parent;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return getParent().equals(product.getParent()) && getPrice().equals(product.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash( getPrice());
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();

        if(this.parent != null && !this.parent.isAgreement()){
            sb.append(String.format("Parent name: %s",this.parent.getName()));
            sb.append(System.lineSeparator());
        }

        sb.append(String.format("Product name: %s",super.getName()));
        sb.append(System.lineSeparator());

        sb.append("   Child products: ");
        sb.append(System.lineSeparator());
        super.getChildProducts().forEach(cp -> {
            sb.append(String.format("       %s",cp.getName()));
            sb.append(System.lineSeparator());
        });
        sb.append(System.lineSeparator());

        return sb.toString();
    }
}
