/**
 * 
 */

function executaAjaxGet(urlChamada, divDestino, preExecute, posExecute) {
	$.ajax({
		type : "GET",
		url : urlChamada,
		beforeSend : preExecute,
		success : function(response) {
			// we have the response
			var x = document.getElementById(divDestino);
			x.innerHTML = response;

		},
		complete : posExecute,
		error : function(xhr) {
			alert("Um erro ocorreu: " + xhr.status + " - " + xhr.statusText);
		}
	});

}

function executaAjaxPost(divDestino, formOrigem, preExecute, posExecute) {
	$.ajax({
		type : 'POST',
		url : formOrigem.action,
		data : $(formOrigem).serialize(),
		beforeSend : preExecute,
		success : function(response) {
			document.getElementById(divDestino).innerHTML = response;
			$("#myModal").modal('hide');
		},
		complete : posExecute,
		error : function(xhr) {
			alert("Um erro ocorreu: " + xhr.status + " - " + xhr.statusText);
		}
	});
}
function menuEstadosOnClick() {
	executaAjaxGet('/data/estado', 'page-wrapper', '', executaAjaxGet(
			'/data/estado/obtertodos', 'listaEstados', '', ''));
}

function menuCidadesOnClick() {
	executaAjaxGet('/data/cidade', 'page-wrapper', '', function(e) {
		$(selecaoEstado).on({
			change : function(e) {
				carregaListaCidades()
			}
		});
		carregaListaCidades();
	});
}

function carregaListaCidades() {
	var sel = document.getElementById('selecaoEstado');
	executaAjaxGet("/data/cidadeporidestado/" + sel.value, "listaCidades", "",
			"");
}

function exibeModalEditaCidade(valor) {
	executaAjaxGet("/data/cidade/" + valor, "corpoModal", "", $("#myModal")
			.modal());
}

function exibeModalEditaEstado(valor) {
	executaAjaxGet("/data/estado/" + valor, "corpoModal", "", $("#myModal")
			.modal());
}

$body = $("body");

$(document).on({
	ajaxStart : function() {
		$body.addClass("loading");
	},
	ajaxStop : function() {
		$body.removeClass("loading");
	}
});