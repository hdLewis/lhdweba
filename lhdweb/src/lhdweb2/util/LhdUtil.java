package lhdweb2.util;

import lhdweb2.dao.LhdDao;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

public class LhdUtil {
    static Logger log=Logger.getLogger(LhdDao.class);
    public static void setParams(HttpServletRequest request,Object obj){

        try{
            Enumeration<String> params=request.getParameterNames();
            while (params.hasMoreElements()){
                String inputName=params.nextElement();
                if(inputName.equals("op")){
                    continue;
                }
                String inputValue=request.getParameter(inputName);
                Field field=obj.getClass().getDeclaredField(inputName);
                field.setAccessible(true);
                Class<?> fieldType=field.getType();
                if ((Byte.TYPE==fieldType)||(Byte.class==fieldType)){
                    field.set(obj,Byte.valueOf(inputValue));
                }else if ((Short.TYPE==fieldType)||(Short.class==fieldType)){
                    field.set(obj,Short.valueOf(inputValue));
                }else if ((Integer.TYPE==fieldType)||(Integer.class==fieldType)){
                    field.set(obj,Integer.valueOf(inputValue));
                }else if (String.class==fieldType){
                    field.set(obj,inputValue);
                }else if ((Long.TYPE==fieldType)||(Long.class==fieldType)){
                    field.set(obj,Long.valueOf(inputValue));
                }else if ((Float.TYPE==fieldType)||(Float.class==fieldType)){
                    field.set(obj,Float.valueOf(inputValue));
                }else if ((Double.TYPE==fieldType)||(Double.class==fieldType)){
                    field.set(obj,Double.valueOf(inputValue));
                }else if (Character.TYPE==fieldType) {
                    if ((inputValue!=null)&&(inputValue.length()>0))
                    field.set(obj, Character.valueOf(inputValue.charAt(0)));
                }else if (Date.class==fieldType){
                    SimpleDateFormat sDf=new SimpleDateFormat("yyyy-MM-dd");
                    try{
                        java.util.Date date1=sDf.parse(inputValue);
                        Date date=new Date(date1.getTime());
                        field.set(obj,date);
                    }catch (Exception e){
                        field.set(obj,new Date(System.currentTimeMillis()));
                        log.error(e.getMessage());
                    }
                }
            }
        }catch (Exception e){
            log.error(e.getMessage());
            }
        }

    }

