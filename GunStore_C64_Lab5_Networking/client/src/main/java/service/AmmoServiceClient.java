package service;

import TCP.TcpClient;
import domain.Ammunition;
import message.Message;
import utils.Adapter;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

public class AmmoServiceClient implements AmmoServiceInterface{
    private final ExecutorService executorService;
    private final TcpClient tcpClient;

    public AmmoServiceClient(ExecutorService executorService, TcpClient tcpClient){
        this.executorService = executorService;
        this.tcpClient = tcpClient;
    }

    @Override
    public CompletableFuture<Optional<Ammunition>> addAmmunition(Ammunition ammo) throws Exception {
        Message message = new Message("add_ammunition", Adapter.ammoToOIString(ammo));
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
    public CompletableFuture<Optional<Ammunition>> delete(Long aLong) throws Exception {
        Message message = new Message("delete_ammunition", String.valueOf(aLong));
        Message response = tcpClient.sendAndReceive(message);

        String body = response.getBody();
        String header = response.getHeader();
        if (header.equals("error"))
            throw new Exception(body);

        return CompletableFuture.supplyAsync(
                ()->Optional.of(Adapter.messageToAmmo(body)),
                executorService
        );
    }

    @Override
    public CompletableFuture<Optional<Ammunition>> update(Ammunition ammo) throws Exception {
        Message message = new Message("update_ammunition", Adapter.ammoToOIString(ammo));
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
    public CompletableFuture<Boolean> existsAmmunition(Long id) {
        Message message = new Message("exists_ammunition", String.valueOf(id));

        Message response = tcpClient.sendAndReceive(message);

        String body = response.getBody();

        return CompletableFuture.supplyAsync(
                () -> Boolean.valueOf(body),
                executorService
        );
    }

    @Override
    public CompletableFuture<Set<Ammunition>> getAllAmmunition() {
        Message message = new Message("get_all_ammunition", "");
        return getSetCompletableFuture(message);
    }

    private CompletableFuture<Set<Ammunition>> getSetCompletableFuture(Message message) {
        Message response = tcpClient.sendAndReceive(message);

        Set<Ammunition> filtered_ammo;
        String body = response.getBody();
        if (body.length() > 0) {
            String[] tokens = (body.split(System.lineSeparator()));
            filtered_ammo = Arrays.stream(tokens).map(Adapter::messageToAmmo).collect(Collectors.toSet());
        }
        else
            filtered_ammo = new HashSet<>();

        return CompletableFuture.supplyAsync(
                ()-> filtered_ammo,
                executorService
        );
    }

    private CompletableFuture<List<Ammunition>> getListCompletableFuture(Message message) {
        Message response = tcpClient.sendAndReceive(message);

        List<Ammunition> sorted_ammo;
        String body = response.getBody();
        if (body.length() > 0) {
            String[] tokens = (body.split(System.lineSeparator()));
            sorted_ammo = Arrays.stream(tokens).map(Adapter::messageToAmmo).collect(Collectors.toList());
        }
        else
            sorted_ammo = new ArrayList<>();

        return CompletableFuture.supplyAsync(
                ()-> sorted_ammo,
                executorService
        );
    }
}
