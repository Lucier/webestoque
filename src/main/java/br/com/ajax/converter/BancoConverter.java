package br.com.ajax.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.ajax.model.Banco;
import br.com.ajax.repository.BancoRepository;
import br.com.ajax.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Banco.class)
public class BancoConverter implements Converter {

	private BancoRepository bancoRepository;

	public BancoConverter() {
		bancoRepository = CDIServiceLocator.getBean(BancoRepository.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Banco retorno = null;

		if (value != null) {
			Long id = new Long(value);
			retorno = bancoRepository.buscarPorId(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Banco banco = (Banco) value;
			return banco.getId() == null ? null : banco.getId().toString();
		}

		return "";
	}

}
