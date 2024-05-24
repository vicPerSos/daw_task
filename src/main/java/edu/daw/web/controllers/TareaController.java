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

    @GetMapping("/all")
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

    @GetMapping("/pendientes")
    public ResponseEntity<Optional<List<Tarea>>> listPendientes() {

        return ResponseEntity.ok(this.tareaService.getTareaPendiente());
    }

    @GetMapping("/finalizados")
    public ResponseEntity<Optional<List<Tarea>>> listFinalizados() {

        return ResponseEntity.ok(this.tareaService.getTareaCompletada());
    }

    @GetMapping("/enProceso")
    public ResponseEntity<Optional<List<Tarea>>> listEnProceso() {

        return ResponseEntity.ok(this.tareaService.getTareaEnProceso());
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
    @DeleteMapping
    public ResponseEntity<Tarea> delete(int idTarea){
        if (this.tareaService.borrarTarea(idTarea)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/caducadas")
    public ResponseEntity<Optional<List<Tarea>>> listCaducada(){
        return ResponseEntity.ok(this.tareaService.getTareaVencida());
    }

    @GetMapping("/noCaducadas")
    public ResponseEntity<Optional<List<Tarea>>> listNoCaducada(){
        return ResponseEntity.ok(this.tareaService.getTareaNoVencida());
    }

}
