package ru.artsec.ParkingUnderground.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.artsec.ParkingUnderground.model.Hl_inside;

import java.util.List;

@Repository
public interface Inside extends JpaRepository<Hl_inside, String> {

    @Query(
            value = "select hli.*, hlc.name as place,  hlp.name as parkingname  from hl_inside hli\n" +
                    "join hl_counters hlc on hlc.id = hli.counterid\n" +
                    " join hl_parking hlp on hlp.id = hli.counterid\n" +
                    " where hlp.id = :id",
            nativeQuery = true
    )
    List<Hl_inside> selectAllInside(@Param("id") Long id);
}
