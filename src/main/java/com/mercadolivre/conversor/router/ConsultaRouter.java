package com.mercadolivre.conversor.router;

import com.mercadolivre.conversor.core.entity.Consulta;
import com.mercadolivre.conversor.core.usecase.impl.ConsultasUseCaseImpl;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consulta")
public class ConsultaRouter {

    @Autowired
    ConsultasUseCaseImpl consultasUseCase;


    @GetMapping("/all")
    public ResponseEntity<List<Consulta>> getAllConsultas() {

        List<Consulta> response = consultasUseCase.getAllConsultas();
        if (response.isEmpty()) {
            return new ResponseEntity<List<Consulta>>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<Consulta>>(response, HttpStatus.OK);
        }
    }

    @SneakyThrows
    @PostMapping("/{from}/{to}")
    public ResponseEntity<Consulta> realizarConsulta(@PathVariable String from,
                                                     @PathVariable String to,
                                                     @RequestParam Double valor) {
        Consulta response = consultasUseCase.createExchangeTransaction(from, to, valor);
try {
    return new ResponseEntity<Consulta>(response, HttpStatus.OK);
}catch (Exception e){
    return new ResponseEntity<Consulta>(HttpStatus.BAD_REQUEST);
}

    }


}
