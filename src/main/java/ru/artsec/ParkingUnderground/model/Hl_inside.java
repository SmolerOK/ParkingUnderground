package ru.artsec.ParkingUnderground.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hl_inside")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hl_inside {

    @Id
    String id_card;
    String entertime;
    Long counterid;
    String parkingname;
}
