package Workers;


import cartEntities.product;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CartWorker {
    public List<product> cartBuilder() {
        FileChooser fileChooser = new FileChooser();
        BufferedReader bufferedReader = null;
        List<product> cart = new ArrayList<>();
        try {
            bufferedReader = new BufferedReader(new FileReader(fileChooser.fileOpen()));
            String[] rawProduct;
            while ((rawProduct = bufferedReader.readLine().split(" ")) != null) {
                String taxID = "";
                product product = new product();
                product.setProductQuantity(Integer.parseInt(rawProduct[0]));
                product.setProductPrice(Double.parseDouble((rawProduct[rawProduct.length - 1])));
                rawProduct = Arrays.copyOfRange(rawProduct, 1, rawProduct.length - 2);
                product.setProductName(String.join(" ", rawProduct));
                setProductType(product);
                cart.add(product);
            }

        } catch (NullPointerException e) {
        } catch (FileNotFoundException e) {
            System.out.println("The file could not be found");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }
        salesTax(cart);
        return cart;
    }


    public List<product> salesTax(List<product> finalCart) {
        double importTax = .05;
        double salesTax = .1;

        for (product product : finalCart) {
            if (product.getTaxID().contains("I")) {
                product.setProductTax((product.getProductTax() + (product.getProductPrice() * importTax)));
            }

            product.setProductTax(Math.round(product.getProductTax() * 10) / 10.0);
            System.out.println(product.getProductTax());


            if (product.getTaxID().contains("T")) {
                product.setProductTax((product.getProductTax() + (product.getProductPrice() * salesTax)));
            }

            product.setProductTax(Math.round(product.getProductTax() * 10) / 10.0);

            product.setProductFinalSale(product.getProductTax() + product.getProductPrice());
        }
        return finalCart;
    }


    public void setProductType(product prod) {
        String[] noTax = {"food", "chocolate", "pills", "headache", "book", "books", "chocolates"};
        String taxID = "";
        boolean isTaxable = true;
        boolean isImported = prod.getProductName().toLowerCase().contains("imported");
        if (isImported) taxID += "I";
        for (String i : noTax) {
            if (prod.getProductName().toLowerCase().contains(i)) {
                isTaxable = false;
            }
        }
        if (isTaxable) taxID += "T";

        prod.setTaxID(taxID);
    }


}


