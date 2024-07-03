import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class QuoteService {
    private List<Quote> quotes;

    public QuoteService(List<Quote> quotes) {
        this.quotes = quotes;
    }

    public List<String> getAllQuotes() {
        return quotes.stream()
                .map(Quote::toString)
                .collect(Collectors.toList());
    }

    public List<Quote> getQuotesForAuthor(String author) {
        return quotes.stream()
                .filter(quote -> quote.getAuthor().equals(author))
                .collect(Collectors.toList());
    }

    public List<String> getAuthors() {
        return quotes.stream()
                .map(Quote::getAuthor)
                .distinct()
                .collect(Collectors.toList());
    }

    public void setFavourite(int id) {
        for (Quote quote : quotes) {
            if (quote.getId() == id) {
                quote.setFavourite(true);
                break;
            }
        }
    }

    public List<Quote> getFavourites() {
        return quotes.stream()
                .filter(Quote::isFavourite)
                .collect(Collectors.toList());
    }

    public String getRandomQuote() {
        Random random = new Random();
        int id = random.nextInt(quotes.size()) + 1;
        for (Quote quote : quotes) {
            if (quote.getId() == id) {
                return quote.toString();
            }
        }
        return null;
    }
}

