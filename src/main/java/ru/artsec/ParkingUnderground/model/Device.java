package ru.artsec.ParkingUnderground.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "device")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Device {

    @Id
    Long id_dev;
    String name;
}
