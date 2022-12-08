package ru.artsec.ParkingUnderground.model;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;

@Entity
@Table(name="hl_parking")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class Hl_parking {

    @Id
    Long id;

    Long id_db;

    String name;

    Long enabled;
}
