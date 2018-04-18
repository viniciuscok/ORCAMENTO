$(function(){
	var decimal = $('.js-decimal');
	decimal.maskMoney({ decimal: ',', thousands: '.'});
	
	var plain = $('.js-plain');
	plain.maskMoney({ precision: 0, thousands: '.'});
	
	var inputTelefone = $('.js-telefone');
	var mascaraFixoCelular = function (val) {
		  return val.replace(/\D/g, '').length === 11 ? '(00) 00000-0000' : '(00) 0000-00009';
		},
		options = {
		  onKeyPress: function(val, e, field, options) {
		      field.mask(mascaraFixoCelular.apply({}, arguments), options);
		    }
		};

	inputTelefone.mask(mascaraFixoCelular, options);
	
	var formatacaoPlaca =  $('.js-placa');
	formatacaoPlaca.inputmask('AAA-9999');
	
	
	
	//formatacaoPlaca.inputmask('AAA-9999');
	
	
	//$("#nomePlaca").keyup(function() {
	//	var valor = $("#nomePlaca").val().replace(/[^a-z{4}]+/g,'');
	//	$("#nomePlaca").val(valor);
	//});
	//var le = $('.js-placa').keyup(function(e){
	//	if(e.key == 'a')
	//	{
	//		var t = $(this).keyup();
	//		console.log("teste");
	//		t.val(le.substring(0, t.length - 1));
			//t.val('');
			//le.val();
	//	}else
	//	{
	//		le.val();
	//	}
				//console.log("teste: "+e.keyCode);
		
	//});
	
	//var placaCarro = $('.js-placa');
	//var ehPlacaValida = function( placa ){
		//var er = /[a-z]{3}-?\d{4}/gim;
		//er.lastIndex = 0;
		//return er.test( placa );}
	//ehPlacaValida(placaCarro);
	
	//var placaCarro = $('.js-placa');
	//if(placaCarro == 'a')
	//	{
	//		placaCarro.value +='b';
			
	//	}
	//cosole.log(placaCarro.value);
	
	//declarar um evento global de key press para um atributo customizado
	// chamar função q recebe a string e 
	
	//$( document.body ).on('keypress', '*[mascara-placa]', function(e) {
	  //if(e.key=='a'){
		//  e.target.value += 'b';
		  //e.preventDefault();
	  //}
	//});
	
	
	//function formataPlaca(valorAtual){
		
	//}
	
	//var nomeInput = $('.js-nome-servico-input');
	//var options = {
	//	url:'http://localhost:8080/servicos/filtro?nome=',
	//	getValue: 'nome'
	//};
	
	//$('.js-nome-servico-input').easyAutocomplete(options);
	
});