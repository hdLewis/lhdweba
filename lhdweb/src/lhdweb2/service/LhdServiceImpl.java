package lhdweb2.service;

import lhdweb2.dao.LhdDao;
import lhdweb2.entity.Lhd;
import org.apache.log4j.Logger;

import java.util.List;


public class LhdServiceImpl implements  LhdService{
    Logger log=Logger.getLogger(LhdServiceImpl.class);
    @Override
    public boolean insertLhd(Lhd lhd) {
        //String sql="insert into lhda values ('" + lhd.getLhda() + "','" + lhd.getLhdb() + "','" + lhd.getLhdc() + "')";
        String sql="insert into lhda(lhda,lhdb,lhdc) values(?,?,?)";
        log.debug(sql);
        return LhdDao.exeUpdate(sql,new Object[]{lhd.getLhda(),lhd.getLhdb(),lhd.getLhdc()});
    }

    @Override
    public boolean deleteLhd(int lhda) {
       // String sql="delete from lhda where lhda='"+lhda+"'";
        String sql="delete from lhda where lhda=?";
        log.debug(sql);
        return LhdDao.exeUpdate(sql,new Object[]{lhda});
    }

    @Override
    public boolean checkLhd(int lhda) {
        String sql="select * from lhda where lhda='"+lhda+"'";
        log.debug(sql);
        return LhdDao.isExist(sql);
    }

    @Override
    public boolean updateLhd(Lhd lhd, int oldlhda) {

        String sql="update lhda set lhda='" + lhd.getLhda() + "',lhdb='" + lhd.getLhdb() + "',lhdc='" + lhd.getLhdc() + "'where lhda='"+oldlhda+"'";
        log.debug(sql);
        return LhdDao.exeUpdate(sql);
    }

    @Override
    public List<Lhd> getLhd(String lhda) {
        String sql="select * from lhda where lhda like '%" + lhda + "%'";
        log.debug(sql);
        return LhdDao.getLhd(sql);
    }
}
