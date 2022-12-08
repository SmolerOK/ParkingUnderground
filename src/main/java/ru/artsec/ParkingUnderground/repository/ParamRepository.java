package ru.artsec.ParkingUnderground.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.artsec.ParkingUnderground.model.Hl_param;

public interface ParamRepository extends JpaRepository<Hl_param, Long> {

    @Query(value = "\n" +
            "INSERT INTO HL_PARAM (TABLO_IP, TABLO_PORT, BOX_IP, BOX_PORT, ID_GATE, ID_CAM, ID_DEV) " +
            "VALUES (:tablo_ip, :tablo_port, :box_ip, :box_port, :id_gate, :id_cam, :id_dev);",
    nativeQuery = true)
    @Modifying
    @Transactional
    void addParamDevice(@Param("tablo_ip") String tablo_ip,
                        @Param("tablo_port") Long tablo_port,
                        @Param("box_ip") String box_ip,
                        @Param("box_port") Long box_port,
                        @Param("id_gate") Long id_gate,
                        @Param("id_cam") Long id_cam,
                        @Param("id_dev") Long id_dev
                        );
}
