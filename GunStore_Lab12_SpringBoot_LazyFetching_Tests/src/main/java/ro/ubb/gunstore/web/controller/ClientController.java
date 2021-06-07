package ro.ubb.gunstore.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.gunstore.core.service.ClientServiceInterface;
import ro.ubb.gunstore.web.converter.ClientConverter;
import ro.ubb.gunstore.web.dto.ClientDto;

import java.util.List;


@RestController
@RequestMapping("/api")
public class ClientController {
    private static final Logger log = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientServiceInterface clientService;

    @Autowired
    private ClientConverter clientConverter;


    @RequestMapping(value = "/clients")
    List<ClientDto> getAllClients(){
        log.info("getAllClients - method entered");
        var result = clientConverter.convertModelsToDtos(clientService.getAllClients());
        log.info("getAllClients: result={}",result);
        return result;
    }

    @RequestMapping(value = "/clients", method = RequestMethod.POST)
    ClientDto addClient(@RequestBody ClientDto clientDto){
        log.info("addClient - method entered: clientDto={}", clientDto);
        var client = clientConverter.convertDtoToModel(clientDto);
        ClientDto result;
        try {
            var resultClient = clientService.addClient(client);
            result = clientConverter.convertModelToDto(resultClient);
        } catch (Exception e) {
            // e.printStackTrace();
            result = new ClientDto();
            result.setName(e.getMessage());
        }
        log.info("addClient: result={}",result);
        return result;
    }

    @RequestMapping(value = "/clients/{id}", method = RequestMethod.PUT)
    ClientDto updateClient(@PathVariable Long id, @RequestBody ClientDto dto){
        log.info("updateClient - method entered: id={} clientDto={}",id,dto);
        ClientDto result;
        try {
            result = clientConverter.convertModelToDto(
                    clientService.update(clientConverter.convertDtoToModel(dto))
            );
        } catch (Exception e) {
            // e.printStackTrace();
            result = new ClientDto();
            result.setName(e.getMessage());
        }
        log.info("updateClient: result={}",result);
        return result;
    }

    @RequestMapping(value = "/clients/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteClient(@PathVariable Long id) {
        log.info("deleteClient - method entered: clientID={}", id);
        var result = new ResponseEntity<>(HttpStatus.OK);
        try {
            var deleted = clientService.delete(id);
            log.debug("deleted client: {}", deleted);
        } catch (Exception e) {
            e.printStackTrace();
            result = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("deleteClient: result={}",result);
        return result;
    }
}
