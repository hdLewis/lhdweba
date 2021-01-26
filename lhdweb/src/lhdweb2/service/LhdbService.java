package lhdweb2.service;

public interface LhdbService {
    boolean register(String lhduser,String lhdpass);
    boolean login(String lhduser,String lhdpass);
    boolean check(String lhduser);

}
