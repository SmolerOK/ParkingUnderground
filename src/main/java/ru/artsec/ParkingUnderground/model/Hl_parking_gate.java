package ru.artsec.ParkingUnderground.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hl_parking_gate")
public class Hl_parking_gate {

    @Id
    Long id;
    int id_dev = 0;
    int id_parking;
    int id_db;
    String is_enter = "1";
    String name;
}
