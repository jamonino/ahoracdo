
package com.albares.game.api;

import com.albares.game.utils.Parameters;
import com.albares.game.utils.JWTUtils;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/user")
public class UserService {
    
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String createUser(User newUser){
        if(newUser.getScore()!=0 ){
            newUser.setScore(-Math.abs(newUser.getScore()));
            return "Hackeo detectado... tu token es: "+JWTUtils.generateToken(Parameters.match.addUser(newUser));
        }
        return JWTUtils.generateToken(Parameters.match.addUser(newUser));
    }    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List getUsers(){ 
        return new ArrayList<>(Parameters.match.getUsers().values());
    }    
}







