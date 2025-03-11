package pe.edu.galaxy.training.java.sb.arq.layered.api_be_reclamos.mappers.base;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class GenericMapper {

	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public <T, D> D mapItem(T item, Class<D> cl) {
		return modelMapper().map(item, cl);
	}

	public <T, D> List<D> map(List<T> list, Class<D> cl) {
		return list.stream().map(item -> modelMapper().map(item, cl)).toList();
	}

}
