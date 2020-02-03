/*
Created By: Jorge Avalos
Date: 2/1/2020
Description of Class:
This class created the so called "grocery cart" when a user first inputs a text file.

 */
package workers;

import cartEntities.product;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class cartWorker {

    /*
    cartBuilder method will create the "grocery cart" when the user inputs a file and parse the
    text file for the data that is needed. (e.g Quantity, Name, and Price)
    */
    public List<product> cartBuilder() {
        fileChooser fileChooser = new fileChooser();
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
        } catch (FileNotFoundException e) {
            System.out.println("The file could not be found");
            cartBuilder();
        } catch (IOException | NullPointerException e) {
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                }

            }

        }
        salesTax(cart);
        return cart;
    }

    /*
    salesTax method will scan the list of objects and add a tax amount for the product that is
    determined by the taxID that is added in the setProductType method of the class.
     */
    public List<product> salesTax(List<product> finalCart) {
        double importTax = .05;
        double salesTax = .1;
        for (product product : finalCart) {
            if (product.getTaxID().contains("I")) {
                product.setProductTax((product.getProductTax() + (product.getProductPrice() * importTax)));
            }
            product.setProductTax(Math.round(product.getProductTax() * 10) / 10.0);
            if (product.getTaxID().contains("T")) {
                product.setProductTax((product.getProductTax() + (product.getProductPrice() * salesTax)));
            }
            product.setProductTax(Math.round(product.getProductTax() * 10) / 10.0);
            product.setProductFinalSale(product.getProductTax() + product.getProductPrice());
        }
        return finalCart;
    }

    /*
    setProductType will add a taxID to the product if it is applicable,  it uses a small array of known keywords
    for products that are non-taxable.  The product name is scanned to see if any matches are found.
    If the term "imported" is found in the products name, the import tax is added.
     */
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


