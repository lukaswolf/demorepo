package pl.lukaszwilk.demo.repo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.lukaszwilk.demo.repo.models.ReservationModel;
import pl.lukaszwilk.demo.repo.models.forms.ReservationForm;
import pl.lukaszwilk.demo.repo.models.repositories.ReservationRepositore;
import pl.lukaszwilk.demo.repo.models.services.StringService;
import sun.util.resources.cldr.rm.CalendarData_rm_CH;

import javax.validation.Valid;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MainController {
    @Autowired
    StringService stringService;
    @Autowired
    ReservationRepositore reservationRepositore;

    @GetMapping(value = "/" )
    public String index(Model model){
        PageRequest pageRequest= new PageRequest(0,2);
        model.addAttribute("reservations",reservationRepositore.findByDataIsBetween(LocalDate.now()
                ,LocalDate.now().plusWeeks(1),pageRequest).getContent());
        model.addAttribute("reservationForm",new ReservationForm());

        return "index";
    }

    @PostMapping("/")
    public String index(@ModelAttribute("reservationForm") @Valid ReservationForm form, BindingResult result,Model model){
        if (result.hasErrors()){
           return "index";

        }else if (reservationRepositore.existsByDataEquals(form.getFormateddate())){
            model.addAttribute("error","Ten dzien jest zajety");
            return "index";
        }


        reservationRepositore.save(new ReservationModel(form));


        return "index";

    }
//    @GetMapping("/test")
//    @ResponseBody
//    public String test(){
////List<ReservationModel> reservationModel =reservationRepositore.findByName("asia");
//
////        return reservationModel.stream()
////                .map(s ->s.toString())
////                .collect(Collectors.joining());
//       List <ReservationModel> reservationModel = reservationRepositore.findByDataAfter(LocalDate.of(2017,01,12));
//        return reservationModel.stream()
//              .map(s ->s.toString())
//               .collect(Collectors.joining(","));
//    }
}
