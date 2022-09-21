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
	//calendar
	const alertPlaceholderCalendar = document.getElementById('liveAlertPlaceholderCalendar')
	const alertCalendar = (message, type) => {
	  const wrapper = document.createElement('div')
	  wrapper.innerHTML = [
	    `<div class="alert alert-${type} alert-dismissible" role="alert">`,
	    `   <div>${message}</div>`,
	    '    <button type="button" class="close" data-dismiss="alert">&times;</button>',
	    '</div>'
	  ].join('')
	  
	  alertPlaceholderCalendar.append(wrapper)
	}
	
	
	// buscar pelicula por inventario id
	$('#buscarPeli').click(function() {
		$('#tablaCalendar').empty()
		var inventoryID=$("#inventoryID").val();
		if(inventoryID=="")
	    {
	       alert('Error, ingresa un numero de pelicula para continuar', 'danger')
	                    
	    }else{
		
			 $.ajax({
		        type: 'GET',
		        url: 'http://localhost:8080/busqueda/'+ $('#inventoryID').val()  ,
		        contentType: JSON,
		        error: function(XMLHttpRequest, textStatus, errorThrown){
		        		alert('Error, no se puede devolver una pelicula que no esta rentada', 'danger')
		        		 $("#tablaDiv").hide()
		        		 $("#tabla2Div").hide()
		    		},
        		success: (item) => {
					$('#tablaInventario').empty()
		            const row = `<tr>
		                <td>${ item.nombre }</td>
		                <td>${ item.title }</td>
		                <td>${ item.rentalDate}</td>
		                <td>${ item.fechaVigencia}</td>
		            </tr>`;
		            $('#tablaInventario').append( row );
		            $('#fechaDevolucion').empty()
		            $("#tablaDiv").show()
		            $("#tabla2Div").hide()
        			}
    			});
			}	
		});
	//Calendario	
	$('#fechaDevolucion').change(function() {
		var inventoryID=$("#inventoryID").val();
		if(inventoryID=="")
	    {
	       alertCalendar('Ingresa un numero de pelicula para continuar', 'warning')
	                    
	    }else{
			$.ajax({
		        type: 'GET',
		        url: 'http://localhost:8080/calendario/'+ $('#inventoryID').val()+'/'+ $('#fechaDevolucion').val()  ,
		        contentType: JSON,
		        error: function(XMLHttpRequest, textStatus, errorThrown){
		        		alertCalendar('Error con el servidor', 'danger')
		        		 $("#tabla2Div").hide()
		    		},
	    		success: (item) => {
					if(typeof item.nombre==='undefined')
					{
						alertCalendar('Error, no puedes devolver antes de la fecha de renta', 'danger')
						 $("#tabla2Div").hide()
						
					}else{
						$('#tablaCalendar').empty()
		            const row = `<tr>
		                <td>${ item.nombre }</td>
		                <td>${ item.title }</td>
		                <td>${ item.diasAtraso}</td>
		                <td>$${ item.multa}</td>
		                <td>${ item.fechaSeleccionada}</td>
		            </tr>`;
		            $('#tablaCalendar').append( row );
		            $("#tabla2Div").show()
		            $("#subtotal").empty()
		            $("#IVA").empty()
		            $("#Total").empty()
		            $("#subtotal").append(item.multa)
		            $("#IVA").append(item.iva)
		            $("#Total").append(item.total)
					}
		          
	    			}
				});
			}
		 
				
		});
		
		//Registar devolucion	
		
	$("#registrar").click(function() {
		var inventoryID=$("#inventoryID").val();
		if(inventoryID=="")
	    {
	       alertModal('Ingresa un numero de pelicula para continuar', 'warning')
	                    
	    }else{
		registrar()
		 $.ajax({
	        type: 'GET',
	        url: 'http://localhost:8080/ticket/'+ $('#inventoryID').val()+'/'+ $('#fechaDevolucion').val()  ,
	        contentType: JSON,
	        error: function(XMLHttpRequest, textStatus, errorThrown){
	        		alert('Error, Algo malo paso  con el servidor amigo', 'danger')
	        		 
	        		 $('#tablaInventario').empty()
	        		 $('#tablaCalendar').empty()
	        		 $("#subtotal").empty()
			         $("#IVA").empty()
			         $("#Total").empty()
			         $("#inventoryID").empty()
	        		 $("#tabla2Div").hide()
	        		 $("#tablaDiv").hide()
	        		 $("#modalRegistro").modal("hide")
	    		},
    		success: function() {
				
	          		alert('Pelicula devuelta correctamente', 'success')
					
					$('#tablaInventario').empty()
	        		 $('#tablaCalendar').empty()
	        		 $("#subtotal").empty()
			         $("#IVA").empty()
			          $("#inventoryID").empty()
			         $("#Total").empty()
	        		 $("#tabla2Div").hide()
	        		 $("#tablaDiv").hide()
			 		$("#modalRegistro").modal("hide")
	          		
    			}
			});
			
			
		}
	});
	
	
	
	
});


function registrar()
{
	var inventoryID=$("#inventoryID").val()

	window.open("http://localhost:8080/pdf?inventarioId="+inventoryID+"&fechaSeleccionada="+$('#fechaDevolucion').val());
	
}










