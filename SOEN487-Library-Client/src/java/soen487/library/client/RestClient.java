/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soen487.library.client;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:LibraryResource [books]<br>
 * USAGE:
 * <pre>
 *        RestClient client = new RestClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author jonathan
 */
public class RestClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/SOEN487-Library-Service/webservice";

    public RestClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("books");
    }

    public String deleteBook(String id) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("delete/{0}", new Object[]{id})).request().delete(String.class);
    }

    public String getBook(String id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{id}));
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public String post() throws ClientErrorException {
        return webTarget.path("create").request(javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED).post(null, String.class);
    }

    public String editBook(String id) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("edit/{0}", new Object[]{id})).request().put(null, String.class);
    }

    public String listAllBooks() throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("list");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public void close() {
        client.close();
    }
    
}
