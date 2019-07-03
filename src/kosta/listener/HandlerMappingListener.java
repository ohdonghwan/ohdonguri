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
    	//�̸� ������ start �ɶ� ���� ��ü�� �����ؼ� Map�� ����
    	Map<String, Controller> map = new HashMap<>();
    	
    	//1.pmmmroperties���� �ε�.
    	ServletContext application = e.getServletContext();
    	String fileName = application.getInitParameter("fileName");
    	System.out.println("fileName : " + fileName);
    	ResourceBundle rb = ResourceBundle.getBundle(fileName);
    	
    	//2.key�� value�� �и��ϸ鼭 value�� ��ü�� ���� �ʿ� ����
    	Iterator<String> it = rb.keySet().iterator();    	
    			while(it.hasNext()) {
    				String key = it.next();
    				String value = rb.getString(key);
    				
    				//���ڿ��� ��ü�� ��ȯ
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
    				
    			}//while��
    			application.setAttribute("map", map);	
    	
    }
	
}
