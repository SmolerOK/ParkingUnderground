package ru.artsec.ParkingUnderground.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.artsec.ParkingUnderground.model.Hl_parking;

import java.util.List;

public interface Parking extends JpaRepository<Hl_parking, Long> {

    List<Hl_parking> findAllBy();

    Hl_parking findAllById(Long id);

    @Query(value =
            " UPDATE HL_PARKING " +
            " SET ID_DB = 1," +
            " NAME = :#{#hl_parking.name}," +
            " ENABLED = :#{#hl_parking.enabled}" +
                    " WHERE (ID = :#{#hl_parking.id})",
            nativeQuery = true)
    @Transactional
    @Modifying
    void updateParking(@Param("hl_parking") Hl_parking hl_parking);

    @Query(value = "delete from HL_PARKING where ID = :id",
    nativeQuery = true)
    @Transactional
    @Modifying
    void deleteHl_parkingById(@Param("id") Long id);

    @Query(value = "INSERT INTO HL_PARKING (ID_DB, NAME, ENABLED) VALUES (1, :name, 1);",
    nativeQuery = true)
    @Transactional
    @Modifying
    void insertParkingByName(@Param("name") String name);
}
