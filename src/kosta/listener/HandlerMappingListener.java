package kosta.listener;

import java.util.HashMap;

import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import kosta.controller.Controller;

/**
 * Application Lifecycle Listener implementation class HandlerMappingListener
 *
 */
@WebListener
public class HandlerMappingListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent arg0)  {}

    public void contextInitialized(ServletContextEvent e)  {
    	//미리 서버가 start 될때 사용될 객체를 생성해서 Map에 저장
    	Map<String, Controller> map = new HashMap<>();
    	
    	//1.pmmmroperties파일 로딩.
    	ServletContext application = e.getServletContext();
    	String fileName = application.getInitParameter("fileName");
    	System.out.println("fileName : " + fileName);
    	ResourceBundle rb = ResourceBundle.getBundle(fileName);
    	
    	//2.key와 value를 분리하면서 value를 객체로 만들어서 맵에 저장
    	Iterator<String> it = rb.keySet().iterator();    	
    			while(it.hasNext()) {
    				String key = it.next();
    				String value = rb.getString(key);
    				
    				//문자열을 객체로 변환
    				Controller classObj;
					try {
						classObj = (Controller)Class.forName(value).newInstance();

						System.out.println(classObj);
						
	    				map.put(key, classObj);
					} catch (InstantiationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IllegalAccessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
    				
    			}//while끝
    			application.setAttribute("map", map);	
    	
    }
	
}
