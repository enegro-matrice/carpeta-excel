
package com.carpetaciudadana.app.domain;

import java.io.IOException;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;


public class Documento implements Serializable{

    
    /**
     *
     */
    
    private static final long serialVersionUID = 1L;
    public Informacion informacion;

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = As.PROPERTY, property = "type")
    @JsonSubTypes({ 
    @Type(value = EstudianteCedel.class, name = "CERTIFICADO_CEDEL"),
   // @Type(value = Ap.class, name = "CERTIFICADO_AP"),
    @Type(value = Ccd.class, name = "CERTIFICADO_CMD"),
    //@Type(value = TituloSecundarioDTO.class, name = "TITULO_SECUNDARIO")
    })
    
    public static class Informacion {



        @JsonIgnore
        public boolean getAliniacion(){
            return true;
        };

        public String llamar(String path) throws JsonParseException, JsonMappingException, IOException{
            return null;
        } 
        

    }
    
    public Informacion getInformacion() {
        return informacion;
    }

    


}