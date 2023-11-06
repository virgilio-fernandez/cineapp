package com.cine.springboot.app.view.pdf;


import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.cine.springboot.app.model.entity.Boleto;

import com.cine.springboot.app.model.entity.Usuario;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component("ventas/imprimirBoletos")
public class BoletoPdf extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		List<Boleto> lista = (List<Boleto>) model.get("lista");

		Usuario user = (Usuario) model.get("user");

		/*
		 * Rectangle pagina = new Rectangle(150f,200f); document.setPageSize(pagina);
		 * document.setMargins(2, 2, 2, 2);
		 */
		Rectangle one = new Rectangle(150, 180);

		document.setPageSize(one);
		document.setMargins(2, 2, 10, 2);

		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat hourFormat = new SimpleDateFormat("HH:mm");

		document.open();

		DecimalFormat formato1 = new DecimalFormat("#.00");
		Font negrita = new Font(Font.TIMES_ROMAN, 14, Font.BOLD);
		Font normal = new Font(Font.TIMES_ROMAN, 12);

		for (int i = 0; i < lista.size(); i++) {
		

				PdfPTable tabla = new PdfPTable(1);
				tabla.setWidthPercentage(100);
				PdfPCell c1 = null;

				c1 = new PdfPCell(new Phrase(lista.get(i).getHorario().getPeliculaTipo().getPelicula().getTitulo()
						+ "  " + lista.get(i).getHorario().getPeliculaTipo().getTipo().getNombre() + "  "
						+ lista.get(i).getHorario().getPeliculaTipo().getTipo().getAudio(),negrita));
				c1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
				c1.setBorder(Rectangle.NO_BORDER);
				tabla.addCell(c1);

				c1 = new PdfPCell(new Phrase(dateFormat.format(lista.get(i).getHorario().getFecha()),negrita));
				c1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
				c1.setBorder(Rectangle.NO_BORDER);
				tabla.addCell(c1);

				c1 = new PdfPCell(new Phrase(hourFormat.format(lista.get(i).getHorario().getHora()) + "      "
						+ lista.get(i).getHorario().getSala().getNombre(),negrita));
				c1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
				c1.setBorder(Rectangle.NO_BORDER);
				tabla.addCell(c1);

				c1 = new PdfPCell(new Phrase("Bs " + formato1.format(lista.get(i).getHorario().getPrecio()),negrita));
				c1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
				c1.setBorder(Rectangle.NO_BORDER);
				tabla.addCell(c1);

				
				tabla.setSpacingAfter(20);

				PdfPTable tabla2 = new PdfPTable(1);
				tabla2.setSpacingAfter(30);
				tabla2.setWidthPercentage(100);

				c1 = new PdfPCell(new Phrase("usuario: " + user.getUsername(),normal));
				c1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
				c1.setBorder(Rectangle.NO_BORDER);
				tabla2.addCell(c1);
				
				c1 = new PdfPCell(new Phrase("Tarija, " +dateFormat.format(date) + " " + hourFormat.format(date),normal));
				c1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
				c1.setBorder(Rectangle.NO_BORDER);
				tabla2.addCell(c1);

			
				document.add(tabla);
				document.add(tabla2);

		

		}
		
		document.close();
	}

}