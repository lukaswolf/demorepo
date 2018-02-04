package pl.lukaszwilk.demo.repo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.lukaszwilk.demo.repo.models.KeyModel;
import pl.lukaszwilk.demo.repo.models.ReservationModel;
import pl.lukaszwilk.demo.repo.models.repositories.KeyRepository;
import pl.lukaszwilk.demo.repo.models.repositories.ReservationRepositore;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class RestController {

    @Autowired
    ReservationRepositore reservationRepositore;
    @Autowired
    KeyRepository keyRepository;

    //produces jaki tekst bd zwracac aplikacja

//    @RequestMapping(value = "/rest/reservation" ,method = RequestMethod.GET,produces = MediaType.APPLICATION_XML_VALUE)
//    //responsentity pozwala azaminiec obiekt na json
//    public ResponseEntity responseEntity(){
//        return new ResponseEntity(reservationRepositore.findAll(),HttpStatus.OK);
//
//    }
@RequestMapping(value = "/rest/reservation", method = RequestMethod.GET,
        produces = "application/json")
public ResponseEntity reservationIndex(@RequestHeader("Password-App") String key){

    if(keyRepository.existsByText(key)){
        KeyModel keyModel = keyRepository.findByText(key);
        if(keyModel.getCounter() >= 100){
            return new ResponseEntity("Too many uses", HttpStatus.NOT_ACCEPTABLE);
        }else{
            keyModel.setCounter(keyModel.getCounter() + 1);
            keyRepository.save(keyModel);
        }
    }

    return new ResponseEntity(reservationRepositore.findAll(),HttpStatus.OK);
}



    @RequestMapping(value = "/rest/reservations/{text}",method = RequestMethod.GET,produces = "application/json")
    @ResponseBody
    public ResponseEntity responseEntity(@PathVariable("text") String text){
        return new ResponseEntity(reservationRepositore.findByLastnameIgnoreCase(text),HttpStatus.OK);

    }
    @RequestMapping(value = "/rest/reservation",method = RequestMethod.POST,produces = "application/json")
    public ResponseEntity responseEntity(@RequestBody ReservationModel model){
        reservationRepositore.save(model);
        return new ResponseEntity(HttpStatus.OK);

    }
    @RequestMapping(value = "/rest/reservations/{id}",method = RequestMethod.DELETE,produces = "application/json")
    @ResponseBody
    public ResponseEntity responseEntityDelete(@PathVariable("id") int id){
        reservationRepositore.delete(id);
        return new ResponseEntity(HttpStatus.OK);

    }
    @RequestMapping(value = "/rest/reservations/{id}/{data}",method = RequestMethod.PUT,produces = "application/json")
    @ResponseBody
    public ResponseEntity reservationDateChange(@PathVariable("id") int id,@PathVariable("data") String data){
        ReservationModel model = reservationRepositore.findOne(id);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate newDate = LocalDate.from(formatter.parse(data));

        if (reservationRepositore.existsByDataEquals(newDate)){
            return new ResponseEntity("This date alredy exist",HttpStatus.CONFLICT);
        }

        model.setData(newDate);
        reservationRepositore.save(model);
        return new ResponseEntity(HttpStatus.OK);

    }
    @RequestMapping(value = "/rest/reservation",method = RequestMethod.PUT,produces = "application/json")
    public ResponseEntity responseAct(@RequestBody ReservationModel model){
        reservationRepositore.save(model);
        return new ResponseEntity(HttpStatus.OK);
    }

}
