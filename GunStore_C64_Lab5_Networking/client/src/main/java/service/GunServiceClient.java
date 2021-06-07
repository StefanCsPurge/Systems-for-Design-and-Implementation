package service;

import TCP.TcpClient;
import domain.Gun;
import message.Message;
import utils.Adapter;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

public class GunServiceClient implements GunServiceInterface{
    private final ExecutorService executorService;
    private final TcpClient tcpClient;

    public GunServiceClient(ExecutorService executorService, TcpClient tcpClient){
        this.executorService = executorService;
        this.tcpClient = tcpClient;
    }

    @Override
    public CompletableFuture<Optional<Gun>> addGun(Gun gun) throws Exception {
        Message message = new Message("add_gun", Adapter.gunToOIString(gun));
        Message response = tcpClient.sendAndReceive(message);

        String body = response.getBody();
        String header = response.getHeader();
        if (header.equals("error"))
            throw new Exception(body);

        return CompletableFuture.supplyAsync(()-> {
                    try {
                        Thread.sleep(20000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return Optional.empty();},
                executorService
        );
    }

    @Override
    public CompletableFuture<Optional<Gun>> delete(Long aLong) throws Exception {
        Message message = new Message("delete_gun", String.valueOf(aLong));
        Message response = tcpClient.sendAndReceive(message);

        String body = response.getBody();
        String header = response.getHeader();
        if (header.equals("error"))
            throw new Exception(body);

        return CompletableFuture.supplyAsync(
                ()->Optional.of(Adapter.messageToGun(body)),
                executorService
        );
    }

    @Override
    public CompletableFuture<Optional<Gun>> update(Gun gun) throws Exception {
        Message message = new Message("update_gun", Adapter.gunToOIString(gun));
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
    public CompletableFuture<Boolean> existsGun(Long id) {
        Message message = new Message("exists_gun", String.valueOf(id));

        Message response = tcpClient.sendAndReceive(message);

        String body = response.getBody();

        return CompletableFuture.supplyAsync(
                () -> Boolean.valueOf(body),
                executorService
        );
    }

    @Override
    public CompletableFuture<Set<Gun>> getAllGuns() {
        Message message = new Message("get_all_guns", "");
        return getSetCompletableFuture(message);
    }

    @Override
    public CompletableFuture<Set<Gun>> filterGunsByModel(String s) {
        Message message = new Message("filter_guns_model", s);
        return getSetCompletableFuture(message);
    }

    @Override
    public CompletableFuture<Set<Gun>> filterGunsByType(String s) {

        Message message = new Message("filter_guns_type", s);
        return getSetCompletableFuture(message);
    }

    private CompletableFuture<Set<Gun>> getSetCompletableFuture(Message message) {
        Message response = tcpClient.sendAndReceive(message);

        Set<Gun> filtered_guns;
        String body = response.getBody();
        if (body.length() > 0) {
            String[] tokens = (body.split(System.lineSeparator()));
            filtered_guns = Arrays.stream(tokens).map(Adapter::messageToGun).collect(Collectors.toSet());
        }
        else
            filtered_guns = new HashSet<>();

        return CompletableFuture.supplyAsync(
                ()-> filtered_guns,
                executorService
        );
    }

    @Override
    public CompletableFuture<List<Gun>> getSortedGuns() {
        Message message = new Message("sort_guns_price", "");
        return getListCompletableFuture(message);
    }

    @Override
    public CompletableFuture<List<Gun>> getTop3SoldGuns() {
        Message message = new Message("get_top3_guns", "");
        return getListCompletableFuture(message);
    }

    private CompletableFuture<List<Gun>> getListCompletableFuture(Message message) {
        Message response = tcpClient.sendAndReceive(message);

        List<Gun> sorted_guns;
        String body = response.getBody();
        if (body.length() > 0) {
            String[] tokens = (body.split(System.lineSeparator()));
            sorted_guns = Arrays.stream(tokens).map(Adapter::messageToGun).collect(Collectors.toList());
        }
        else
            sorted_guns = new ArrayList<>();

        return CompletableFuture.supplyAsync(
                ()-> sorted_guns,
                executorService
        );
    }

    @Override
    public void deleteAllOrdersByGunId(Long gunId) {}
}
