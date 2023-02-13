package arquitectura.software.ExchangeJava.bl;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import okhttp3.*;
import java.io.*;

import arquitectura.software.ExchangeJava.dto.RequestDto;
import arquitectura.software.ExchangeJava.dto.ErrorDto;

import com.fasterxml.jackson.databind.ObjectMapper;



@Service
public class ChangeBl {

    public ChangeBl() {
    }

    Logger logger = LoggerFactory.getLogger(ChangeBl.class);
        
    @Value("${api.url}")
    private String apiUrl;

    @Value("${api.key}")
    private String apiKey;

    

    
    public ResponseEntity<String> getExchange(String from, String to, BigDecimal amount){
        validateValues(from, to, amount);
        Response response = requestApi(from, to, amount);
        RequestDto requestDto = parseResponse(response, RequestDto.class);
        return new ResponseEntity<String>(requestDto.toString(), HttpStatus.OK);
    }

    private void validateValues(String from, String to, BigDecimal amount){
        if(from == null || from.isEmpty()){
            logger.error("Ocurrio un error: El parametro from es requerido");
            throw new IllegalArgumentException("El parametro from es requerido");
        }
        if(to == null || to.isEmpty()){
            logger.error("Ocurrio un error: El parametro to es requerido");
            throw new IllegalArgumentException("El parametro to es requerido");
        }
        if(amount == null){
            logger.error("Ocurrio un error: El parametro amount es requerido");
            throw new IllegalArgumentException("El parametro amount es requerido");
        }
        if(amount.compareTo(BigDecimal.ZERO) < 0 || amount.compareTo(BigDecimal.ZERO) == 0){
            logger.error("Ocurrio un error: El monto debe ser mayor a 0");
            throw new IllegalArgumentException("El monto debe ser mayor a 0");
        }
    }


    public Response requestApi(String from, String to, BigDecimal amount){
        OkHttpClient client = new OkHttpClient();
        Response response = null;
        Request request = new Request.Builder()
            .url(apiUrl + "to="+to+"&from="+from+"&amount="+amount)
            .addHeader("apikey", apiKey)
            .build();
        try{
            response = client.newCall(request).execute();
            if(response.code() != 200){
                ErrorDto error = parseResponse(response, ErrorDto.class);
                logger.error("Ocurrio un error: " + error.getMessage());
                throw new RuntimeException("Ocurrio un error: " + error.getMessage());
            }
            return response;
        }catch(IOException e){
            logger.error("Ocurrio un error: " + e.getMessage());
            throw new RuntimeException("Ocurrio un error: " + e.getMessage());
        }
    }

    public <T> T parseResponse(Response response, Class<T> valueType){
        try{
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(response.body().string(), valueType);
        }catch(IOException e){
            logger.error("Ocurrio un error: " + e.getMessage());
            throw new RuntimeException("Ocurrio un error: " + e.getMessage());
        }
    }

}
