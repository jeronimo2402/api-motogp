package com.umanizales.apimotogp.controller;


import com.umanizales.apimotogp.model.Classification;
import com.umanizales.apimotogp.model.Motorcycle;
import com.umanizales.apimotogp.model.dto.ClassificationDTO;
import com.umanizales.apimotogp.model.dto.PilotsDTO;
import com.umanizales.apimotogp.service.RaceService;
import com.umanizales.apimotogp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path="race")
public class RaceController {

    @Autowired
    private RaceService raceService;
    @Autowired
    private UserService userService;

    @PostMapping(path="/classification")
    public String saveClassification(@RequestBody Classification classification){
        if(Objects.equals(userService.getCurrentUser().getRole().getCode(),"1001")) {
            raceService.saveClassification(classification);
            return "Classification saved";
        }
        return "User must be administrator";
    }
    @PostMapping(path="/times")
    public String pilotTime(@RequestBody ClassificationDTO classificationDTO){
        if(Objects.equals(userService.getCurrentUser().getRole().getCode(),"1001")) {
            return raceService.saveTime(classificationDTO);
        }
        return "User must be administrator";
    }
    @PostMapping(path="/state")
    public String setState(@RequestBody String state){
        if(Objects.equals(userService.getCurrentUser().getRole().getCode(),"1001")) {
            if (raceService.setRaceState(state)) {
                return "state actualized";
            } else {
                return "invalid state, it must be: initialized, closed or in process";
            }
        }
        return "User must be administrator";
    }
    @GetMapping(path="/start")
    public String startRace(){
        if (Objects.equals(raceService.getRace().getState(), "in process")) {
            raceService.startRace();
            return "Race started";
        }
        return "Race must be in process to start it";
    }
    @PostMapping(path="/pilotfell")
    public String pilotFell(@RequestBody Motorcycle motorcycle) {
        if (Objects.equals(raceService.getRace().getState(), "initialized")) {
            return raceService.pilotFell(motorcycle);
        }
        return "race hasn't started";
    }

    @PostMapping(path="/pilotentry")
    public String pilotEntry(@RequestBody PilotsDTO pilotsDTO){
        if (Objects.equals(raceService.getRace().getState(), "initialized")) {
            return raceService.pilotReEntry(pilotsDTO.getMotorcycle(), pilotsDTO.getPosition());
        }
        return "race hasn't started";
    }
    @PostMapping(path="/pilotadvance")
    public String pilotAdvance(@RequestBody PilotsDTO pilotsDTO){
        if (Objects.equals(raceService.getRace().getState(), "initialized")) {
            return raceService.pilotAdvance(pilotsDTO.getMotorcycle(), pilotsDTO.getPosition());
        }
        return "race hasn't started";
    }
    @PostMapping(path="/pilotretreat")
    public String pilotRetreat(@RequestBody PilotsDTO pilotsDTO){
        if (Objects.equals(raceService.getRace().getState(), "initialized")) {
            return raceService.pilotRetreat(pilotsDTO.getMotorcycle(), pilotsDTO.getPosition());
        }
        return "race hasn't started";
    }
    @PostMapping(path="/takeoutpilot")
    public String pilotExit(@RequestBody Motorcycle motorcycle){
        if (Objects.equals(raceService.getRace().getState(), "initialized")) {
            return raceService.pilotExit(motorcycle);
        }
        return "Race hasn't started";
    }
    @GetMapping(path="/end")
    public List<Motorcycle> endRace(){
        if (Objects.equals(raceService.getRace().getState(), "initialized")) {
            return raceService.endRace();
        }
        return null;
    }
}
