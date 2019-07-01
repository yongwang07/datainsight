import javafx.util.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProductDepartmentMapper {

    private final static String PRODUCTS_FILE = "input/products.csv";

    private static Map<String, String> productDepartment;

    private static ProductDepartmentMapper instance;

    private ProductDepartmentMapper() {
        try (Stream<String> stream = Files.lines(Paths.get(PRODUCTS_FILE))) {
            productDepartment = stream
                    .skip(1)
                    .map(line -> line.split(","))
                    .filter(Utils::isValidateProduct)
                    .map(columns -> new Pair<>(columns[0].trim(), columns[3].trim()))
                    .collect(Collectors.toMap(Pair::getKey, Pair::getValue, (oldValue, newValue) -> oldValue));
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public static synchronized ProductDepartmentMapper instance() {
        if (instance == null) {
            synchronized (ProductDepartmentMapper.class) {
                if (instance == null) {
                    instance = new ProductDepartmentMapper();
                }
            }
        }
        return instance;
    }

    public String getDepartmentId(String productId) {
        return Optional.ofNullable(productDepartment.get(productId)).orElse("");
    }

}
