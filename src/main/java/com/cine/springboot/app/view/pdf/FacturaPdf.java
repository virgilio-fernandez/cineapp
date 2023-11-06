package com.cine.springboot.app.view.pdf;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.cine.springboot.app.model.entity.DetalleVenta;
import com.cine.springboot.app.model.entity.Factura;
import com.cine.springboot.app.model.entity.Venta;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.LineSeparator;

@Component("ventas/detalleVenta")
public class FacturaPdf extends AbstractPdfView{

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		
		
		Venta venta = (Venta) model.get("venta");
		Factura factura= (Factura) model.get("factura");
		String numero=(String) model.get("numero");
		
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat hourFormat = new SimpleDateFormat("HH:mm");
		
		PdfPCell cell=null;

		PdfPTable  tabla = new PdfPTable(1);
		tabla.setSpacingAfter(20);
		cell = new PdfPCell(new Phrase(factura.getCine().getNombre()));
		cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cell.setBorder(Rectangle.NO_BORDER);
		tabla.addCell(cell);
		
		cell = new PdfPCell(new Phrase(factura.getCine().getDireccion()));
		cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cell.setBorder(Rectangle.NO_BORDER);
		tabla.addCell(cell);
		
		cell = new PdfPCell(new Phrase(factura.getCine().getTelefono()));
		cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cell.setBorder(Rectangle.NO_BORDER);
		tabla.addCell(cell);
		
		cell = new PdfPCell(new Phrase(factura.getCine().getEmail()));
		cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cell.setBorder(Rectangle.NO_BORDER);
		tabla.addCell(cell);

		
		
		PdfPTable  tabla1 = new PdfPTable(1);
		tabla1.setSpacingAfter(20);
		cell = new PdfPCell(new Phrase("NIT: "+factura.getCine().getNit()));
		cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cell.setBorder(Rectangle.NO_BORDER);
		tabla1.addCell(cell);
		
		
		cell = new PdfPCell(new Phrase("FACTURA N: "+factura.getNumero()));
		cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cell.setBorder(Rectangle.NO_BORDER);
		tabla1.addCell(cell);
		
		cell = new PdfPCell(new Phrase("AUTORIZACION N: "+factura.getDosificacion().getnAutorizacion()));
		cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cell.setBorder(Rectangle.NO_BORDER);
		tabla1.addCell(cell);
		
		
		PdfPTable  tabla2 = new PdfPTable(1);
		tabla2.setSpacingBefore(20);
		tabla2.setSpacingAfter(20);
		
		cell = new PdfPCell(new Phrase("Fecha: "+dateFormat.format(factura.getFecha())+" - Hora: "+hourFormat.format(factura.getFecha())));
		cell.setBorder(Rectangle.NO_BORDER);
		tabla2.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Nit/CI: "+ venta.getCliente().getNit()));
		cell.setBorder(Rectangle.NO_BORDER);
		tabla2.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Señor(a): "+venta.getCliente().getNombre()+" "+venta.getCliente().getApellido1()+" "+venta.getCliente().getApellido2()));
		cell.setBorder(Rectangle.NO_BORDER);
		tabla2.addCell(cell);

		
		
		
		PdfPTable  tabla3 = new PdfPTable(4);
		tabla3.setSpacingAfter(20);
		tabla3.addCell("Detalle");
		tabla3.addCell("Cant");
		tabla3.addCell("P. Unitario");
		tabla3.addCell("Subtotal");
		
		DecimalFormat formato1 = new DecimalFormat("#.00");
		PdfPCell c1=null;PdfPCell c2=null;PdfPCell c3=null;PdfPCell c4=null;
		
		for(DetalleVenta detalle: venta.getDetalleVenta()) {
			c1 = new PdfPCell(new Phrase(detalle.getHorario().getPeliculaTipo().getPelicula().getTitulo()+" "
					   +detalle.getHorario().getPeliculaTipo().getTipo().getNombre()+" "
					   +detalle.getHorario().getPeliculaTipo().getTipo().getAudio()));
			c1.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			tabla3.addCell(c1);
			
			c2 = new PdfPCell(new Phrase(Integer.toString(detalle.getCantidad())));
			c2.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
			tabla3.addCell(c2);
			
			c3 = new PdfPCell(new Phrase(formato1.format(detalle.getpUnit())));
			c3.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
			tabla3.addCell(c3);
			
			c4 = new PdfPCell(new Phrase(formato1.format(detalle.getSubtotal())));
			c4.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
			tabla3.addCell(c4);
			
			
			
		}
		cell = new PdfPCell(new Phrase("Total Bs: "));
		cell.setColspan(3);
		cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
		tabla3.addCell(cell);
		
		cell = new PdfPCell(new Phrase(formato1.format(venta.getTotal())));
		cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
		tabla3.addCell(cell);
		
		
		cell = new PdfPCell(new Phrase("Son: "+numero+" Bolivianos"));
		cell.setColspan(4);
		cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
		tabla3.addCell(cell);
		
		
		
		
		
		
		document.add(tabla);
		
		LineSeparator ls = new LineSeparator();
		
		document.add(new Chunk(ls));

		document.add(tabla1);
		document.add(new Chunk(ls));
		
		document.add(tabla2);
		document.add(tabla3);
		
		
		
	}
	
}
