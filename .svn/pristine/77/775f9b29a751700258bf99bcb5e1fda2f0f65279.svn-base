package ve.gob.dem.framework.grafico;

import java.awt.Color;
import java.awt.Paint;
import java.awt.Font;
import java.io.IOException;
import java.io.OutputStream;


import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.TextTitle;

import org.jfree.data.general.DefaultPieDataset;

import ve.gob.dem.framework.global.GenericAction;

public class AccionGraficoGenerico extends GenericAction{
	
	
	private JFreeChart crearDefaul(){
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("No definido", new Double(0));
		JFreeChart chart = ChartFactory.createPieChart(
		"No existe gráfico para este Tribunal", // chart title
		dataset, // data
		true, // include legend
		true, // tooltips
		false // urls
		);
		return chart;
	}

	public void pintaGraficoDefault(HttpServletResponse response) throws IOException{
		OutputStream output = null;
	    try{
	    	output = response.getOutputStream();
	    	response.setContentType("image/jpeg");
	    	ChartUtilities.writeBufferedImageAsPNG(output, crearDefaul().createBufferedImage(684, 426));
	    }catch (Exception e){
	    	log.error("Error creando el gráfico", e);
	    }finally{
	    	log.info("Cerrando el outPut");
	    	output.close();
	    }
	}	
	
    
    public static TextTitle setTitulo(String texto){
        Font f = new Font("Verdana", Font.BOLD, 15);
        Paint p = new Color(0, 0, 0);
        TextTitle titulo = new TextTitle(valString(texto), f);
        titulo.setPaint(p);
        return titulo;
      }
    
      public static TextTitle setTribunal(String texto){
        Font f = new Font("Verdana", Font.BOLD, 12);
        Paint p = new Color(55, 96, 146);
        TextTitle titulo = new TextTitle(valString(texto), f);
        titulo.setPaint(p);
        return titulo;
      }
      
      public static TextTitle setSubTitulo(String texto){
        Font f = new Font("Verdana", Font.PLAIN, 12);
        Paint p = new Color(0, 0, 0);
        TextTitle titulo = new TextTitle(valString(texto), f);
        titulo.setPaint(p);
        return titulo;
      }
      
      public static TextTitle setSubTituloBold(String texto){
        Font f = new Font("Verdana", Font.BOLD, 12);
        Paint p = new Color(0, 0, 0);
        TextTitle titulo = new TextTitle(valString(texto), f);
        titulo.setPaint(p);
        return titulo;
      }
      
      public static String valString(String str){
        if(str == null){
          return "";
        }
        return str;
      }
    
	  public void estandarizacionGrafica(JFreeChart chart, String path){
	    try{
		  chart.setBorderVisible(false);
		  chart.setBackgroundImageAlpha(Float.parseFloat("1"));
		  		  
	      PiePlot plot = (PiePlot) chart.getPlot();
	      plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
	      StandardPieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator("{0} = {1} ({2})");
	      plot.setLabelGenerator(gen);
	      plot.setNoDataMessage("No data available");
	      plot.setBackgroundPaint(Color.white);
	      plot.setBackgroundAlpha(new Float(1));
	      plot.setBackgroundImage(Imagen.getSingleton(path).getLoadImg());
	      plot.setCircular(true);
	      plot.setLabelGap(0.02);

		}catch (IOException e){
		  log.error("Error cargando la imagen del grafico", e);			    
		}
	}    
}
