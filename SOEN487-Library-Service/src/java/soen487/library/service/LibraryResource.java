/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soen487.library.service;

import java.util.ArrayList;
import soen487.library.system.Library;
import soen487.library.core.Book;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
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
import javax.ws.rs.core.Response;
/**
 *
 * @author Xavier Vani-Charron, Ryan Taylor
 */
@Path("books")
public class LibraryResource {
    
    @GET
    @Path("/list")
    @Produces(MediaType.TEXT_PLAIN)
    public String listAllBooks(){
        
        String output = "";
        
        Library lib = Library.getInstance();
        
        ArrayList<Book> allBooks = lib.read(null);
        
        if(allBooks.isEmpty()){
            output = "No books in the Library System.";
        }else{
            for (Book book : allBooks){
                output += book.toString();
            }
        }
        
        return output;
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getBook(@PathParam("id") String id){
        
        String result = null;
        
        Library lib = Library.getInstance();
        
        ArrayList<Book> singleBook = lib.read(Integer.parseInt(id));
        
        if(singleBook.isEmpty()){
            result = "No book with the id: "+id;
        }else{
            result = singleBook.get(0).toString();
        }
        
        return result;
    }
    
    @POST
    @Path("/create")
//    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void post(@FormParam("title") String title, @FormParam("description") String description, @FormParam("isbn") String isbn, @FormParam("author") String author, @FormParam("publisher") String publisher){
        
        Library lib = Library.getInstance();
        
        lib.create(description, title, isbn, author, publisher);
    }
    
    @PUT
    @Path("/edit/{id}")
    public void editBook(){
        
    }
    
    @DELETE
    @Path("delete/{id")
    public void deleteBook(){
        
    }
    
}
