package ru.artsec.ParkingUnderground.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hl_param")
public class Hl_param {

    @Id
    Long id;

    String tablo_ip;
    String tablo_port;
    String box_ip;
    String box_port;
    Long id_gate;
    Long id_cam;
    Long id_dev;
}
