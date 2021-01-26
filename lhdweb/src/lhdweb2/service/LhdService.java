package lhdweb2.service;

import lhdweb2.entity.Lhd;

import java.util.List;


public interface LhdService {
    //接口中只能定义常量/抽象函数
    //但是从jdk1.8开始，接口中可以放有函数体的函数
    boolean insertLhd(Lhd lhd);
    boolean deleteLhd(int lhda);
    boolean checkLhd(int lhda);
    boolean updateLhd(Lhd lhd,int oldlhda);
    List<Lhd> getLhd(String lhdc);
}
