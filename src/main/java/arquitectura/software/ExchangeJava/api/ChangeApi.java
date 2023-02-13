package arquitectura.software.ExchangeJava.api;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import arquitectura.software.ExchangeJava.bl.ChangeBl;


@ControllerAdvice
class MyException {
    
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        ResponseEntity<String> response = new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        return response;
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        ResponseEntity<String> response = new ResponseEntity<String>("Ocurrio un error con el servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        return response;
    }
}

@RestController
@RequestMapping("/api/v1/exchange")
public class ChangeApi {
    Logger logger = LoggerFactory.getLogger(ChangeApi.class);
    private ChangeBl changeBl;

    public ChangeApi(ChangeBl changeBl) {
        this.changeBl = changeBl;
    }

    @GetMapping("/get")
    public ResponseEntity<String> getExchange(@RequestParam String from, @RequestParam String to, @RequestParam BigDecimal amount){
        logger.info("Request to REST: from: " + from + " to: " + to + " amount: " + amount);
        return changeBl.getExchange(from, to, amount);
    }
    
}
