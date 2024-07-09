package tech.cognity.garage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import tech.cognity.garage.entity.Vehiculo;
import tech.cognity.garage.service.VehiculoService;

@RestController
@RequestMapping("/v1/vehiculos")
public class VehiculoController {
	@Autowired
	private VehiculoService service;
	
	@GetMapping()
	public ResponseEntity<List<Vehiculo>> findAll(
			@RequestParam(value = "marca", required = false, defaultValue = "") String marca,
			@RequestParam(value = "placa", required = false, defaultValue = "") String placa,
			@RequestParam(value = "propietario", required = false, defaultValue = "") String propietario,
			@RequestParam(value = "numeroPropietario", required = false, defaultValue = "") Integer numeroPropietario,
			@RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
			@RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize
			){
		Pageable page= PageRequest.of(pageNumber,pageSize);
		List<Vehiculo> vehiculos;
		if(marca==null) {
			vehiculos=service.findAll(page);
		}else {
			vehiculos=service.findByMarca(marca, page);
		}
		
		if(vehiculos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(vehiculos);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Vehiculo> findById(@PathVariable("id") int id){
		Vehiculo vehiculo = service.findById(id);
		if(vehiculo==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(vehiculo);			
	}
	
	@PostMapping()
	public ResponseEntity<Vehiculo> create(@RequestBody Vehiculo vehiculo){
		Vehiculo registro=service.save(vehiculo);
		return ResponseEntity.status(HttpStatus.CREATED).body(registro);
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Vehiculo> update(@PathVariable("id") int id, @RequestBody Vehiculo vehiculo){
		Vehiculo registro=service.update(vehiculo);
		if(registro==null) {
			return ResponseEntity.notFound().build();
		}		
		return ResponseEntity.ok(registro);	
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Vehiculo> delete(@PathVariable("id") int id){
		service.delete(id);
		return ResponseEntity.ok(null);
	}
}