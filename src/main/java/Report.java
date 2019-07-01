import javafx.util.Pair;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Report {
    private static final String REPORT_FILE = "output/report.csv";

    private Map<Long, OrderStatistics> results = new TreeMap<>();

    public void reportOrder(Pair<String, String> pair) {
        String departmentId = pair.getKey();
        boolean isReordered = pair.getValue().equals("1");
        results.compute(Long.parseLong(departmentId), (k, v) -> v != null ? v : new OrderStatistics(departmentId))
                .orderNumber(isReordered);
    }

    public void generateReport() {
        try {
            Files.write(
                    Paths.get(REPORT_FILE),
                    Stream.concat(
                            Stream.of(OrderStatistics.HEADER),
                            results.values().stream().map(item -> item.toString())
                    ).collect(Collectors.joining("\n")).getBytes());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
