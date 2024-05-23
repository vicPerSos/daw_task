package edu.daw.persistence.repositories;

import edu.daw.persistence.crud.TareaCrudRepository;
import edu.daw.persistence.entities.Tarea;
import edu.daw.persistence.entities.enums.Estado;
import jakarta.persistence.EntityManager;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.parser.Entity;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class TareaRepository {
    @Autowired
    private TareaCrudRepository tareaCrudRepository;

    public List<Tarea> findAll() {
        return (List<Tarea>) this.tareaCrudRepository.findAll();
    }

    public Optional<Tarea> findById(int idTarea) {
        return this.tareaCrudRepository.findById(idTarea);
    }

    public Tarea save(Tarea tarea) {
        return this.tareaCrudRepository.save(tarea);
    }

    public void deleteById(int idTarea) {
        this.tareaCrudRepository.deleteById(idTarea);
    }

    public Optional<List<Tarea>> findByStatus(Estado estado) {
        return this.tareaCrudRepository.findByEstado(estado);
    }

    public Optional<List<Tarea>> findByVencidas(LocalDate fechaExp) {
        return this.tareaCrudRepository.findByExpirables(fechaExp);
    }

    public Optional<List<Tarea>> findByNoVencidas(LocalDate fechaExp) {
        return this.tareaCrudRepository.findByNoExpirables(fechaExp);
    }

}
