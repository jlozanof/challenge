package pe.edu.galaxy.training.java.sb.arq.layered.api_be_reclamos.repository.jpa.core;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
//import jakarta.transaction.Transactional;
import pe.edu.galaxy.training.java.sb.arq.layered.api_be_reclamos.entity.core.ReclamoEntity;
import pe.edu.galaxy.training.java.sb.arq.layered.api_be_reclamos.repository.jpa.base.GenericRepository;

@Repository
public interface ReclamoRepository extends GenericRepository<ReclamoEntity, Long> {

	// Default
	List<ReclamoEntity> findByMotivoIgnoreCaseLikeAndTipoAndEstado(String motivo, Integer tipo, String estado);
	
	// JPQL(Java Persistence Query Language)
	@Query("select r from ReclamoEntity r where upper(r.motivo)  like upper(:motivo) and r.tipo=:tipo and r.estado='1'")
	List<ReclamoEntity> findByObjectLike(
			@Param("motivo") String motivo,
			@Param("tipo") Integer tipo);
	
	// SQL
	//@Transactional
	@Modifying
	@Query(nativeQuery = true,value= "UPDATE PRO_RECLAMO SET ESTADO='0' where RECLAMO_id=:id")
	void delete(@Param("id") Long id);
	
	// Projections/Agregates

	@Query("select r from ReclamoEntity r where r.cliente.id=:idCliente and r.estado='1'")	
	Optional<ReclamoEntity> buscarXIDCliente(@Param("idCliente") Long idCliente);
	
}
