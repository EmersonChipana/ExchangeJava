package arquitectura.software.ExchangeJava.dto;

import java.math.BigDecimal;

public class QueryDto {
    private BigDecimal amount;
    private String from;
    private String to;

    public QueryDto() {
    }

    public QueryDto(BigDecimal amount, String from, String to) {
        this.amount = amount;
        this.from = from;
        this.to = to;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "QueryDto [amount=" + amount + ", from=" + from + ", to=" + to + "]";
    }

}
