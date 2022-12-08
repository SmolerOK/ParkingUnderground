package ru.artsec.ParkingUnderground.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.artsec.ParkingUnderground.model.Device;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {

    @Query(value = "select d.id_dev, d.name,  hlpg.* from device d\n" +
            " left join hl_parking_gate hlpg on hlpg.id_dev=d.id_dev\n" +
            " where d.id_reader is not null\n" +
            " and hlpg.id_dev is null",
    nativeQuery = true)
    List<Device> getAllBy();
}
