package edu.daw.persistence.crud;

import edu.daw.persistence.entities.Tarea;
import edu.daw.persistence.entities.enums.Estado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TareaCrudRepository extends CrudRepository<Tarea,Integer> {
    Optional<List<Tarea>> findByEstado(Estado estado);

    @Query("select t from Tarea t where t.fechaVencimiento > :fechaExp")
    Optional<List<Tarea>> findByExpirables(@Param("fechaExp") LocalDate fechaExp);

    @Query("select t from Tarea t where t.fechaVencimiento < :fechaExp")
    Optional<List<Tarea>> findByNoExpirables(@Param("fechaExp") LocalDate fechaExp);
}
