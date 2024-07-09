package tech.cognity.garage.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.cognity.garage.entity.Vehiculo;

@Repository
public interface VehiculoRepository  extends JpaRepository<Vehiculo, Integer>{
	List<Vehiculo> findByMarcaContaining(String marca, Pageable page);

}
