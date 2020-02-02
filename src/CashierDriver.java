import Workers.CartWorker;
import Workers.FileChooser;
import cartEntities.product;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

public class CashierDriver {
    public static void main(String[] args) throws IOException {
        displayReceipt();
    }

    public static void displayReceipt() throws IOException {
        DecimalFormat decimalFormat = new DecimalFormat(".00");
        String dialogueBox = "";
        double totalTax = 0;
        double totalSale = 0;
        CartWorker checkoutCart = new CartWorker();
        List<product> finalCart = checkoutCart.cartBuilder();
        for (product product : finalCart) {
            dialogueBox += (product.getProductQuantity() + " " + product.getProductName() + ": " + decimalFormat.format(product.getProductFinalSale()) + "\n");
            totalTax += product.getProductTax();
            totalSale += product.getProductFinalSale();
        }
        dialogueBox += "Total Tax: $" + decimalFormat.format(totalTax) + "\n";
        dialogueBox += "Total Sale: $" + decimalFormat.format(totalSale);
        int writeOption = JOptionPane.showOptionDialog(null, dialogueBox, "Paperless Receipt", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Restart", "Save"}, "Close");
        if (writeOption == 0) {
            displayReceipt();

        }
        if (writeOption == 1) {
            writeReceipt(finalCart, totalTax, totalSale);

        }
    }

    public static List<product> writeReceipt(List<product> prod, double totalTax, double totalSale) throws IOException {
        String bufferedString = "";
        BufferedWriter bufferedWriter = null;
        DecimalFormat decimalFormat = new DecimalFormat(".00");
        FileChooser fileChooser = new FileChooser();
        File file = new File(fileChooser.fileSave() + ".txt");
        file.createNewFile();
        FileWriter fileWriter = new FileWriter(file);
        bufferedWriter = new BufferedWriter(fileWriter);
        for (product product : prod) {
            bufferedString += ((product.getProductQuantity() + " " + product.getProductName() + ": " + decimalFormat.format(product.getProductFinalSale()) + "\n"));
        }
        bufferedString += ("Total Tax: $" + decimalFormat.format(totalTax) + "\n");
        bufferedString += ("Total Sale: $" + decimalFormat.format(totalSale));
        bufferedWriter.write(bufferedString);
        bufferedWriter.close();
        return null;
    }


}


