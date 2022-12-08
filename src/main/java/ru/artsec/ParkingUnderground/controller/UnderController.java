package ru.artsec.ParkingUnderground.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.artsec.ParkingUnderground.Event.NewEvent;
import ru.artsec.ParkingUnderground.model.Hl_param;
import ru.artsec.ParkingUnderground.model.Hl_parking;
import ru.artsec.ParkingUnderground.model.Hl_parking_gate;
import ru.artsec.ParkingUnderground.model.InsertParking;
import ru.artsec.ParkingUnderground.repository.*;

@Slf4j
@Controller
public class UnderController {

    final Parking parking;
    final ParkingGate parkingGate;
    final DeviceRepository device;
    final Inside inside;
    final ParamRepository paramRepository;
    NewEvent event = new NewEvent();

    public UnderController(Parking parking,
                           ParkingGate parkingGate,
                           DeviceRepository device,
                           Inside inside,
                           ParamRepository paramRepository
    ) {
        this.parking = parking;
        this.parkingGate = parkingGate;
        this.device = device;
        this.inside = inside;
        this.paramRepository = paramRepository;
    }

    @GetMapping("/")
    public String machineSeats(Model model) {
        log.info("Получен GET запрос");
        try{
            model.addAttribute("event", event);
            return "index";
        }catch (Exception ex){
            model.addAttribute("error", ex.getLocalizedMessage());
        }
        return "errorMessage";
    }

    @GetMapping("/layout")
    public String layout(Model model) {
        try {
            model.addAttribute("event", event);
            return "layout";
        }catch (Exception ex){
            model.addAttribute("error", ex.getLocalizedMessage());
        }
        return "errorMessage";
    }

    @GetMapping("/machine-seats")
    public String seats(Model model) {
        try {
            model.addAttribute("event", event);
            model.addAttribute("parking", parking.findAllBy());
            model.addAttribute("insertParking", new InsertParking());
            return "machine-seats";
        }catch (Exception ex){
            model.addAttribute("error", ex.getLocalizedMessage());
        }
        return "errorMessage";

    }

    @GetMapping("/edit-parking/{id}")
    String editParking(Model model, @PathVariable Long id){
        try {
            model.addAttribute("event", event);
            model.addAttribute("parking", parking.findAllById(id));
            model.addAttribute("parkingGate", parkingGate.getAllGate(id));
            model.addAttribute("parkingInside", inside.selectAllInside(id));
            return "edit-parking";
        }catch (Exception ex){
            model.addAttribute("error", ex.getLocalizedMessage());
        }
        return "errorMessage";
    }

    @GetMapping("/edit-gate/{id}/{idDev}")
    String editGate(Hl_param hl_param, @PathVariable Long id, @PathVariable Long idDev, Model model, Hl_parking_gate gate){
        try {
            model.addAttribute("event", event);
            model.addAttribute("gate", gate);
            model.addAttribute("device",device.getAllBy());
            model.addAttribute("deviceById", parkingGate.getDeviceById(idDev));
            model.addAttribute("param", hl_param);
            return "edit-gate";
        }catch (Exception ex){
            model.addAttribute("error", ex.getLocalizedMessage());
        }
        return "errorMessage";
    }

    @GetMapping("/add-gate/{id}")
    String addGate(@ModelAttribute("gate") Hl_parking_gate gate, Model model, @PathVariable int id){
        try {
            model.addAttribute("event", event);
            model.addAttribute("device",device.getAllBy());
            return "add-gate";
        }catch (Exception ex){
            model.addAttribute("error", ex.getLocalizedMessage());
        }
        return "errorMessage";
    }

    @PostMapping("/edit-parking-impl")
    String editParkingImpl(@ModelAttribute Hl_parking hl_parking, Model model){
        try {
            parking.updateParking(hl_parking);
            event.setEvent(true);

            return "redirect:/machine-seats";
        }catch (Exception ex){
            model.addAttribute("error", ex.getLocalizedMessage());
        }
        return "errorMessage";

    }

    @PostMapping("/delete-parking/{id}")
    String deleteParking(@PathVariable("id") Long id, Model model){
        try {
            parking.deleteHl_parkingById(id);
            event.setEvent(true);

            return "redirect:/machine-seats";
        }catch (Exception ex){
            model.addAttribute("error", ex.getLocalizedMessage());
        }
        return "errorMessage";
    }

    @PostMapping("/insertParking")
    String insertParking(InsertParking insertParking, Model model){
        try {
            parking.insertParkingByName(insertParking.getName());
            event.setEvent(true);

            return "redirect:/machine-seats";
        }catch (Exception ex){
            model.addAttribute("error", ex.getLocalizedMessage());
        }
        return "errorMessage";
    }

    @PostMapping("/delete-gate/{id}/{idDev}")
    String deleteGate(@PathVariable Long id, @PathVariable Long idDev, Model model){
        try {
            parkingGate.deleteById(idDev);
            event.setEvent(true);

            return "redirect:/edit-parking/" + id;
        }catch (Exception ex){
            model.addAttribute("error", ex.getLocalizedMessage());
        }
        return "errorMessage";

    }

    @PostMapping("/save-gate/{id}/{idDev}")
    String saveGateById(Hl_parking_gate gate, @PathVariable Long id, @PathVariable Long idDev, Model model){
        try {
            parkingGate.updateGate(id, idDev, gate.getIs_enter());
            event.setEvent(true);

            return "redirect:/edit-parking/" + id;
        }catch (Exception ex){
            model.addAttribute("error", ex.getLocalizedMessage());
        }
        return "errorMessage";
    }

    @PostMapping("/adding-gate/{id}")
    String saveGate(@RequestParam String name, @RequestParam int is_enter, @PathVariable Long id, Model model, HttpEntity<String> entity){
        try {
            String i = entity.getBody();
            parkingGate.insertGate(id, name, is_enter);
            event.setEvent(true);
            return "redirect:/edit-parking/" + id;
        }catch (Exception ex) {
            model.addAttribute("error", ex.getLocalizedMessage());
        }
        return "errorMessage";
    }

    @PostMapping("/save-gate-dev/{id}/{idDev}")
    String saveGateDev(Hl_param hl_param,Model model, @PathVariable String id, @PathVariable String idDev){
        try{


//            paramRepository.addParamDevice();

            return "redirect:/edit-parking/" + id;
        }catch (Exception ex){
            model.addAttribute("error", ex.getLocalizedMessage());
        }
        return "errorMessage";
    }
}
