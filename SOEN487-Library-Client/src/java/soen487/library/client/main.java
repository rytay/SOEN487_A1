/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soen487.library.client;

import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jonathan
 */
public class main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner kb = new Scanner(System.in);
        int quit = 0;
        System.out.println("[1] Help / about");
        System.out.println("[2] List all books");
        System.out.println("[3] Display a book");
        System.out.println("[4] Add a book");
        System.out.println("[5] Update a book");
        System.out.println("[6] Delete a book");
        System.out.println("[7] Quit");
        
        RestClient client = new RestClient();
        
        
        while(quit != 1){
          int select = kb.nextInt();
          switch(select) {
            case 1:
              
              System.out.println("HELP CONTENT");
              break;
            case 2:
              
              System.out.println("input was 2");
              Object printAll = client.listAllBooks();
              System.out.println(printAll);
              break;
            case 3:
              System.out.println("input was 3");
              System.out.println("Enter book ID Number");
              String select2 = kb.next();
              System.out.println("id number is : " + select2);
              Object printBook = client.getBook(select2);
              System.out.println(printBook);
              break;
            case 4:
              System.out.println("input was 4");
              System.out.println("Enter book title");
              String title = kb.next();
              System.out.println("Enter book description");
              String desc = kb.next();
              System.out.println("Enter book isbn");
              String isbn = kb.next();
              System.out.println("Enter book author");
              String author = kb.next();
              System.out.println("Enter book publisher");
              String publ = kb.next();

              Object addBook = client.post(title, desc, isbn, author, publ);
              System.out.println("Result : " + addBook);
              break;
            case 5:
              System.out.println("input was 5");
              System.out.println("Enter book ID");
              String bookID = kb.next();
              
              System.out.println("Enter new book title");
              String newtitle = kb.next();
              System.out.println("Enter new book description");
              String newdesc = kb.next();
              System.out.println("Enter new book isbn");
              String newisbn = kb.next();
              System.out.println("Enter new book author");
              String newauthor = kb.next();
              System.out.println("Enter new book publisher");
              String newpubl = kb.next();
              
              Object updateBook = client.editBook(bookID, newtitle, newdesc, newisbn, newauthor, newpubl);
              System.out.println("Result : " + updateBook);
              break;
            case 6:
              System.out.println("input was 6");
              System.out.println("Enter book ID");
              String select5 = kb.next();
              Object deleteBook = client.deleteBook(select5);
              System.out.println(deleteBook);
              break;
            default:
              System.out.println("User quit");
              quit = 1;
              client.close();
              break;
          }  
        }
        
        
    }
}

