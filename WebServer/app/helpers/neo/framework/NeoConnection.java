package helpers.neo.framework;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * 
 * @author arjrao
 *
 */
public class NeoConnection {

    private static final String SERVER_ROOT_URI = "http://localhost:7474/db/data/";

    public NeoConnection(){}
    
    public boolean checkDatabaseIsRunning(){
        final WebResource resource = Client.create().resource( SERVER_ROOT_URI );
        final ClientResponse response = resource.get( ClientResponse.class );
        int status = response.getStatus();
        response.close();
        return status == 0 ? false : true;
    }
    
    public String getServerURI(){
    	return SERVER_ROOT_URI;
    }
}
