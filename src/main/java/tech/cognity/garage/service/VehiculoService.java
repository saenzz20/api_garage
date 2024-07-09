package tech.cognity.garage.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import tech.cognity.garage.entity.Vehiculo;

public interface VehiculoService {
	public List<Vehiculo> findAll(Pageable page);
	public List<Vehiculo> findByMarca(String marca, Pageable page);
	public Vehiculo findById(int id);
	public Vehiculo save(Vehiculo vehiculo);
	public Vehiculo update(Vehiculo vehiculo);
	public void delete(int id);
}
