/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.muic.ooc.webapp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author gigadot
 */
public class SecurityService {
    
//    private Map<String, String> userCredentials = new HashMap<String, String>() {{
//        put("admin", "123456");
//        put("muic", "1111");
//    }};

//    private DatabaseService databaseService;

    public boolean isAuthorized(HttpServletRequest request) {

        Map<String, String> userCredentials = new DatabaseService().readData();
//        curUserId = (String) request.getSession()
//                .getAttribute("username");
//        System.out.println("asdf"+curUserId);

        String username = (String) request.getSession()
                .getAttribute("username");
        // do checking
       return (username != null && userCredentials.containsKey(username));
    }

    public boolean authenticate(String username, String password, HttpServletRequest request) {

        Map<String, String> userCredentials = new DatabaseService().readData();

        String passwordInDB = userCredentials.get(username);
        boolean isMatched = StringUtils.equals(password, passwordInDB);

        System.out.println(passwordInDB);
        System.out.println(password);
        System.out.println(username);

        if (isMatched) {
            request.getSession().setAttribute("username", username);
            return true;
        } else {
            return false;
        }
    }

    public boolean allowAdd(String username) {

        Map<String, String> userCredentials = new DatabaseService().readData();

        ArrayList<String> allUsers = new ArrayList<>();
        for (Map.Entry<String, String> entry: userCredentials.entrySet()){
            allUsers.add(entry.getKey());
        }

        return !(allUsers.contains(username));
    }
    
    public void logout(HttpServletRequest request) {
        request.getSession().invalidate();
    }

}
