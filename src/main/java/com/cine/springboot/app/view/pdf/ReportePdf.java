package com.cine.springboot.app.view.pdf;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.cine.springboot.app.Utils.Reporte;
import com.cine.springboot.app.Utils.Total;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;




@Component("ventas/imprimirReporteGeneral")
public class ReportePdf extends AbstractPdfView{

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		List<Reporte> lista = ((List<Reporte>) model.get("lista"));
		Total totales = (Total) model.get("totales");
		String fecha1 = (String) model.get("f1");
		String fecha2 = (String) model.get("f2");
		
		DecimalFormat formato1 = new DecimalFormat("#.00");
		
		
		PdfPCell c1=null;
		Font negrita = new Font(Font.TIMES_ROMAN, 12, Font.BOLD);
		Font negritaG = new Font(Font.TIMES_ROMAN, 18, Font.BOLD);
		PdfPTable  tab = new PdfPTable(1);
		tab.setSpacingAfter(50);
		
		c1 = new PdfPCell(new Phrase("Reporte de Ventas Realizadas",negritaG));
		c1.setBorder(Rectangle.NO_BORDER);
		c1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		tab.addCell(c1);
		
		
		c1 = new PdfPCell(new Phrase(fecha1+"  al   "+fecha2,negrita));
		c1.setBorder(Rectangle.NO_BORDER);
		c1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		tab.addCell(c1);
		
		
		
		
		PdfPTable  tabla = new PdfPTable(8);
		
		
		document.setPageSize(PageSize.A4);
		tabla.setWidthPercentage(100);
		
		tabla.setWidths(new float[] {0.35f,1.7f,1,1,1,0.8f,1,0.8f});
		Font normal = new Font(Font.TIMES_ROMAN, 12);
	
		
		c1 = new PdfPCell(new Phrase("Nº"));
		c1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		tabla.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("Pelicula",negrita));
		c1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		tabla.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("Fecha-Hora",negrita));
		c1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		tabla.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("Cliente",negrita));
		c1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		tabla.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("Vendedor",negrita));
		c1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		tabla.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("Estado",negrita));
		c1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		tabla.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("Descuento",negrita));
		c1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		tabla.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("Subtotal",negrita));
		c1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		tabla.addCell(c1);
		String estado;
		if (lista.size()!=0) {
			for (int i = 0; i < lista.size(); i++) {
				
				c1 = new PdfPCell(new Phrase(Integer.toString(i+1)));
				c1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
				
				tabla.addCell(c1);
				
				c1 = new PdfPCell(new Phrase(lista.get(i).getPtitulo()+" / "+lista.get(i).getTnombre()+" / "+lista.get(i).getTaudio(),normal));
				c1.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
				tabla.addCell(c1);
				
				c1 = new PdfPCell(new Phrase(lista.get(i).getVfecha()+"   "+lista.get(i).getVhora(),normal));
				c1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER); 
				tabla.addCell(c1);
				
				c1 = new PdfPCell(new Phrase(lista.get(i).getCnombre()+" "+lista.get(i).getCapellido1()+" "+lista.get(i).getCapellido2(),normal));
				c1.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
				tabla.addCell(c1);
				
				
				c1 = new PdfPCell(new Phrase(lista.get(i).getUnombre()+" "+lista.get(i).getUapellido1()+" "+lista.get(i).getUapellido2()+" ("+lista.get(i).getUsername()+")",normal));
				c1.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
				tabla.addCell(c1);
				
				if(lista.get(i).getVestado().equals("1")) {
					estado="Pagada";
				}else {
					estado="Anulada";
				}
				
				c1 = new PdfPCell(new Phrase(estado,normal));
				c1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER); 
				tabla.addCell(c1);
				
				c1 = new PdfPCell(new Phrase(lista.get(i).getDnombre()+"  "+lista.get(i).getDporcentaje()+"%",normal));
				c1.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
				tabla.addCell(c1);
				
				c1 = new PdfPCell(new Phrase(formato1.format(Double.parseDouble(lista.get(i).getDvsubtotal())),normal));
				c1.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
				tabla.addCell(c1);
			}
			
			c1 = new PdfPCell(new Phrase("Total Bs: "));
			c1.setColspan(7);
			c1.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
			tabla.addCell(c1);
			
			c1 = new PdfPCell(new Phrase(formato1.format(Double.parseDouble(totales.getTotal())) ));
			c1.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
			tabla.addCell(c1);
			
		}
		
			
		
		document.add(tab);
		document.add(tabla);
		
	}

}
