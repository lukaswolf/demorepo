package pl.lukaszwilk.demo.repo.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.lukaszwilk.demo.repo.models.forms.ReservationForm;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
//nazwa klasy powinna nazywac sie tak jak nzawa tebli
@Table(name = "reservation")
@NoArgsConstructor
@XmlRootElement(name = "reservation")
@XmlAccessorType(XmlAccessType.NONE)
public class ReservationModel {
    //hibernate bd wiedzial ze id odpoiwada polu
    @Id
    //po to zeby sql nie krzyczal ze nie ma uzupelnionego
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlAttribute
    private int id;
   // @Column(name = "name1")
    @XmlElement
    private String name;
    @XmlElement
    private String lastname;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @XmlElement
    private LocalDate data;
    @XmlElement
    private String adres;
    public ReservationModel (ReservationForm form){
        name =form.getName();
        lastname=form.getLastname();
        data=form.getFormateddate();
        adres=form.getAdres();

    }

}
