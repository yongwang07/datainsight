import javafx.util.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class PurchaseAnalytics {
    private final static String ORDER_FILE = "input/order_products.csv";
    private static ProductDepartmentMapper productMapper = ProductDepartmentMapper.instance();
    private static Report report = new Report();

    public static void main(String args[]) {
        try (Stream<String> stream = Files.lines(Paths.get(ORDER_FILE))) {
            stream
                    .skip(1)
                    .map(line -> line.split(","))
                    .filter(Utils::isValidateOrder)
                    .map(columns -> new Pair<>(productMapper.getDepartmentId(columns[1].trim()), columns[3].trim()))
                    .filter(pair -> pair.getKey().length() > 0)
                    .forEach(pair -> report.reportOrder(pair));
            report.generateReport();
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
