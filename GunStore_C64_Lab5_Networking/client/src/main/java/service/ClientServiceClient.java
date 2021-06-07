package service;

import TCP.TcpClient;
import domain.Client;
import domain.Gun;
import domain.exceptions.SocketException;
import message.Message;
import utils.Adapter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

public class ClientServiceClient implements ClientServiceInterface {
    private final ExecutorService executorService;
    private final TcpClient tcpClient;

    public ClientServiceClient(ExecutorService executorService, TcpClient tcpClient) {
        this.executorService = executorService;
        this.tcpClient = tcpClient;
    }

    @Override
    public CompletableFuture<Optional<Client>> addClient(Client client) throws Exception {
        Message message = new Message("add_client", Adapter.clientToOIString(client));
        Message response;

        try {
            response = tcpClient.sendAndReceive(message);
        }
        catch (SocketException e) {
            throw new RuntimeException(e.getCause());
        }

        String body = response.getBody();
        String header = response.getHeader();
        if(header.equals("error"))
            throw new Exception(body);

        return CompletableFuture.supplyAsync(
                Optional::empty,
                executorService
        );
    }

    @Override
    public CompletableFuture<Optional<Client>> delete(Long aLong) throws Exception {
        Message message = new Message("delete_client", String.valueOf(aLong));
        Message response = tcpClient.sendAndReceive(message);

        String body = response.getBody();
        String header = response.getHeader();
        if (header.equals("error"))
            throw new Exception(body);

        return CompletableFuture.supplyAsync(
                ()->Optional.of(Adapter.messageToClient(body)),
                executorService
        );
    }

    @Override
    public CompletableFuture<Optional<Client>> update(Client entity) throws Exception {
        Message message = new Message("update_client", Adapter.clientToOIString(entity));
        Message response = tcpClient.sendAndReceive(message);

        String body = response.getBody();
        String header = response.getHeader();
        if (header.equals("error"))
            throw new Exception(body);

        return CompletableFuture.supplyAsync(
                Optional::empty,
                executorService
        );
    }

    @Override
    public CompletableFuture<Boolean> existsClient(Long id) throws Exception {
        return null;
    }

    @Override
    public CompletableFuture<Set<Client>> getAllClients() {
        Message message = new Message("get_all_clients", "");
        Message response;
        try {
            response = tcpClient.sendAndReceive(message);
        } catch (SocketException e){
            throw new RuntimeException(e.getCause());
        }
        Set<Client> clients;

        String body = response.getBody();
        if (body.length() > 0) {
            String[] tokens = (body.split(System.lineSeparator()));
            clients = Arrays.stream(tokens).map(Adapter::messageToClient).collect(Collectors.toSet());
        }
        else
            clients = new HashSet<>();

        return CompletableFuture.supplyAsync(
                ()-> clients,
                executorService
        );
    }

    @Override
    public CompletableFuture<Set<Client>> filterClientsByName(String s) {
        return null;
    }

    @Override
    public CompletableFuture<Void> deleteAllOrdersByClientId(Long clientId) {
        return null;
    }
}
