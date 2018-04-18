$(function(){
	var nomeInput = $('.js-nome-servico-input');
	var htmlTemplateAutocomplete = $('#template-autocomplete-servico').html();
	var template = Handlebars.compile(htmlTemplateAutocomplete);
	//var autocomplete = Autocomplete();
	
	var formatarMoeda = function(valor) {
		numeral.language('pt-br');
		return numeral(valor).format('0,0.00');
	}
	var options = {
		url: function(nome){
			return 'http://localhost:8080/servicos?nome=' + nome;
		},
			
		getValue: 'nome',
		minCharNumber: 3,
		requestDelay: 300,
		ajaxSettings:{
			contentType: 'application/json'
		},
		template: {
			type: 'custom',
			method: function(nome, servico) {
				servico.valor = formatarMoeda(servico.valor);
				return template(servico);
			}.bind(this)
		},
		list:{
			onChooceEvent: function(){
				console.log('teste');
			}
		}
	};
	
	nomeInput.easyAutocomplete(options);
});