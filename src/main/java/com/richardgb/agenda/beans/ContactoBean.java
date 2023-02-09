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
import org.primefaces.event.SelectEvent;

/**
 *
 * @author richard
 */
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
            contactoFacade.create(contacto);
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

    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }

    public List<Contacto> getContactos() {
        return contactos;
    }

    public void setContactos(List<Contacto> contactos) {
        this.contactos = contactos;
    }

    public List<Contacto> getContactosFiltrados() {
        return contactosFiltrados;
    }

    public void setContactosFiltrados(List<Contacto> contactosFiltrados) {
        this.contactosFiltrados = contactosFiltrados;
    }
    
}
