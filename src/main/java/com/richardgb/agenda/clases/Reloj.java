/*
 * Click nbfs;//nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs;//nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.richardgb.agenda.clases;

import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author richard
 */
@Data
public class Reloj implements Serializable {

    private Integer year;
    private Integer month;
    private Integer day;
    private Integer hour;
    private Integer minute;
    private Integer seconds;
    private Integer milliSeconds;
    private String dateTime;
    private String date;
    private String time;
    private String timeZone;
    private String dayOfWeek;
    private Boolean dstActive;

}
