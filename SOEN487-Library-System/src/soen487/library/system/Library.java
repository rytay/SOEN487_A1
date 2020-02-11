/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soen487.library.system;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import soen487.library.core.Book;

/**
 *
 * @author Xavier Vani-Charron, Ryan Taylor
 */

//TODO: Should we accept Book objects as arguments or the parameters themselves?
public class Library {
    private static Library library_instance = null;
    private static ConcurrentHashMap bookList = null;
    private static final AtomicInteger sequence = new AtomicInteger(0);

    private Library(){
        bookList = new ConcurrentHashMap<Integer,String>();
    }
    
    public static Library getInstance(){
        if(library_instance == null){
            library_instance = new Library();
        }
        return library_instance;
    }
    
    //Probably dont need this considering two ids may be the same depending on what the last id was for the paramter hashmap
    public Library(ConcurrentHashMap bookList) {
        this.bookList = bookList;
    }
    
    public void create(String title, String description, String isbn, String author, String publisher){
        
        Book newBook = new Book(sequence.incrementAndGet() ,title, description, isbn, author, publisher);
	bookList.put(newBook.getId(), newBook);
        
    }
    
    //Will be null if id not present in bookList, otherwise will return the book but it must be cast as book when returned.
    public Book delete(Integer id){
	return (Book)bookList.remove(id);
    }
    
    //TODO:Should we add a book if not in the hashmap? This implementation does not. 
    public void update(Book updatedBook){
	
	int id = updatedBook.getId();
	if(bookList.containsKey(id))
	    bookList.put(id, updatedBook);
	//Uncomment for creating a new book if it does not exist
	/*else{
	    this.create(updatedBook)
	}
	*/
	
    }
    
    //If id is null return all the books, otherwise return matching book or empty ArrayList if not present
    public ArrayList<Book> read(Integer id){
        
	ArrayList<Book> result = new ArrayList<>();
	
	if(id == null){
	    result.addAll(bookList.values());
	}
	else{
	    if(bookList.containsKey(id))
		result.add((Book)bookList.get(id));
	}
	return result;
    }
}
