$(document).ready(function() {
//alerta
const alertPlaceholder = document.getElementById('liveAlertPlaceholder')

	const alert = (message, type) => {
	  const wrapper = document.createElement('div')
	  wrapper.innerHTML = [
	    `<div class="alert alert-${type} alert-dismissible" role="alert">`,
	    `   <div>${message}</div>`,
	    '    <button type="button" class="close" data-dismiss="alert">&times;</button>',
	    '</div>'
	  ].join('')
	
	  alertPlaceholder.append(wrapper)
	}
// cambio
	$('#tablaC').DataTable({
		language: {
			processing: "Tratamiento en curso...",
			search: "Buscar&nbsp;:",
			lengthMenu: "Agrupar de _MENU_ items",
			info: "Mostrando del item _START_ al _END_ de un total de _TOTAL_ items",
			infoEmpty: "No existen datos.",
			infoFiltered: "(filtrado de _MAX_ elementos en total)",
			infoPostFix: "",
			loadingRecords: "Cargando...",
			zeroRecords: "No se encontraron datos con tu busqueda",
			emptyTable: "No hay datos disponibles en la tabla.",
			paginate: {
				first: "Primero",
				previous: "Anterior",
				next: "Siguiente",
				last: "Ultimo"
			},
			aria: {
				sortAscending: ": active para ordenar la columna en orden ascendente",
				sortDescending: ": active para ordenar la columna en orden descendente"
			}
		},

	});
	//nuevo cliente
	$('#nuevocliente').click(function() {
		$('#tableDiv').hide()
		$('#title').hide()
		$('#title2').show()
		$('#RegistroForm').show()
	})
	$('#nuevocliente2').click(function() {
		$('#tableDiv').hide()
		$('#title').hide()
		$('#title2').show()
		$('#RegistroForm').show()
	})
	$('#CancelBtn').click(function() {
		Cancel()
	})
	
	$('#selectCountry').change(function() {
		$.get("http://localhost:8080/ciudadesPais/" + $('#selectCountry').val(), function(data, status) {
			$('#selectCity').empty().trigger("change");
			$("#selectCity").select2({
				
				data: data,
				cache: false,
				placeholder: "Seleciona una opción"
			});
			
		});
	});

	
///Registrar
	$("#RegistrarBtn").click(function(){
		var nombre = $("#Nombre").val()
		var apellido = $("#Apellido").val()
		var email = $("#Email").val()
		var numero = $("#Numero").val()
		var direccion = $("#Direccion").val()
		var country = $("#selectCountry").val()
		var city = $("#selectCity").val()
		var store = $("#storeId").val()
		if(nombre=="" || apellido=="" || email=="" || numero=="" || direccion=="" || country==-1 || city==-1 ||store==-1){
			alert('Error, ingresa todos los datos en los campos correspondientes!!', 'danger')
		}else{
			$("#modalAdvertencia").modal("show")
		}
	})	
	
	
	$("#registrar").click(function(){
		
		
		$("#RegistroForm").submit()
	})
	

});

function filtro(){
	var tecla = event.key;
	if (['.','e',',','-','+','*','/','(',')'].includes(tecla))
	   event.preventDefault()
}

function Cancel(){
	    $("#Nombre").empty()
		$("#Apellido").empty()
		$("#Email").empty()
		$("#Numero").empty()
		$("#Direccion").empty()
		$("#selectCountry").val(-1)
		$("#selectCity").val(-1)
		$("#storeId").val(-1)
		$('#tableDiv').show()
		$('#title').show()
		$('#title2').hide()
		$('#RegistroForm').hide()
}

function detalles(filmId) {
	$.get("http://localhost:8080/detallesFilm/" + filmId, function(data, status) {
		console.log(data);
		console.log(status);

		let detalles = data.detalles;
		let categorias = data.categorias;
		let actores = data.actores;

		let categorias1 = "";

		$("#detalleNumPelicula").html(detalles.filmId);
		$("#detalleTitulo").html(detalles.title);
		$("#detallePrecio").html("$" + detalles.rentalRate);
		$("#detalleLanzamiento").html(detalles.releaseYear);
		$("#detalleDuracion").html(detalles.length + " min");

		categorias.forEach(c => categorias1 += c + ", ");
		categorias1 = categorias1.slice(0, categorias1.length - 2)
		$("#detalleCategorias").html(categorias1);
		$("#detalleLenguaje").html(detalles.language.name.trim());
		$("#detalleClasificacion").html(detalles.rating);
		$("#detalleDescripcion").html(detalles.description);

		let lista = document.getElementById("detalleActores");
		lista.innerHTML = "";

		for (let i = 0; i < actores.length; i++) {
			let li = document.createElement("li");
			li.innerHTML = actores[i];
			lista.appendChild(li);
		}

		$("#modalDetalles").modal("show");

	});
}


