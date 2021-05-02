package util;

import domain.Agreement;
import domain.BaseProduct;
import domain.Product;
import util.interfaces.AgreementParser;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class AgreementParserImpl implements AgreementParser {

    @Override
    public Agreement parseAgreement(String content) {
        String[] agreementProductsSplit = content.split("Products");
        String agreementNameSignedBy[] = agreementProductsSplit[0].split(":");
        String agrementName = agreementNameSignedBy[0].trim();
        String signedBy = agreementNameSignedBy[1].split("-")[1].trim();

        String allProductsContent = agreementProductsSplit[1];
        String[] mainProductsAndSubproducts = allProductsContent.split("####");
        List<BaseProduct> agreementProducts = new ArrayList<>();

        for (int i = 0; i < mainProductsAndSubproducts.length; i++) {
            String[] currentProductContent = Arrays.stream(mainProductsAndSubproducts[i].split("\\s+")).filter(x -> !x.trim().isEmpty())
                    .toArray(String[]::new);

            Product mainProduct = getCurrentProductContent(currentProductContent,0);

            var childrenCount = mainProduct.getChildProducts().size();
            mainProduct.setChildProducts(new ArrayList<BaseProduct>());

            mainProduct = parseCurrentProdContent(currentProductContent,mainProduct,6,childrenCount);

           agreementProducts.add(mainProduct);


        }

        Agreement parsedAgreement = new Agreement();
        parsedAgreement.setName(agrementName);
        parsedAgreement.setSignedBy(signedBy);
        parsedAgreement.setChildProducts(agreementProducts);


        return parsedAgreement;
        }




    private Product parseCurrentProdContent(String[] currentProductContent, Product accumulatorProduct, Integer currentProductContentIndex ,Integer childrenCount){
        if(currentProductContentIndex >= currentProductContent.length){
            return accumulatorProduct;
        }

        if(childrenCount > 0){
            Product currentlyParsedProduct = getCurrentProductContent(currentProductContent,currentProductContentIndex);
            var currentProdChildren = currentlyParsedProduct.getChildProducts().size();
            currentlyParsedProduct.setChildProducts(new ArrayList<BaseProduct>());
            accumulatorProduct.addChild(currentlyParsedProduct);
            if(currentProdChildren>0)
            parseCurrentProdContent(currentProductContent,currentlyParsedProduct,currentProductContentIndex + 6, currentProdChildren);

            childrenCount --;

        }

        return parseCurrentProdContent(currentProductContent,accumulatorProduct,currentProductContentIndex + 6, childrenCount);

    }

    private Product getCurrentProductContent(String[] currentProductContent, Integer currentIdx){
        String prodName = currentProductContent[currentIdx+1];
        String prodPrice = currentProductContent[currentIdx+3];
        Integer prodChildrenCount = Integer.parseInt(currentProductContent[currentIdx+5]);

        return new Product(prodName,new BigDecimal(prodPrice),Arrays.asList(new BaseProduct[prodChildrenCount]));
    }



}

