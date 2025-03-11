package pe.edu.galaxy.training.java.sb.arq.layered.api_be_reclamos.repository.jpa.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;


@NoRepositoryBean
public interface GenericRepository<T,ID>  extends JpaRepository<T, ID>{

	// TODO
	//default, static ...
	
	
}
