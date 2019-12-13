
package AppPack;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import java.util.ListIterator;
public class Scraper{

    /**
     * A class representing a web scraper for the website nutritionvalues.org
     * @author Mikolaj Bien
     */
  
    public Scraper(){
	
    }
    public ArrayList<String> queryDatabase(String item){
	System.out.println(item);
	String url = "https://www.nutritionvalue.org/";
	Connection.Response resp = null;
	Document respDoc = null;
	try{
	   resp= Jsoup.connect(url).method(Connection.Method.GET).execute();
	   respDoc = resp.parse();
	}catch(java.io.IOException e){
	    e.printStackTrace();
	}

	FormElement form = (FormElement)respDoc.selectFirst("body > table > tbody > tr:nth-child(3) > td > form");
	Element searchField = form.selectFirst("#food_query");
	searchField.val(item);
	//	System.out.println(searchField);
	
	Connection.Response results = null;
	Document resultsDoc = null;
	try{
	    results = form.submit().execute();
	    resultsDoc = results.parse();
	}catch(java.io.IOException e){
	    e.printStackTrace();
	}

	//	Element firstRowOfResult = resultsDoc.selectFirst("body > table > tbody > tr:nth-child(3) > td > table > tbody > tr:nth-child(2)");
	Element resultText = resultsDoc.selectFirst("td.results.left");
	Element linkToInfo = resultText.selectFirst("a");
	//System.out.println(linkToInfo);
	String Link = linkToInfo.absUrl("href");
	//System.out.println(Link);
	Connection.Response infoResp = null;
	Document infoDoc = null;
	try{
	    infoResp = Jsoup.connect(Link).method(Connection.Method.GET).execute();
	    infoDoc = infoResp.parse();
	}catch(java.io.IOException e){
	    e.printStackTrace();
	}

	Element table = infoDoc.selectFirst("table.center.zero.wide.fixed table.center.zero");
	ArrayList<String> Facts = new ArrayList<String>();	
	for (Element row : table.select("tr")){
	    for(Element td : row.select("td")){
		if ((td.text().equals("")) || (td.text().contains("•"))){//skip bars in block and entries with dot
		    continue;
		}
		else{
		    String s = td.text().trim();
		    
		    Facts.add(s);//stick text into a list
		    
		    if (td.text().contains("Sugar")){//match the sugar entry to the other nutrients
			Facts.add("N/A");
		    }
		}
	    }
	}
	//ArrayList<FoodFinder.Nutrient> nutList = new ArrayList<FoodFinder.Nutrient>;
	
	
	return Facts;
	
    }
}
