/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.avbravo.jettraframeworkclient;

import com.avbravo.jettraframework.JettraFramework;
import com.avbravo.jettraframework.controller.UserHandler;
import com.avbravo.jettraframework.enumerations.Protocol;
import com.avbravo.jettraframework.model.JettraContext;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.text.TableView;

/**
 *
 * @author avbravo
 */
public class Start {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        
        List<JettraContext> jettraContexts = new ArrayList<>();
        jettraContexts.add(new JettraContext("/users", new UserHandler()));
        jettraContexts.add(new JettraContext("/users/", new UserHandler()));
        
        
          String host="localhost";
           JettraFramework local = new JettraFramework.Builder()
                .protocol(Protocol.HTTP)
                .port(8080)
                .logo(Boolean.TRUE)
                .jettraContext(jettraContexts)
                .start();
    }
}
