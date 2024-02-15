package net.javaguides.springboot.monitors;

import java.io.FileWriter;

public class Monitor {


    private FileWriter monitor;

    private int stare;
    public Monitor() {
        stare = 0;
    }

    public void deletedItem(int id, float amount) {

        try {

            FileWriter monitor = new FileWriter("logs.txt", true);
            String aux = new String();
            aux = amount + " iterations of the item " + id + " were deleted from a cart\n";
            monitor.write(aux);
            monitor.close();
        }
        catch(Exception e) {}

    }

    public void addItemSuccess(int id) {

        try {
            FileWriter monitor = new FileWriter("logs.txt", true);
            String aux = new String();
            aux = "The item " + id + " was added to a cart\n";
            monitor.write(aux);
            monitor.close();
        }
        catch(Exception e) {}
    }

    public void addItemFail(int id) {

        try {
            FileWriter monitor = new FileWriter("logs.txt", true);
            String aux = new String();
            aux = "A client tried to add the item " + id + " but it was out of stock\n";
            monitor.write(aux);
            monitor.close();

        }
        catch(Exception e) {}
    }

    public void adminDeleteItem(int id) {

        try {
        FileWriter monitor = new FileWriter("logs.txt", true);
        String aux = new String();

        aux = "An admin has deleted the item with ID: " + id + "\n";
        monitor.write(aux);
        monitor.close();
        }
        catch (Exception e){}
    }

    public void adminAdd()
    {
        try {
            FileWriter monitor = new FileWriter("logs.txt", true);
            String aux = new String();

            aux = "A new flower has been added \n";
            monitor.write(aux);
            monitor.close();
        }
        catch (Exception e) {}
    }

    public void adminLogin()
    {
        try {
            FileWriter monitor = new FileWriter("logs.txt", true);
            String aux = new String();

            if(this.stare == 1)
            {
                aux = "An admin has just logged out\nAn admin has just logged in \n";
            }
            else if(this.stare == 2)
            {
                aux = "A client has just logged out\nAn admin has just logged in \n";
            }
            else
                aux = "An admin has just logged in \n";

            this.stare = 1;
            //aux = "An admin has just logged in \n";
            monitor.write(aux);
            monitor.close();
        }
        catch (Exception e) {}
    }

    public void clientLogin()
    {
        try {
            FileWriter monitor = new FileWriter("logs.txt", true);
            String aux = new String();

            if(this.stare == 1)
            {
                aux = "An admin has just logged out\nA client has just logged in \n";
            }
            else if(this.stare == 2)
            {
                aux = "A client has just logged out\nA client has just logged in \n";
            }
            else
                aux = "A client has just logged in \n";


            this.stare = 2;
            //aux = "A client has just logged in \n";
            monitor.write(aux);
            monitor.close();
        }
        catch (Exception e) {}
    }


}
