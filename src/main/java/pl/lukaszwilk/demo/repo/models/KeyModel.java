package pl.lukaszwilk.demo.repo.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "licznik")
public class KeyModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String text;
    private int counter;
}