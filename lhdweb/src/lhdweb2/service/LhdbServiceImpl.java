package lhdweb2.service;

import lhdweb2.dao.LhdDao;
import org.apache.log4j.Logger;

public class LhdbServiceImpl implements LhdbService {
    Logger log=Logger.getLogger(LhdbServiceImpl.class);
    @Override
    public boolean register(String lhduser, String lhdpass) {
        String sql="insert into lhdb values ('" + lhduser + "','" + lhdpass + "')";
        log.debug(sql);
        return LhdDao.exeUpdate(sql);
    }

    @Override
    public boolean login(String lhduser, String lhdpass) {
        String sql="select * from lhdb where lhduser='"+lhduser+"'and lhdpass='"+lhdpass+"'";
        log.debug(sql);
        return LhdDao.isExist(sql);
    }

    @Override
    public boolean check(String lhduser) {
        String sql="select * from lhdb where lhduser='"+lhduser+"'";
        log.debug(sql);
        return LhdDao.isExist(sql);
    }
}
