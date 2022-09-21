$(document).ready(function() {

	// alerta
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
	// alertaModal
	const alertPlaceholderModal = document.getElementById('liveAlertPlaceholderModal')

	const alertModal = (message, type) => {
	  const wrapper = document.createElement('div')
	  wrapper.innerHTML = [
	    `<div class="alert alert-${type} alert-dismissible" role="alert">`,
	    `   <div>${message}</div>`,
	    '    <button type="button" class="close" data-dismiss="alert">&times;</button>',
	    '</div>'
	  ].join('')
	
	  alertPlaceholderModal.append(wrapper)
	}
	// buscar pelicula por inventario id y id customer
	$('#btnBuscar').click(function() {
		if($("#numeroPelicula").val().length==0||$("#numeroCliente").val().length==0)
	    {
	       alert('Error, ingresa un numero de pelicula y numero de usuario para continuar', 'danger')
	                    
	    }else{
		
			 $.ajax({
		        type: 'GET',
		        url: 'http://localhost:8080/buscarpeli/'+ $("#numeroPelicula").val()+'/'+$("#numeroCliente").val() ,
		        contentType: JSON,
		        error: function(XMLHttpRequest, textStatus, errorThrown){
		        		alert('Error en el servidor', 'danger')
		        		$("#pago").empty()
		        		$("#numeroPelicula").empty()
		            	$("#numeroCliente").empty()
		        		$("#Subtotal").empty()
			         	$("#IVA").empty()
			        	$("#Total").empty()
		        		$('#tMostrar').empty()
		        		$("#tablaDiv").hide()
		    		},
        		success: (item) => {
				//verificar que no sea nulo
				if(item.film.title== "lost"){
						alert('Ese numero de pelicula no existe!', 'danger')
		        		$("#pago").empty()
		        		$("#numeroPelicula").empty()
		            	$("#numeroCliente").empty()
		        		$("#Subtotal").empty()
			         	$("#IVA").empty()
			        	$("#Total").empty()
		        		$('#tMostrar').empty()
		        		$("#tablaDiv").hide()
					}else{
						if(item.film.title== null || typeof item==='undefined'){
						alert('Esa pelicula esta rentada!', 'danger')
		        		$("#pago").empty()
		        		$("#numeroPelicula").empty()
		            	$("#numeroCliente").empty()
		        		$("#Subtotal").empty()
			         	$("#IVA").empty()
			        	$("#Total").empty()
		        		$('#tMostrar').empty()
		        		$("#tablaDiv").hide()
					}else{
						if(item.film.title=="Inactivo"){
							alert('Error, este cliente esta inactivo', 'danger')
			        		$("#pago").empty()
			        		$("#numeroPelicula").empty()
			            	$("#numeroCliente").empty()
			        		$("#Subtotal").empty()
				         	$("#IVA").empty()
				        	$("#Total").empty()
			        		$('#tMostrar').empty()
			        		$("#tablaDiv").hide()
						}else{
							$('#tMostrar').empty()
				            const row = `<tr>
				                <td>${ item.film.filmId }</td>
				                <td>${ item.film.title }</td>
				                <td>${ item.film.rentalRate}</td>
				            </tr>`;
				            $('#tMostrar').append( row );
				            $("#Subtotal").empty()
					        $("#IVA").empty()
					        $("#Total").empty()
				            $('#Subtotal').append( (item.film.rentalRate).toFixed(2) );
				            $('#IVA').append( (item.film.rentalRate*.16).toFixed(2) );
				            $('#Total').append( ((item.film.rentalRate*.16)+ item.film.rentalRate).toFixed(2));
				            $('#cambio').empty()
				            $('#cambio').append("0");
				            $("#tablaDiv").show()
						   }
						
					   }
					}
        		 }
    		});
			}	
		});
		
		//Registra la pelicula
		
	$('#bRegistrar').click(function() {
		var peliID=$("#numeroPelicula").val();
		var userID=$("#numeroCliente").val();
		var importe=$("#pago").val();
		if(peliID==""&&userID==""&&importe=="")
	    {
	       alert('Error, ingresa un numero de pelicula y numero de usuario para continuar', 'danger')
	                    
	    }else{
		var cambio = document.getElementById("cambio").innerHTML;
			if(cambio==="0"||cambio==="Saldo Insuficiente")
			{
				alert('Ingresa el importe de pago suficiente', 'warning')
			}else{
				$("#modalRegistro").modal("show")
				$('#registrar').click(function() {
				var total = document.getElementById("Total").innerHTML;
			 	pdfRental()
			 
				 $.ajax({
			        type: 'GET',
			        url: 'http://localhost:8080/registrar/'+ $("#numeroPelicula").val()+'/'+$("#numeroCliente").val()+'/'+total ,
			        contentType: JSON,
			        error: function(XMLHttpRequest, textStatus, errorThrown){
			        		alert('Error con el servidor', 'danger')
			        		$("#pago").empty()
			        		$("#numeroPelicula").val("")
			           	    $("#numeroCliente").val("")
			        		$("#Subtotal").empty()
				         	$("#IVA").empty()
				        	$("#Total").empty()
			        		$('#tMostrar').empty()
			        		$("#tablaDiv").hide()
			        		$("#pagoImporte").val("")
			        		$("#modalRegistro").modal("hide")
			    		},
					success: function() {
						alert('Se rento correctamente la pelicula', 'success')
					    $("#pago").empty()
						$("#numeroPelicula").val("")
			            $("#numeroCliente").val("")
						$("#Subtotal").empty()
				        $("#IVA").empty()
				        $("#Total").empty()
			        	$('#tMostrar').empty()
			        	$("#pagoImporte").val("")
			        	$("#tablaDiv").hide()
			        	$("#modalRegistro").modal("hide")
						}
					});
				
				});
			}
			
		}
	
	});
	
	//Cambio de la persona
	$("#pagoImporte").change(function(){
		var total = document.getElementById("Total").innerHTML;
		console.log(total)
		console.log($("#pagoImporte").val())
		var comma = total.split(',')[1];
  			total = total.split(',')[0];
  		console.log("Coma:"+comma );
		$.ajax({
		        type: 'GET',
		        url: 'http://localhost:8080/cambio/'+ total+'/'+$("#pagoImporte").val() ,
		        
		        error: function(XMLHttpRequest, textStatus, errorThrown){
		        		alert('Error', 'danger')
		        		$("#pago").empty()
		        		$('#cambio').empty()
		    		},
        		success: function(item){
					$('#cambio').empty()
		            $('#cambio').append( item);
		            
        			}
    			});
		
	});
});
	
	
function pdfRental()
{
	
	window.open("http://localhost:8080/pdfRental?peliId="+$("#numeroPelicula").val()+"&userid="+$("#numeroCliente").val()+"&efectivo="+$("#pagoImporte").val());
	
}


