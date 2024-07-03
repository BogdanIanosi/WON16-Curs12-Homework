import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Quote> quotes = new ArrayList<>();
        try (InputStream is = Main.class.getClassLoader().getResourceAsStream("quotes.txt");
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            int id = 1;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("~");
                if (parts.length == 2) {
                    quotes.add(new Quote(id++, parts[0], parts[1]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        QuoteService quoteService = new QuoteService(quotes);


        System.out.println("All quotes:");
        quoteService.getAllQuotes().forEach(System.out::println);

        System.out.println("\nQuotes by Yoda:");
        quoteService.getQuotesForAuthor("Yoda").forEach(System.out::println);

        System.out.println("\nAll authors:");
        quoteService.getAuthors().forEach(System.out::println);

        quoteService.setFavourite(1);
        System.out.println("\nFavourite quotes:");
        quoteService.getFavourites().forEach(System.out::println);

        System.out.println("\nRandom quote:");
        System.out.println(quoteService.getRandomQuote());
    }
}
