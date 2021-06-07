package TCP;


import domain.Client;
import message.Message;
import service.ClientServiceInterface;
import utils.Adapter;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public class ClientMessageHandler {
    ClientServiceInterface clientService;

    public ClientMessageHandler(ClientServiceInterface clientService){
        this.clientService = clientService;
    }

    /**
     * Takes the request message from client and tries to add
     * the new client using the addClient method from the server service
     * @param request Message from the client
     * @return Message from the server as a reply
     */
    public Message addClient(Message request){
        Client receivedClient = Adapter.messageToClient(request.getBody());
        try {
            clientService.addClient(receivedClient);
            return new Message("ok",Adapter.clientToOIString(receivedClient));
        }
        catch (Exception e){
            return new Message("error", e.getCause().getMessage());
        }
    }

    public Message deleteClient(Message request){
        Long clientId = Long.valueOf(request.getBody());
        try {
            Optional<Client> opt =  clientService.delete(clientId).get();
            return opt.map(client -> new Message("ok", Adapter.clientToOIString(client)))
                    .orElseGet(() -> new Message("error", "Client does not exist"));
        }
        catch (Exception e) {
            // System.out.println(e.getCause().getMessage());
            return new Message("error", e.getCause().getMessage());
        }
    }

    public Message updateClient(Message request){
        Client receivedClient = Adapter.messageToClient(request.getBody());
        try{
            clientService.update(receivedClient);
            return new Message("ok",Adapter.clientToOIString(receivedClient));
        }
        catch (Exception e){
            return new Message("error", e.getCause().getMessage());
        }
    }

    public Message getAllClients(Message request){
        try {
            Set<Client> result =  clientService.getAllClients().get();
            String messageBody = result.stream()
                    .map(Adapter::clientToOIString)
                    .reduce("", (substring,element) -> substring + element + System.lineSeparator());
            return new Message("ok", messageBody);
        }
        catch (InterruptedException | ExecutionException e) {
            // System.out.println(e.getCause().getMessage());
            return new Message("error", e.getCause().getMessage());
        }
    }

}
