/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.controller.util;

import com.gestion.coves.dominio.entities.Empresa;
import com.gestion.coves.dominio.entities.Factura;
import com.gestion.coves.dominio.entities.FacturaDetalle;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

public class ImprimirPdf {

    //private String FILE = "/WEB-INF/reporte/FirstPdf.pdf";
    private static final Font CATFONT = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL);
    private static final Font NEGRITA = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD);
    //private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.NORMAL);
    // private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.NORMAL);

    /**
     *
     * @param factura
     * @param empresa
     * @param listaFacturaDetalle
     * @return
     */
    public String crearFactura(Factura factura, Empresa empresa, java.util.List<FacturaDetalle> listaFacturaDetalle) {
        try {
            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            //String path = servletContext.getRealPath("/WEB-INF");
            String path1 = servletContext.getRealPath("/resources");
            File file = new File(path1 + "/reporte");
            file.mkdirs();
            String nombre = "/factura_venta_" + factura.getIdFactura() + ".pdf";
            File newFile2 = new File(path1 + "/reporte" + nombre);

            //File f1 = new File(FILE);
            Document document = new Document();
            document.setMargins(10, 10, 0,0);

            Rectangle tamanio = new Rectangle(165, 500);
            document.setPageSize(tamanio);
            PdfWriter.getInstance(document, new FileOutputStream(newFile2.getAbsolutePath()));
            document.open();
            addMetaData(document);
            addTitlePage(document, empresa, factura);
            addContent(document, listaFacturaDetalle);
            addFooter(document, factura);

            document.close();
            return nombre;
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void addFooter(Document document, Factura factura) throws DocumentException {

        Paragraph preface = new Paragraph();
        Paragraph parrafo = new Paragraph(" ", NEGRITA);
        parrafo.setAlignment(Element.ALIGN_CENTER);
        // We add one empty line
        preface.add(parrafo);
        // Lets write a big header
        preface.add(parrafo("-----------------------"));
        preface.add(parrafo("Subtotal: " + factura.getSubtotal().toString()));
        preface.add(parrafo("IVA: " + factura.getIva().toString()));
        preface.add(parrafo("Total: " + factura.getTotal().toString()));
        preface.add(parrafo("-----------------------"));
        preface.add(parrafo("Efectivo: " + factura.getEfectivo().toString()));
        preface.add(parrafo("Cambio: " + factura.getCambio().toString()));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss a");
        preface.add(parrafo("Fecha: " + sdf.format(factura.getFechaVencimiento())));
        preface.add(parrafo("Gracias por su compra !!! "));
        document.add(preface);
    }

    private static Paragraph parrafo(String cadena) {
        Paragraph parrafo = new Paragraph(cadena, CATFONT);
        parrafo.setAlignment(Element.ALIGN_CENTER);
        return parrafo;
    }

    private static Paragraph parrafo(String cadena, Font fuente) {
        Paragraph parrafo = new Paragraph(cadena, fuente);
        parrafo.setAlignment(Element.ALIGN_CENTER);
        return parrafo;
    }

    // iText allows to add metadata to the PDF which can be viewed in your Adobe
    // Reader
    // under File -> Properties
    private static void addMetaData(Document document) {
        document.addTitle("Factura");
        document.addSubject("Using iText");
        document.addKeywords("Bebes, Ropa, Factura");
        document.addAuthor("Sistema de Invetario COVES");
        document.addCreator("paulo.alexander12@gmail.com");
    }

    private static void addTitlePage(Document document, Empresa empresa, Factura factura)
            throws DocumentException {
        Paragraph preface = new Paragraph();
        // We add one empty line

        // Lets write a big header
        preface.add(parrafo(empresa.getRazonsocial(), NEGRITA));
        preface.add(parrafo("NIT: " + empresa.getIdentificacion().toString(), CATFONT));
        preface.add(parrafo("Tel: " + empresa.getDireccion(), CATFONT));
        preface.add(parrafo(empresa.getTelefono(), CATFONT));
        preface.add(parrafo("Factura Nro:" + factura.getNumeroFactura(), NEGRITA));
        preface.add(parrafo(" ", NEGRITA));
        preface.add(parrafo("Cliente :" + factura.getIdCliente().getNombres() +" "+ factura.getIdCliente().getApellidos(), CATFONT));
        preface.add(parrafo(factura.getIdCliente().getIdTipoIdentificacion().getDescripcion()+" :" + factura.getIdCliente().getIdentificacion(), CATFONT));
        preface.add(parrafo(" ", NEGRITA));
        document.add(preface);

    }

    private static void addContent(Document document, java.util.List<FacturaDetalle> listaFacturaDetalle) throws DocumentException {
        Paragraph preface = new Paragraph();
        for (FacturaDetalle facturaDetalle : listaFacturaDetalle) {
            // We add one empty line
            // Lets write a big header
            preface.add(parrafo(facturaDetalle.getIdProducto().getCodigo()
                    + " " + facturaDetalle.getIdProducto().getDescripcion()
                    + " x " + facturaDetalle.getCantidad()
                    + " $ " + facturaDetalle.getPrecioTotal(), CATFONT));
        }

        document.add(preface);

    }

//    private static void createTable()
//            throws BadElementException {
//        
//        Anchor anchor = new Anchor("First Chapter", catFont);
//        Chapter catPart = new Chapter(new Paragraph(anchor), 1);
//        Paragraph subPara = new Paragraph("Subcategory 1", catFont);
//        Section subCatPart = catPart.addSection(subPara);
//        subCatPart.add(new Paragraph("Hello"));
//        
//        PdfPTable table = new PdfPTable(3);
//
//        // t.setBorderColor(BaseColor.GRAY);
//        // t.setPadding(4);
//        // t.setSpacing(4);
//        // t.setBorderWidth(1);
//        PdfPCell c1 = new PdfPCell(new Phrase("Table Header 1"));
//        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//        table.addCell(c1);
//        
//        c1 = new PdfPCell(new Phrase("Table Header 2"));
//        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//        table.addCell(c1);
//        
//        c1 = new PdfPCell(new Phrase("Table Header 3"));
//        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//        table.addCell(c1);
//        table.setHeaderRows(1);
//        
//        table.addCell("1.0");
//        table.addCell("1.1");
//        table.addCell("1.2");
//        table.addCell("2.1");
//        table.addCell("2.2");
//        table.addCell("2.3");
//        
//        subCatPart.add(table);
//        
//    }
//    private static void createList(Section subCatPart) {
//        List list = new List(true, false, 10);
//        list.add(new ListItem("First point"));
//        list.add(new ListItem("Second point"));
//        list.add(new ListItem("Third point"));
//        subCatPart.add(list);
//    }
//    private static void addEmptyLine(Paragraph paragraph, int number) {
//        for (int i = 0; i < number; i++) {
//            paragraph.add(new Paragraph(" "));
//        }
//    }
}
