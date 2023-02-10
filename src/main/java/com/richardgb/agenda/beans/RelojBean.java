/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.richardgb.agenda.beans;

import com.richardgb.agenda.clases.Reloj;
import com.richardgb.agenda.utils.RestClientReloj;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author richard
 */
@Getter
@Setter

@ViewScoped
@Named
public class RelojBean implements Serializable {

    private String fechaHoraActual;

    @PostConstruct
    public void iniciar() {
        try {
            fechaHoraActual = "00:00:00 A.M.";
            obtenerFechaFormateada();
        } catch (Exception ex) {

        }
    }

    private Reloj consultarFecha() {
        RestClientReloj restClientTimeZone = new RestClientReloj();
        return restClientTimeZone.obtenerFechaHoraActual();
    }

    public void obtenerFechaFormateada() {
        Reloj horaActual = consultarFecha();
        String fechaActual = horaActual.getDate() + " " + 
                horaActual.getTime() + ":" + String.format("%02d", horaActual.getSeconds());
        fechaHoraActual = fechaActual;
    }

}
