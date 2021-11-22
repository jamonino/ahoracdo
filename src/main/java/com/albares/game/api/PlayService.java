
package com.albares.game.api;

import com.albares.game.utils.JWTUtils;
import com.albares.game.utils.Parameters;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/play")
public class PlayService {
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{token}/{word}")
    public String playMatch(@PathParam("token") String token,@PathParam("word") String word){
        Integer id = JWTUtils.checkJWTandGetUserId(token);
        
        String mask = "";
        //Si el token no es válido
        if(id == -1) return "token no valido";
        
        User player = Parameters.match.getUsers().get(id);
        
        if(id.equals(Parameters.match.getTurn())){
            
            if(word.length()>1){
                if(Parameters.match.getRandomWord().equals(word)){
                    player.increment(100);
                    mask = Parameters.match.getMask();
                    Parameters.match.generateNewMatch();
                    
                }else{
                    player.decrement(50);
                }
            }else{
                player.increment(Parameters.match.checkChar(word.charAt(0)) * 20);
                mask = Parameters.match.getMask();
            }
            
            Parameters.match.nextTurn();
        }else{
            
            player.decrement(100);
        }
        
        return mask;
        
    }    
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getTurn(){ 
        return String.valueOf(Parameters.match.getTurn());
    }    
}







