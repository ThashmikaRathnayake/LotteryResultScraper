public class Main {
    public static void main(String[] args) {
        String filePath = "mahajana.html";
        LotteryScraper scraper = new LotteryScraper();
        scraper.scrapeAndSave(filePath);
    }
}
