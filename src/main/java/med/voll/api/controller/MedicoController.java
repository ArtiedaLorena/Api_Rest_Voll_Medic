package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    public void registrarMedico(@RequestBody @Valid DatosRegistroMedico datosRegistroMedico) {
        medicoRepository.save(new Medico(datosRegistroMedico));
    }
    @GetMapping
    public Page<DatosListadoMedico> listaMedicos(@PageableDefault(size = 3) Pageable paginacion){

        //return medicoRepository.findAll(paginacion).map(DatosListadoMedico::new);
        return medicoRepository.findbyActivoTrue(paginacion).map(DatosListadoMedico::new);
    }
    @PutMapping
    @Transactional
    public void actualizaMedico(@RequestBody @Valid DatosActualizarMedico datosActualizarMedico){
        Medico medico= medicoRepository.getReferenceById(datosActualizarMedico.id());
        medico.actualizarDatos(datosActualizarMedico);
    }
    //Delete logico por medio de un boolean
    @DeleteMapping("/{id}")
    @Transactional
    public void eliminarMedico(@PathVariable Long id){
        Medico medico= medicoRepository.getReferenceById(id);
        medico.desactivarMedico();
    }
    //Borrado a nivel de base de datos
    /*@DeleteMapping("/{id}")
    @Transactional
    public void eliminarMedico(@PathVariable Long id){
        Medico medico= medicoRepository.getReferenceById(id);
        medicoRepository.delete(medico);
    }*/

}
