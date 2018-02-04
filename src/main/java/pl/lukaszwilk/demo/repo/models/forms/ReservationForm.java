package pl.lukaszwilk.demo.repo.models.forms;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReservationForm {
    @Getter
    @Setter
    @NotBlank
    @Size(min = 3,max = 25)
    private String name;
    @Getter
    @Setter
    @NotBlank
    @Size(min = 3 ,max = 25)
    private String lastname;
    @Getter
    @Setter
    @NotBlank
    @Pattern(regexp = "2[0-9]{3}-[0-9][0-9]-[0-9][0-9]", message = "Poprawny format to rok miesiac dzien")
    private String data;
    @Getter
    @Setter
    @NotBlank
   @Size(min = 4,max = 35)
    private String adres;
    //nowe podejscie do czasu
    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public LocalDate getFormateddate(){

            return LocalDate.parse(data,format);

    }
}
