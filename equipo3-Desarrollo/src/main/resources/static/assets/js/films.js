$(document).ready(function(){
	
	$(".selection").select2({
		theme: "bootstrap-5",
		placeholder: "Seleciona una opción"
	});
	
	$(".multiple-selection").select2({
		theme: "bootstrap-5",
		placeholder: "Seleciona una o varias opciones"
	});
	
});

function crear_elemento(){
	
	$('#contenedor').append('<li><input name="nombre[]" type="text" placeholder="Nombre"><span>   </span><input name="apellido[]" type="text" placeholder="Apellido">' + '<a class="agregar" onclick="crear_elemento();">&plus;</a><a class="eliminar" style="background-color: red" onclick="eliminar_elemento(this);">&minus;</a></li>');

};

function eliminar_elemento(valor){
	
	valor.parentNode.parentNode.removeChild(valor.parentNode);
	
};
