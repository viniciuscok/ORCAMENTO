package br.com.pioneiro.orcamento.thymeleaf.processor;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.spring5.util.FieldUtils;
import org.thymeleaf.templatemode.TemplateMode;

public class MsgErrorAttributeTagProcessor extends AbstractAttributeTagProcessor
{
	//Nome do atributo que será utilizado na página html
	private static final String NOME_ATRIBUTO = "msgerror";
	private static final int PRECEDENCIA = 1000;

/*
	 TemplateMode.html = indica em qual documento vamos usar esse atributo.
	 dialectPrefix = recebe o prefixo do diaalect que será 'tito'.
	 elementName = o terceiro intem do construtor informa que não estamos criando um elemento e sim um atributo por isso que ele está como null
	 prefixoElement= está como false ppq estamos criando um um atributo e não um elemento
	 nomeAtributo= o nome do atributo foi configurado como uma variável estática.
	 prefixAtributo = informo true para o prefixo do atributo.
	 precedence = Informa qual atributo vai ser executado primeiro em caso de precisar de uma sequencia, neste caso informamos 1000 e colocamos como uma variável estática.
	 
 */
	public MsgErrorAttributeTagProcessor(String dialectPrefix) {
		super(TemplateMode.HTML, dialectPrefix, null, false, NOME_ATRIBUTO, true, PRECEDENCIA, true);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName, 
			String attributeValue, IElementTagStructureHandler structureHandler)
	{
		System.out.println("entrou no doProcess");
		//verificando se existe erro no campo
		boolean temErro = FieldUtils.hasErrors(context, attributeValue);
		System.out.println("saiu do verificar " + temErro);
		
		if(temErro)
		{
			System.out.println("verificou se tem error: "+ temErro);
			//se existir error ele adiciona has-error na class da div.
			String classesExistentes = tag.getAttributeValue("class");
			structureHandler.setAttribute("class", classesExistentes + " has-error");
		}
		
	}

}
