/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avbravo.jettraframeworkclient.microprofileconfig;

import com.avbravo.jettraframework.config.JettraConfig;

/**
 *
 * @author avbravo
 */
public class LeerPropiedad implements JettraConfig{
    
    public void leerpropiedad(String propiedad){
        System.out.println(getMicroprofileConfig(propiedad));
    }
}
