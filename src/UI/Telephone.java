package UI;
import java.util.Scanner;

import Domain.Connection;

public class Telephone {

   private Scanner scanner;
	   
   public Telephone(Scanner aScanner) {
      scanner = aScanner;
   }

   public void speak(String output) {
      System.out.println(output);
   }

   public void run(Connection c) {
      boolean more = true;
      while (more) {
         String input = scanner.nextLine();
         if (input == null) return;
         if (input.equalsIgnoreCase("H"))
            c.hangUp();
         else if (input.equalsIgnoreCase("Q"))
            System.exit(0);
         else if (input.length() == 1 && "1234567890#".indexOf(input) >= 0)
            c.dial(input);
         else
            c.record(input);
      }
   }

}
