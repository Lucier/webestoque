package br.com.ajax.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.ajax.model.FormaDePagamento;
import br.com.ajax.repository.FormaDePagamentoRepository;
import br.com.ajax.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = FormaDePagamento.class)
public class FormaDePagamentoConverter implements Converter {
	
	private FormaDePagamentoRepository formaDePagamentoRepository;
	
	public FormaDePagamentoConverter() {
		formaDePagamentoRepository = CDIServiceLocator.getBean(FormaDePagamentoRepository.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		FormaDePagamento retorno = null;
		
		if(value != null){
			Long id = new Long(value);
			retorno = formaDePagamentoRepository.buscarPorId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null){
			FormaDePagamento formaDePagamento = (FormaDePagamento) value;
			return formaDePagamento.getId() == null ? null : formaDePagamento.getId().toString();
		}
		
		return "";
	}

}
