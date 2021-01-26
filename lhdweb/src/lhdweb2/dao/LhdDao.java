package lhdweb2.dao;

import lhdweb2.entity.Lhd;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.io.FileReader;
import java.sql.*;
import java.util.*;

public class LhdDao {
//    private static String sDriver;
//    private static String sUrl;
//    private static String sUser;
//    private static String sPass;
     static   Logger log=Logger.getLogger(LhdDao.class);
//    static {
//
//        String sPath = Thread.currentThread().getContextClassLoader().getResource("/").getPath() + "/db.properties";
//        System.out.println(sPath);
//
//        try {
//            FileReader fr = new FileReader(sPath);
//            Properties prop = new Properties();
//            prop.load(fr);
//            sDriver = prop.getProperty("driver");
//            sUrl = prop.getProperty("url");
//            sUser = prop.getProperty("user");
//            sPass = prop.getProperty("pass");
//        } catch (Exception e) {
//            log.error(e.getMessage());
//        }
//
//    }

    private static Connection getConn() {
        Connection conn = null;

        try{
            //另一种连接数据库方式，java命名与目录接口
            Context ctx=new InitialContext();
            DataSource ds=(DataSource) ctx.lookup("java:comp/env/jdbc/lhd");
            //java:comp/env/是一个J2EE环境的定义，说白了就是代表当前J2EE应用的环境、JNDI是 Java 命名与目录接口（Java Naming and Directory Interface）
            //JNDI避免了程序与数据库之间的紧耦合，使应用更加易于配置、易于部署。
            conn=ds.getConnection();

        }catch (Exception e){
            log.error(e.getMessage());

        }


//        try {
//
//            Class.forName(sDriver);
//            conn = DriverManager.getConnection(sUrl, sUser, sPass);
//
//        } catch (Exception e) {
//            log.error(e.getMessage());
//        }
        return conn;
    }

    public static boolean exeUpdate(String sql) {
        Connection conn = getConn();
        try {
            Statement st = conn.createStatement();
            int cnt = st.executeUpdate(sql);
            return cnt > 0;
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    log.error(e.getMessage());
                }
            }
        }
        return false;
    }


    public static boolean exeUpdate(String sql,Object params[]) {
        Connection conn = getConn();
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            for (int i=0;i<params.length;i++){
                pst.setObject(i+1,params[i]);
            }
            int cnt = pst.executeUpdate();
            return cnt > 0;
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    log.error(e.getMessage());
                }
            }
        }
        return false;
    }


    public static List<Map> getAll(String sql){
        List<Map> lst=new ArrayList<Map>();

        Connection conn = getConn();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            ResultSetMetaData md=rs.getMetaData();
            int fldcnt=md.getColumnCount();
            while (rs.next()){
                Map<String,Object>  map=new HashMap<String, Object>();
                for (int i=1;i<=fldcnt;i++){
                    map.put(md.getColumnName(i),rs.getObject(md.getColumnName(i)));
                }
                lst.add(map);
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
        }

        return lst;
    }


    public static List<Lhd> getLhd(String sql) {
        ArrayList<Lhd> lst = new ArrayList<Lhd>();

        Connection conn = getConn();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Lhd y = new Lhd();
                y.setLhda(rs.getInt("lhda"));
                y.setLhdb(rs.getFloat("lhdb"));
                y.setLhdc(rs.getString("lhdc"));
                lst.add(y);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    log.error(e.getMessage());
                }
            }
        }
        return lst;
    }

    /**
     * 函数功能：判断查询语句是否有数据
     * @param sql
     * @return   true：有数据；false：没有数据或出现异常
     */
    public static boolean isExist(String sql) {

        Connection conn = getConn();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
           return rs.next();
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    log.error(e.getMessage());
                }
            }
        }
        return false;
    }
}
