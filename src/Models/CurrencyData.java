package Models;

public class CurrencyData {
    private final String name;
    private final String code;
    private final double bid;
    private final double ask;

    public CurrencyData(String name, String code, double bid, double ask) {
        this.name = name;
        this.code = code;
        this.bid = bid;
        this.ask = ask;
    }


    public String getName() { return name; }
    public String getCode() { return code; }
    public double getBid() { return bid; }
    public double getAsk() { return ask; }
}
