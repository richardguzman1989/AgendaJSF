/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.richardgb.agenda.beans;

import com.richardgb.agenda.ejb.ContactoFacade;
import com.richardgb.agenda.entidades.Contacto;
import com.richardgb.agenda.utils.MessageUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author richard
 */

@Getter
@Setter

@ViewScoped
@Named
public class ContactoBean implements Serializable {

    private Contacto contacto;
    private List<Contacto> contactos;
    private List<Contacto> contactosFiltrados;

    @EJB
    private ContactoFacade contactoFacade;

    @PostConstruct
    public void iniciar() {
        limpiar();
        try {
            contactos = new ArrayList<>();
            consultarDatos();
        } catch (Exception ex) {
            contactos = new ArrayList<>();
        }
    }

    public void guardar() {
        try {
            if (contacto.getId() == null) {
                contactoFacade.create(contacto);
                MessageUtils.sendSuccessfulMessage("message", "¡Contacto " + contacto.getNombre() + " creado exitósamente!");
            } else {
                contactoFacade.edit(contacto);
                MessageUtils.sendSuccessfulMessage("message", "¡Contacto " + contacto.getNombre() + " actualizado exitósamente!");
            }
            limpiar();
            consultarDatos();

        } catch (Exception ex) {
            Logger.getLogger(ContactoBean.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.sendErrorMessage("message", "¡Error guardando contacto!");
        }
    }

    public void limpiar() {
        this.contacto = new Contacto();
    }

    public void consultarDatos() {
        contactos = contactoFacade.findAll();
    }

    public void onRowSelectContacto(SelectEvent event) {
        this.contacto = (Contacto) event.getObject();
    }

}
