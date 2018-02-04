package pl.lukaszwilk.demo.repo.models.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.lukaszwilk.demo.repo.models.KeyModel;

import javax.persistence.criteria.CriteriaBuilder;

public interface KeyRepository extends CrudRepository<KeyModel ,Integer> {
    boolean existsByText(String key);
    KeyModel findByText(String key);
}
