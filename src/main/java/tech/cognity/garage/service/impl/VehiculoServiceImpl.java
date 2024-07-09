package tech.cognity.garage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tech.cognity.garage.entity.Vehiculo;
import tech.cognity.garage.repository.VehiculoRepository;
import tech.cognity.garage.service.VehiculoService;

@Service
public class VehiculoServiceImpl implements VehiculoService{
	@Autowired
	private VehiculoRepository repository;

	@Override
	@Transactional(readOnly=true)
	public List<Vehiculo> findAll(Pageable page) {
		try {
			return repository.findAll(page).toList();
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	@Transactional(readOnly=true)
	public List<Vehiculo> findByMarca(String marca, Pageable page) {
		try {
			return repository.findByMarcaContaining(marca, page);
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	@Transactional(readOnly=true)
	public Vehiculo findById(int id) {
		try {
			Vehiculo registro=repository.findById(id).orElseThrow();
			return registro;
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	public Vehiculo save(Vehiculo vehiculo) {
		try {
			vehiculo.setActivo(true);
			Vehiculo registro=repository.save(vehiculo);
			return registro;
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	public Vehiculo update(Vehiculo vehiculo) {
		try {
			Vehiculo registro = repository.findById(vehiculo.getId()).orElseThrow();
			registro.setMarca(vehiculo.getMarca());
			registro.setPlaca(vehiculo.getPlaca());
			registro.setPropietario(vehiculo.getPropietario());
			registro.setNumeroPropietario(vehiculo.getNumeroPropietario());
			repository.save(registro);
			return registro;
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	public void delete(int id) {
		try {
			Vehiculo registro=repository.findById(id).orElseThrow();
			repository.delete(registro);
		}catch(Exception e) {
			
		}
		
	}	

}
