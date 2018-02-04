package pl.lukaszwilk.demo.repo.models.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.lukaszwilk.demo.repo.models.ReservationModel;
import sun.util.resources.LocaleData;


import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepositore extends CrudRepository<ReservationModel, Integer > {
    //tutaj piszmey zapytania sql

//   List< ReservationModel> findByName (String name);
//   ReservationModel findByNameAndLastname(String name, String lastname);
//   List<ReservationModel> findByLastnameContaining(String text);
//   List<ReservationModel> findByIdGreaterThan(Integer number);
//   List<ReservationModel> findByDataAfter(LocalDate date);
    boolean existsByDataEquals(LocalDate data);
    Page<ReservationModel> findByDataIsBetween(LocalDate date, LocalDate date2, Pageable page);
    ReservationModel findByLastnameIgnoreCase(String name);
    ReservationModel findById(Integer id);

}
