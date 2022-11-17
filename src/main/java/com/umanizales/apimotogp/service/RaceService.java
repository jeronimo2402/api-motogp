package com.umanizales.apimotogp.service;


import com.umanizales.apimotogp.model.Classification;
import com.umanizales.apimotogp.model.Motorcycle;
import com.umanizales.apimotogp.model.Race;
import com.umanizales.apimotogp.model.dto.ClassificationDTO;
import com.umanizales.apimotogp.model.dto.PilotsDTO;
import com.umanizales.apimotogp.repository.ClassificationDTORepository;
import com.umanizales.apimotogp.repository.ClassificationRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Data
public class RaceService {
    @Autowired
    private ClassificationRepository classificationRepository;
    @Autowired
    private ClassificationDTORepository classificationDTORepository;
    @Autowired
    private MotorcycleService motorcycleService;
    private Classification classification;
    private Race race;
    private List<Classification> classifications;

    public RaceService(){
        classifications = classificationRepository.findAll();
    }
    public void saveClassification(Classification classification){
        for (Classification classification1:classifications){
            classification1.setState(false);
            classificationRepository.save(classification1);
        }
        classification.setState(true);
        this.classification = classification;
        classificationRepository.save(classification);
    }
    public String saveTime(ClassificationDTO classificationDTO){
        race.setState("in process");
        if (classification.getGrill()==null) {
            classification.getGrill().add(classificationDTO);
            classificationDTORepository.save(classificationDTO);
        }
        else {
            List<ClassificationDTO> list = new ArrayList<>();
            for(ClassificationDTO classificationDTO1:classification.getGrill()) {
                if(classificationDTO.getTime()<classificationDTO1.getTime()){
                    if(!list.contains(classificationDTO)){
                        list.add(classificationDTO);
                        classificationDTORepository.save(classificationDTO);
                    }
                }
                list.add(classificationDTO1);
            }
            classification.setGrill(list);
        }
        return "Time successfully added";
    }


    public boolean setRaceState(String state){
        if (Objects.equals(state, "initialized") || Objects.equals(state,"closed")||Objects.equals(state,"in process")){
            race.setState(state);
            if(Objects.equals(race.getState(), "initialized")) {
                return true;
            }
        }
        return false;
    }

    @PostConstruct
    public void startRace(){
        for(ClassificationDTO classificationDTO:classification.getGrill())
        {
            race.getListDE().addEnd(classificationDTO.getMotorcycle());
        }
        race.setState("initialized");
    }

    public String pilotFell(Motorcycle motorcycle){
        race.getPilotsFelt().add(new PilotsDTO(motorcycle,race.getListDE().deletePilot(motorcycle.getPilot())));
        return "pilot partially out of race";
    }

    public String pilotReEntry(Motorcycle motorcycle,int pos){
        for(PilotsDTO pilotsDTO :race.getPilotsFelt()){
            if (Objects.equals(pilotsDTO.getMotorcycle(),motorcycle)){
                if(pos> pilotsDTO.getPosition()){
                    race.getListDE().addPosition(pos,motorcycle);
                    return "pilot re integrated";
                }
                return "not valid position to re entry";
            }
        }
        return "No coincidence with pilots felt";
    }

    public String pilotAdvance(Motorcycle motorcycle, int num){
        if(race.getListDE().advance(motorcycle.getPilot(), num)){
            return "pilot advanced";
        }else{
            return "invalid number";
        }
    }
    public String pilotRetreat(Motorcycle motorcycle, int num){
        if(race.getListDE().losePosition(motorcycle.getPilot(), num)){
            return "pilot retreat";
        }else{
            return"invalid number";
        }
    }
    public String pilotExit(Motorcycle motorcycle){
        motorcycle.setState(false);
        motorcycleService.saveMotorcycle(motorcycle);
        race.getListDE().deletePilot(motorcycle.getPilot());
        return "pilot eliminated";
    }

    public List<Motorcycle> endRace(){
        race.setState("closed");
        return race.getListDE().getList();
    }
}
