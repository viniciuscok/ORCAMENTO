package br.com.pioneiro.orcamento.thymeleaf.dialect;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import br.com.pioneiro.orcamento.thymeleaf.processor.MsgErrorAttributeTagProcessor;
import br.com.pioneiro.orcamento.thymeleaf.processor.OrderElementTagProcessor;
import br.com.pioneiro.orcamento.thymeleaf.processor.PaginacaoElementTagProcessor;

@Component
public class TitoDialect extends AbstractProcessorDialect
{
		
	public TitoDialect() 
	{
		super("Pioneiro Orcamento", "tito", StandardDialect.PROCESSOR_PRECEDENCE);
	}

	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) 
	{
		final Set<IProcessor> processadores = new HashSet<>();
		processadores.add(new MsgErrorAttributeTagProcessor(dialectPrefix));
		processadores.add(new OrderElementTagProcessor(dialectPrefix));
		processadores.add(new PaginacaoElementTagProcessor(dialectPrefix));
		
		return processadores;
	}

}
