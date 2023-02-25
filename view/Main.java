package view;

import controller.RedesController;

import javax.swing.*;

public class Main
{
    public static void main(String[] args)
    {
        RedesController cont = new RedesController();
        boolean end = false;

        do
        {
            String answer = menuOption();

            try
            {
                int option = Integer.parseInt(answer);

                switch (option)
                {
                    case 1 -> cont.ip();
                    case 2 -> cont.ping();
                    case 3 -> {
                        finalMessage();
                        end = true;
                    }
                    default -> errorMessage();
                }
            }
            catch (Exception e)
            {
                errorMessage();
                System.err.println(e.getMessage());
                end = true;
            }

        } while (!end);
    }


    static String menuOption()
    {
        String menu;
        String msg = "1 - Show Ipv4\n2 - Show Ping\n3 - Exit";
        menu = JOptionPane.showInputDialog(null, msg, "Activity 01",
                JOptionPane.PLAIN_MESSAGE);
        return (menu);
    }


    static void errorMessage()
    {
        JOptionPane.showMessageDialog(null, "Invalid Option",
                "ERROR", JOptionPane.ERROR_MESSAGE);
    }


    static void finalMessage()
    {
        JOptionPane.showMessageDialog(null, "Program Finished",
                "End", JOptionPane.PLAIN_MESSAGE);
    }
}
