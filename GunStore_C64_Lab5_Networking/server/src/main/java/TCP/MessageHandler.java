package TCP;

import domain.Gun;
import domain.exceptions.SocketException;
import message.Message;
import service.GunServiceInterface;
import utils.Adapter;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public class MessageHandler {
    GunServiceInterface gunService;

    public MessageHandler(GunServiceInterface gunService){
        this.gunService = gunService;
    }

    /**
     * Takes the request message from client and tries to add
     * the new gun using the addGun method from the server service
     * @param request Message from the client
     * @return Message from the server as a reply
     */
    public Message addGun(Message request){
        Gun receivedGun = Adapter.messageToGun(request.getBody());
        try {
            Optional<Gun> opt = gunService.addGun(receivedGun).get();
            return opt.map(gun -> new Message("ok", Adapter.gunToOIString(gun)))
                    .orElseGet(() -> new Message("error", "Gun could not be added"));
        }
        catch (Exception e){
            return new Message("error", e.getCause().getMessage());
        }
    }

    public Message deleteGun(Message request){
        Long gunId = Long.valueOf(request.getBody());
        try {
            Optional<Gun> opt =  gunService.delete(gunId).get();
            return opt.map(gun -> new Message("ok", Adapter.gunToOIString(gun)))
                    .orElseGet(() -> new Message("error", "Gun does not exist"));
        }
        catch (Exception e) {
            // System.out.println(e.getCause().getMessage());
            return new Message("error", e.getCause().getMessage());
        }
    }

    public Message updateGun(Message request){
        Gun receivedGun = Adapter.messageToGun(request.getBody());
        try{
            gunService.update(receivedGun);
            return new Message("ok",Adapter.gunToOIString(receivedGun));
        }
        catch (Exception e){
            return new Message("error", e.getCause().getMessage());
        }
    }

    public Message getAllGuns(Message request){
        try {
            if(request.getBody().length() > 0)
                throw new SocketException("Bad request");
            Set<Gun> result =  gunService.getAllGuns().get();
            String messageBody = result.stream()
                    .map(Adapter::gunToOIString)
                    .reduce("", (substring,element) -> substring + element + System.lineSeparator());
            return new Message("ok", messageBody);
        }
        catch (InterruptedException | ExecutionException | SocketException e) {
            // System.out.println(e.getCause().getMessage());
            return new Message("error", e.getCause().getMessage());
        }
    }

    public Message filterGunsModel(Message request){
        String model = request.getBody();
        try{
            Set<Gun> result = gunService.filterGunsByModel(model).get();
            String messageBody = result.stream()
                    .map(Adapter::gunToOIString)
                    .reduce("", (substring,element) -> substring + element + System.lineSeparator());
            return new Message("ok", messageBody);

        } catch (InterruptedException | ExecutionException e) {
            return new Message("error", e.getCause().getMessage());
        }
    }

    public Message filterGunsType(Message request){
        String type = request.getBody();
        try{
            Set<Gun> result = gunService.filterGunsByType(type).get();
            String messageBody = result.stream()
                    .map(Adapter::gunToOIString)
                    .reduce("", (substring,element) -> substring + element + System.lineSeparator());
            return new Message("ok", messageBody);

        } catch (InterruptedException | ExecutionException e) {
            return new Message("error", e.getCause().getMessage());
        }
    }

    public Message getTop3Guns(Message request){
        try {
            if(request.getBody().length() > 0)
                throw new SocketException("Bad request");
            List<Gun> result =  gunService.getTop3SoldGuns().get();
            String messageBody = result.stream()
                    .map(Adapter::gunToOIString)
                    .reduce("", (substring,element) -> substring + element + System.lineSeparator());
            return new Message("ok", messageBody);
        }
        catch (InterruptedException | ExecutionException | SocketException e) {
            return new Message("error", e.getCause().getMessage());
        }
    }

    public Message sortGunsPrice(Message request){
        try {
            if(request.getBody().length() > 0)
                throw new SocketException("Bad request");
            List<Gun> result =  gunService.getSortedGuns().get();
            String messageBody = result.stream()
                    .map(Adapter::gunToOIString)
                    .reduce("", (substring,element) -> substring + element + System.lineSeparator());
            return new Message("ok", messageBody);
        }
        catch (InterruptedException | ExecutionException | SocketException e) {
            return new Message("error", e.getCause().getMessage());
        }
    }
}
