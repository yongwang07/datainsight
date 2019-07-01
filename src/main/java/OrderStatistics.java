public class OrderStatistics {
    public final static String HEADER = "department_id,number_of_orders,number_of_first_orders,percentage";
    private String departmentId;
    private long orderNumber;
    private long firstOrderNumber;
    private double percentage;

    public OrderStatistics(String departmentId) {
        this.departmentId = departmentId;
        this.orderNumber = 0;
        this.firstOrderNumber = 0;
    }

    public void orderNumber(boolean isReordered) {
        this.orderNumber += 1;
        this.firstOrderNumber += isReordered ? 0 : 1;
        this.percentage = (double)this.firstOrderNumber / this.orderNumber;
    }

    @Override
    public String toString() {
        return String.format("%s,%d,%d,%.2f", departmentId, orderNumber, firstOrderNumber, percentage);
    }
}
