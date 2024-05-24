package edu.daw.services;

import edu.daw.persistence.entities.Tarea;
import edu.daw.persistence.entities.enums.Estado;
import edu.daw.persistence.repositories.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TareaService {
    @Autowired
    private TareaRepository tareaRepository;

    public List<Tarea> getTareas() {
        return this.tareaRepository.findAll();
    }
    //------------------------------------------------------------------------------------------------------------------

    public Optional<Tarea> getTarea(int idTarea) {
        return this.tareaRepository.findById(idTarea);
    }
    //------------------------------------------------------------------------------------------------------------------

    public Tarea crearTarea(Tarea tarea) {
        tarea.setEstado(Estado.PENDIENTE);
        tarea.setFechaCreacion(LocalDate.now());
        return this.tareaRepository.save(tarea);
    }
    //------------------------------------------------------------------------------------------------------------------

    public Tarea actualizarTarea(Tarea tarea) {
        return this.tareaRepository.save(tarea);
    }
    //------------------------------------------------------------------------------------------------------------------

    public boolean borrarTarea(int idTarea) {
        boolean result = false;
        if (this.tareaRepository.findById(idTarea).isPresent()) {
            this.tareaRepository.deleteById(idTarea);
        }
        return result;
    }
    //------------------------------------------------------------------------------------------------------------------

    public Optional<List<Tarea>> getTareaPendiente() {
        return this.tareaRepository.findByStatus(Estado.PENDIENTE);
    }
    //------------------------------------------------------------------------------------------------------------------

    public Optional<List<Tarea>> getTareaEnProceso() {
        return this.tareaRepository.findByStatus(Estado.EN_PROCESO);
    }
    //------------------------------------------------------------------------------------------------------------------

    public Optional<List<Tarea>> getTareaCompletada() {
        return this.tareaRepository.findByStatus(Estado.COMPLETADA);
    }
    //------------------------------------------------------------------------------------------------------------------

    public Optional<List<Tarea>> getTareaVencida() {
        return this.tareaRepository.findByVencidas(LocalDate.now());
    }
    //------------------------------------------------------------------------------------------------------------------

    public Optional<List<Tarea>> getTareaNoVencida() {
        return this.tareaRepository.findByNoVencidas(LocalDate.now());
    }
    //------------------------------------------------------------------------------------------------------------------

    public List<Tarea> getTareaByNombre(String coincidencia) {
        return this.tareaRepository.findByNombre(coincidencia);
    }
}
