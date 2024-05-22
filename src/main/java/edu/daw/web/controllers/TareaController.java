package edu.daw.web.controllers;

import edu.daw.persistence.entities.Tarea;
import edu.daw.services.TareaService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tareas")
public class TareaController {
    @Autowired
    private TareaService tareaService;

    @GetMapping
    public ResponseEntity<List<Tarea>> listAll() {

        return ResponseEntity.ok(this.tareaService.getTareas());
    }

    @GetMapping("/{idTarea}")
    public ResponseEntity<Tarea> findOne(@PathVariable int idTarea) {
        Optional<Tarea> tarea = this.tareaService.getTarea(idTarea);
        if (tarea.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tarea.get());
    }

    @PostMapping
    public ResponseEntity<Tarea> create(@RequestBody Tarea tarea) {
        return new ResponseEntity<Tarea>(this.tareaService.crearTarea(tarea), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Tarea> update(int idTarea, @RequestBody Tarea tarea) {
        if (idTarea == tarea.getId()) {
            if (this.tareaService.getTarea(idTarea).isPresent()) {
                return ResponseEntity.ok(this.tareaService.actualizarTarea(tarea));
            }
        }
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<Tarea> delete(int idTarea){
        if (this.tareaService.borrarTarea(idTarea)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
