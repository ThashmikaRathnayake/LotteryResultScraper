import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
public class LotteryScraper {
    public void scrapeAndSave(String filePath) {
        try{
            File input = new File("mahajana.html");
            Document doc = Jsoup.parse(input, "UTF-8");

            Element resultSection = doc.selectFirst("div.lbox div.lresult");

            if (resultSection != null) {
                Element drawNo = resultSection.selectFirst("h1 b");
                String drawNoText = (drawNo != null ? drawNo.text() : "N/A");
                Element drawDate = resultSection.selectFirst("p:contains(Date:)");
                String cleanDate = (drawDate != null) ? drawDate.text().replace("Date:", "").trim() : "";
                Elements results = resultSection.select("ol.B li");

                System.out.println("Mahajana Sampatha Lottery Results: " + cleanDate);
                System.out.println("Draw No: "+ drawNoText);
                System.out.println("Winning Results: ");
                String winningLetter = "";
                int[] winningNumbers = new int[6];
                int numIndex = 0;

                for(int i=0; i<results.size(); i++){
                    String value = results.get(i).text();
                    if (!value.equalsIgnoreCase("More")) {
                        System.out.print(value + " ");
                        if (results.get(i).hasClass("Letter")) {
                            winningLetter = value;
                        } else if (numIndex < 6) {
                            winningNumbers[numIndex] = Integer.parseInt(value);
                            numIndex++;
                        }
                    }
                }
                System.out.println();

                DatabaseManager.insertIntoDatabase(drawNoText, cleanDate, winningLetter, winningNumbers);
            } else {
                System.out.println("Couldn't fetch Lottery results");
            }
        } catch (Exception e) {
            System.out.println("Error fetching results: "+ e.getMessage());
        }
    }
}