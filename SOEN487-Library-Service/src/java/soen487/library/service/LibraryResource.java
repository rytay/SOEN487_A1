/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soen487.library.service;

import java.util.ArrayList;
import soen487.library.system.Library;
import soen487.library.core.Book;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
/**
 *
 * @author Xavier Vani-Charron, Ryan Taylor
 */
@Path("books")
@Produces(MediaType.TEXT_PLAIN)
public class LibraryResource {
    
    
    @GET
    @Path("/list")
    public String listAllBooks(){
        
        String output = "";
        
        Library lib = Library.getInstance();
        
        ArrayList<Book> allBooks = lib.read(null);
        
        if(allBooks.isEmpty()){
            output = "No books in the Library System.";
        }else{
            for (Book book : allBooks){
                output += book.toString()+"\n";
            }
        }
        
        return output;
    }
    
    @GET
    @Path("/{id}")
    public String getBook(@PathParam("id") String id){
        
        int parsed_id;
        
        try{
            parsed_id = Integer.parseInt(id);
        }
        catch(NumberFormatException e){
            return "Please input an integer";
        }
        
        String result = null;
        
        Library lib = Library.getInstance();
        
        ArrayList<Book> singleBook = lib.read(parsed_id);
        
        if(singleBook.isEmpty()){
            result = "No book with the id: "+id;
        }else{
            result = singleBook.get(0).toString();
        }
        
        return result;
    }
    
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String post(@FormParam("title") String title, @FormParam("description") String description, @FormParam("isbn") String isbn, @FormParam("author") String author, @FormParam("publisher") String publisher){
        
        Library lib = Library.getInstance();
        Book newBook = lib.create(title,description, isbn, author, publisher);
	return "Successfully added Book :"+newBook.toString();
    }
    
    @PUT
    @Path("/edit/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String editBook(@PathParam("id") String id, @FormParam("title") String title, @FormParam("description") String description, @FormParam("isbn") String isbn, @FormParam("author") String author, @FormParam("publisher") String publisher){
        
        int parsed_id;
        
        try{
            parsed_id = Integer.parseInt(id);
        }
        catch(NumberFormatException e){
            return "Please input an integer";
        }
        
        Library lib = Library.getInstance();
	Book updatedBook = new Book(parsed_id , title, description, isbn, author, publisher);
	Book result = lib.update(updatedBook);
	if(result == null)
	    return "Cannot edit Book with id "+id+" because it does not exist";
	else
	    return "Successfully edited Book :" + result.toString();
	    
    }
    
    @DELETE
    @Path("delete/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteBook(@PathParam("id") String id){
        
        int parsed_id;
        
        try{
            parsed_id = Integer.parseInt(id);
        }
        catch(NumberFormatException e){
            return "Please input an integer";
        }
        
	Library lib = Library.getInstance();
	Book deleted = lib.delete(parsed_id);

	//If we want to have a return message
	if (deleted == null)
	    return "Book with id "+id+" does not exist.";
	else
	    return "Succesfully deleted book: "+deleted.toString();
        
    }
    
}
