import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class BitcoinPrice {

    // Function to convert number into words (Indian format)
    static String[] units = {"", "One", "Two", "Three", "Four", "Five", "Six",
            "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen",
            "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};

    static String[] tens = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    public static String convertToWords(int num) {
        if (num == 0) return "Zero";

        if (num < 20) return units[num];

        if (num < 100)
            return tens[num / 10] + " " + units[num % 10];

        if (num < 1000)
            return units[num / 100] + " Hundred " + convertToWords(num % 100);

        if (num < 100000)
            return convertToWords(num / 1000) + " Thousand " + convertToWords(num % 1000);

        if (num < 10000000)
            return convertToWords(num / 100000) + " Lakh " + convertToWords(num % 100000);

        return convertToWords(num / 10000000) + " Crore " + convertToWords(num % 10000000);
    }

    public static void main(String[] args) {
        try {
            // API URL
            URL url = new URL("https://api.coingecko.com/api/v3/simple/price?ids=bitcoin&vs_currencies=usd,inr,eur");

            // Open connection
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            // Read response
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Parse JSON
            JSONObject json = new JSONObject(response.toString());
            int inr = json.getJSONObject("bitcoin").getInt("inr");

            System.out.println("Bitcoin price in INR: " + inr);
            System.out.println("In words: " + convertToWords(inr));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}