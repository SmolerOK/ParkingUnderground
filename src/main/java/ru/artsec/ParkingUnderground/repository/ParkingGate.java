package ru.artsec.ParkingUnderground.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.artsec.ParkingUnderground.model.Hl_parking_gate;

import java.util.List;

@Repository
public interface ParkingGate extends JpaRepository<Hl_parking_gate, Long> {

    @Query(value = "UPDATE HL_PARKING_GATE SET IS_ENTER = :isEnter" +
            " WHERE (ID_PARKING = :idParking) AND (ID_DEV = :idDev) AND (ID_DB = 1)",
            nativeQuery = true)
    @Modifying
    @Transactional
    void updateGate(@Param("idParking") Long idParking, @Param("idDev") Long idDev, @Param("isEnter") String isEnter);

    @Query(value = "INSERT INTO HL_PARKING_GATE (ID_PARKING, ID_DEV, ID_DB, IS_ENTER, NAME) " +
            "VALUES (3, 97, 1, 1, 'Ворота 1 въезд')",nativeQuery = true)
    @Modifying
    @Transactional
    void insertGate(@Param("idParking") Long idParking,
                    @Param("name") String name,
                    @Param("isEnter") String isEnter
                    );

    @Query(value = "select hlp.id_parking, hlp.id_dev, hlp.id_db, hlp.is_enter, d.name, hlp.ID from hl_parking_gate hlp\n" +
            "join device d on d.id_dev=hlp.id_dev\n" +
            "where hlp.id_parking = :id", nativeQuery = true)
    List<Hl_parking_gate> getAllGate(@Param("id") Long id);

    @Query(value = "select hlp.id_parking, hlp.id_dev, hlp.id_db, hlp.is_enter, d.name from hl_parking_gate hlp\n" +
            "join device d on d.id_dev=hlp.id_dev\n" +
            "where hlp.id_dev = :id", nativeQuery = true)
    Hl_parking_gate getDeviceById(@Param("id") Long id);

    @Query(value = "delete from HL_PARKING_GATE where ID_DEV = :id", nativeQuery = true)
    @Modifying
    @Transactional
    void deleteById(Long id);
}
