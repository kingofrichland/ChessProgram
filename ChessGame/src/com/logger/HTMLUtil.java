package com.logger;

public class HTMLUtil {
	public static HTMLFile createHTMLFile(String filename){
		return new HTMLFile(filename);
	}

	public static String html(String text){
		return "<html>"+text+"</html>";
	}
	
	public static String h1(String text){
		return "<h1>"+text+"</h1>";
	}
	
	public static String hr(){
		return "<hr/>";
	}
	
	public static String td(String text){
		return "<td>"+text+"</td>";
	}

	public static String td(int colspan, String text){
		return "<td colspan="+colspan+">"+text+"</td>";
	}

	public static String th(String text){
		return "<th>"+text+"</th>";
	}
	
	public static String th(int colspan, String text){
		return "<th colspan="+colspan+">"+text+"</th>";
	}
	
	public static String tr(String text){
		return "<tr>"+text+"</tr>";
	}
	
	public static String tr_tt(String id, String parentid, String text){
		if (parentid==null || "".equals(parentid))
			return "<tr data-tt-id=\""+id+"\">"+text+"</tr>";
		return "<tr data-tt-id=\""+id+"\" data-tt-parent-id=\""+parentid+"\">"+text+"</tr>";
	}
	
	public static String table_no_border(String text){
		return "<table>"+text+"</table>";
	}
	
	public static String table(String text){
		return "<table border=1>"+text+"</table>";
	}
	
	public static String table(String id, String text){
		return "<table border=1 id=\""+id+"\">"+text+"</table>";
	}
	
	public static String div(String id, String text){
		return "<div id=\""+id+"\">"+text+"</div>";
	}
	
	public static String body(String text){
		return "<body>"+text+"</body>";
	}
	
	public static String canvas(String canvasid){
		String s = "";
		s += "<div style=\"width:100%\">";
		s += "<div>";
		s += "	<canvas id=\""+canvasid+"\" height=\"400\" width=\"700\"></canvas>";
		s += "</div>";
		s += "</div>";
		return s;
	}
	
	public static String canvas2(String canvasid){
		String s = "";
		s += "<div id=\""+canvasid+"\" style=\"min-width: 310px; height: 400px; margin: 0 auto\"></div>";
		return s;
	}
	
	

	
	public static String header(String title){
		
		String s = "";
		s += "<head> ";
		s += "<meta charset=\"utf-8\">";
		s += "<title>"+title+"</title>";
		s += "<link rel=\"stylesheet\" href=\"css/screen.css\" media=\"screen\" />";
		s += "<link rel=\"stylesheet\" href=\"css/jquery.treetable.css\" />";
		s += "<link rel=\"stylesheet\" href=\"css/jquery.treetable.theme.default.css\" />";
		s += "<script src=\"js/jquery.js\"></script>";
		s += "<script src=\"js/jquery-ui/jquery-ui.js\"></script>";
		s += "<script src=\"js/jquery.treetable.js\"></script>";
		s += "<script src=\"js/Chart.js\"></script>";
		s += "<script src=\"js/highcharts.js\"></script>";
		s += "<script src=\"js/exporting.js\"></script>";
		s += "</head>";
		return s;
	}

	public static String treetablescript(String tableid){
		String s = "";
		s += "<script>";
		s += "$(\"#"+tableid+"\").treetable({ expandable: true });";
		s += "</script>";
		return s;
	}
	
	public static String linechartscript(String canvasid, String[] label, double[] data){
		int MAX_NUM_OF_POINTS = 50;
		int SHOW_POINTS_INTERVAL = 1;
		
		if (label.length!=data.length) throw new IllegalArgumentException("line chart script label array size (i.e."+label.length+") does not equals data array size (i.e."+data.length+")");
		
		if (data.length > MAX_NUM_OF_POINTS){
			SHOW_POINTS_INTERVAL = (int) Math.ceil(data.length / MAX_NUM_OF_POINTS);
		}
		
		String labelstr = "";
		int i=0;
		for(String l : label){
			if (i%SHOW_POINTS_INTERVAL==0)
				labelstr += (("".equals(labelstr)) ? "" : ",") + "\"" + l + "\"";
			i++;
		}
		String datastr = "";
		int j=0;
		for(double d : data){
			if (j%SHOW_POINTS_INTERVAL==0)
				datastr += (("".equals(datastr)) ? "" : ",") + " " + d;
			j++;
		}
		String s = "";
		s += "<script>";
		s += "var lineChartData_"+canvasid+" = {";
		s += "		labels : ["+labelstr+"],";
		s += "		datasets : [";
		s += "			{";
		s += "				label: \"My First dataset\",";
		s += "				fillColor : \"rgba(220,220,220,0.2)\",";
		s += "				strokeColor : \"rgba(220,220,220,1)\",";
		s += "				pointColor : \"rgba(220,220,220,1)\",";
		s += "				pointStrokeColor : \"#fff\",";
		s += "				pointHighlightFill : \"#fff\",";
		s += "				pointHighlightStroke : \"rgba(220,220,220,1)\",";
		s += "				data : ["+datastr+"]";
		s += "			}";
		s += "		]";
		s += "	};";
		s += "	var ctx = document.getElementById(\""+canvasid+"\").getContext(\"2d\");";
		s += "	window.myLine = new Chart(ctx).Line(lineChartData_"+canvasid+", {";
		s += "		responsive: true";
		s += "	});";
		s += "</script>";
		
		return s;
	}

	public static String a(String link, String stockCode) {
		String s = "";
		s = "<a href=\""+link+"\">"+stockCode+"</a>";
		return s;
	}

	public static String li(String text) {
		String s = "";
		s = "<li>"+text+"</li>";
		return s;
	}
	
	
	
}
