/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.richardgb.agenda.utils;

import com.google.gson.Gson;
import com.richardgb.agenda.clases.Reloj;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author richard
 */
public class RestClientReloj {

    public Reloj obtenerFechaHoraActual() {
        
        try {
            URL obj = new URL("https://www.timeapi.io/api/Time/current/zone?timeZone=America/Santo_Domingo");
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", MediaType.APPLICATION_JSON);

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream()), "UTF-8"));

            String output;
            output = br.lines().collect(Collectors.joining());

            Gson g = new Gson();
            
            Reloj timeZone = g.fromJson(output, Reloj.class);
            return timeZone;
            
        } catch (Exception ex) {
            System.out.println("Error al intentar obtener la hora: " + ex.getLocalizedMessage());
            return null;
        }
        
    }

}
