package domain;

import java.util.List;
import java.util.Objects;

public abstract class BaseProduct {
    public BaseProduct(){}

    public BaseProduct(List<BaseProduct> childProducts){
        this.setChildProducts(childProducts);
    }

    public BaseProduct(String name,List<BaseProduct> childProducts) {
       this.setChildProducts(childProducts);
       this.setName(name);
    }

    //state
    private List<BaseProduct> childProducts;
    private String name;

    public BaseProduct addChild(BaseProduct newProduct){
        this.childProducts.add(newProduct);
        return newProduct;
    }

    public abstract Boolean hasParent();

    public abstract Boolean isAgreement();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BaseProduct> getChildProducts() {
        return childProducts;
    }

    public void setChildProducts(List<BaseProduct> childProducts) {

        this.childProducts = childProducts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseProduct)) return false;
        BaseProduct that = (BaseProduct) o;
        return getChildProducts().equals(that.getChildProducts()) && getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getChildProducts(), getName());
    }
}
