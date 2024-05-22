package edu.daw.persistence.crud;

import edu.daw.persistence.entities.Tarea;
import org.springframework.data.repository.CrudRepository;

public interface TareaCrudRepository extends CrudRepository<Tarea,Integer> {
}
