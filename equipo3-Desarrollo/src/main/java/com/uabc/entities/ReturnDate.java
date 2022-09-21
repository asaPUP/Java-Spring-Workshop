package com.uabc.entities;

import java.util.Date;
import java.util.Locale;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

public class ReturnDate {

	
	@DateTimeFormat(pattern="yyyy/mm/dd")
	private java.sql.Timestamp return_date;
	
	@DateTimeFormat(pattern="MM/dd/yyyy")
	private java.sql.Timestamp rental_date;
	
	private String rentalDate;
	
	private int diasRenta;

	private String fechaVigencia;
	
	private Date dateR;
	
	private String fecha;
	
	private Date fechaDeVencimiento;
	
	private long diasAtraso;
	
	private String multa;
	
	private String fechaSeleccionada;
	
	private String iva;
	
	private String total;
	
	private String nombre;
	
	private String title;
	
	private Integer inventarioID;
	
	
	public Integer getInventarioID() {
		return inventarioID;
	}

	public void setInventarioID(Integer inventarioID) {
		this.inventarioID = inventarioID;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIva() {
		return iva;
	}

	public void setIva(String iva) {
		this.iva = iva;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getFechaSeleccionada() {
		return fechaSeleccionada;
	}

	public void setFechaSeleccionada(String fechaSeleccionada) {
		this.fechaSeleccionada = fechaSeleccionada;
	}

	public String getRentalDate() {
		return rentalDate;
	}

	public void setRentalDate(String rentalDate) {
		this.rentalDate = rentalDate;
	}

	public String getMulta() {
		return multa;
	}

	public void setMulta(String multa) {
		this.multa = multa;
	}

	public void setFechaVigencia(String fechaVigencia) {
		this.fechaVigencia = fechaVigencia;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public long getDiasAtraso() {
		return diasAtraso;
	}

	public void setDiasAtraso(long diasAtraso) {
		this.diasAtraso = diasAtraso;
	}

	public Date getDateR() {
		return dateR;
	}

	public void setDateR(Date dateR) {
		this.dateR = dateR;
	}

	public Date getFechaDeVencimiento() {
		return fechaDeVencimiento;
	}

	public void setFechaDeVencimiento(Date fechaDeVencimiento) {
		this.fechaDeVencimiento = fechaDeVencimiento;
	}

	public String getFechaVigencia() {
		return fechaVigencia;
	}

	public void setFecha7Vigencia(String fechaVigencia) {
		this.fechaVigencia = fechaVigencia;
	}

	public int getDiasRenta() {
		return diasRenta;
	}

	public void setDiasRenta(int diasRenta) {
		this.diasRenta = diasRenta;
	}

	public java.sql.Timestamp getReturn_date() {
		return return_date;
	}

	public void setReturn_date(java.sql.Timestamp return_date) {
		this.return_date = return_date;
	}

	public java.sql.Timestamp getRental_date() {
		return rental_date;
	}

	public void setRental_date(java.sql.Timestamp rental_date) {
		this.rental_date = rental_date;
	}

	
	
	


	
	
	
	
	
	public ReturnDate(String rentalDate, String fechaVigencia, long diasAtraso, String multa, String fechaSeleccionada,
			String nombre, String title) {
		super();
		this.rentalDate = rentalDate;
		this.fechaVigencia = fechaVigencia;
		this.diasAtraso = diasAtraso;
		this.multa = multa;
		this.fechaSeleccionada = fechaSeleccionada;
		this.nombre = nombre;
		this.title = title;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ReturnDate [return_date=");
		builder.append(return_date);
		builder.append(", rental_date=");
		builder.append(rental_date);
		builder.append(", diasRenta=");
		builder.append(diasRenta);
		builder.append(", fechaVigencia=");
		builder.append(fechaVigencia);
		builder.append(", dateR=");
		builder.append(dateR);
		builder.append(", fecha=");
		builder.append(fecha);
		builder.append(", fechaDeVencimiento=");
		builder.append(fechaDeVencimiento);
		builder.append(", diasAtraso=");
		builder.append(diasAtraso);
		builder.append("]");
		return builder.toString();
	}

	public ReturnDate() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void sumarDias() {
		
		String pattern = "dd MMMM yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		long time = rental_date.getTime();
		Date date = new Date(time);
		//Fecha de renta
		rentalDate = simpleDateFormat.format(date);
		
		//Fecha de devolucion
		Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, diasRenta);
        fechaDeVencimiento = c.getTime();
		fechaVigencia = simpleDateFormat.format(fechaDeVencimiento);
		
		
		
	}	
	
	
	
	public void comparaFecha() {
		
		
		
		String pattern = "dd MMMM yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        fechaSeleccionada = simpleDateFormat.format(dateR);
        // igual ala fecha actual no hay multa
       if(dateR.equals(fechaDeVencimiento))
       {
    	   //return_date.setTime(dateR.getTime());
    	   diasAtraso=0;
           multa= ""+diasAtraso*.50;
           total= ""+diasAtraso*.50;
           iva=""+diasAtraso*.50;
    	   
       }
      //despues de la fecha de vencimiento
       if(dateR.after(fechaDeVencimiento))
       {
    	   
    	   
    	   SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH);
           long diff = dateR.getTime() - fechaDeVencimiento.getTime();
           TimeUnit time = TimeUnit.DAYS; 
           long diffrence = time.convert(diff, TimeUnit.MILLISECONDS);
           
           DecimalFormat df = new DecimalFormat("#.00");
           float num =(float) (diasAtraso*.50);
             num = (float) (num *.16);
           diasAtraso=diffrence+1;
           BigDecimal multaBd = new BigDecimal(diasAtraso*.50);
           multaBd.setScale(2, BigDecimal.ROUND_HALF_UP); // this does change bd
           multaBd = multaBd.setScale(2, BigDecimal.ROUND_HALF_UP);
           
           BigDecimal ivaBd = new BigDecimal((diasAtraso*.50)*.16);
           ivaBd.setScale(2, BigDecimal.ROUND_HALF_UP); // this does change bd
           ivaBd = ivaBd.setScale(2, BigDecimal.ROUND_HALF_UP);
           
           BigDecimal totalBd = new BigDecimal(((diasAtraso*.50) *.16)+diasAtraso*.50);
           totalBd.setScale(2, BigDecimal.ROUND_HALF_UP); // this does change bd
           totalBd = totalBd.setScale(2, BigDecimal.ROUND_HALF_UP);
           
           multa= multaBd.toString();
           iva= ivaBd.toString();
           total=totalBd.toString();
           
       }
       
       // antes de la fecha de vencimiento
       if(dateR.before(fechaDeVencimiento))
       {
    	   
           diasAtraso=0;
           multa= ""+diasAtraso*.50;
           total=""+ diasAtraso*.50;
           iva=""+diasAtraso*.50;
       }
       
		
	}

	
	
	
	
}
