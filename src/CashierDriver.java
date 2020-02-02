/*Created by Jorge A Avalos on 2/1/2020
Assigned by: LifeRay, Inc. Interviewer: Angelo Jefferson
Description of Program:
User inputs a text file so that both sales tax and import tax can be applied where applicable.
 */

import cartEntities.product;
import workers.cartWorker;
import workers.fileChooser;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

public class CashierDriver {
    /*
    Main; Program is initialized here.
     */
    public static void main(String[] args) throws IOException {
        displayReceipt(0);

    }

    /*
    displayReceipt is the method that displays the receipt of the products when an input file is selected
    A gui is used for convenience to select the file and display the receipt.
     */
    public static void displayReceipt(int restart) throws IOException {
        DecimalFormat decimalFormat = new DecimalFormat(".00");
        String dialogueBox = "";
        double totalTax = 0;
        double totalSale = 0;
        int mainSelection = 2;
        if (restart == 0) {
            mainSelection = JOptionPane.showOptionDialog(null, "Please select your Input File", "Receipt Maker", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"OK", "Exit"}, "Ok");
        }
        if (restart == 1) {
            mainSelection = 0;
        }
        if (mainSelection == 0) {
            cartWorker checkoutCart = new cartWorker();
            List<product> finalCart = checkoutCart.cartBuilder();
            for (product product : finalCart) {
                dialogueBox += (product.getProductQuantity() + " " + product.getProductName() + ": " + decimalFormat.format(product.getProductFinalSale()) + "\n");
                totalTax += product.getProductTax();
                totalSale += product.getProductFinalSale();
            }
            dialogueBox += "Total Tax: $" + decimalFormat.format(totalTax) + "\n";
            dialogueBox += "Total Sale: $" + decimalFormat.format(totalSale);
            int writeOption = JOptionPane.showOptionDialog(null, dialogueBox, "Paperless Receipt", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Restart", "Save"}, "Restart");
            if (writeOption == 0) {
                displayReceipt(1);
            }
            if (writeOption == 1) {
                writeReceipt(finalCart, totalTax, totalSale);

            }
        }
        if (mainSelection == 1) {
            System.exit(0);
        }
    }


    /*
    writeReceipt will write the the receipt to a text file when a user selects both a file destination and a file name.
     */
    public static List<product> writeReceipt(List<product> prod, double totalTax, double totalSale) throws IOException {
        String bufferedString = "";
        BufferedWriter bufferedWriter = null;
        DecimalFormat decimalFormat = new DecimalFormat(".00");
        fileChooser fileChooser = new fileChooser();
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


