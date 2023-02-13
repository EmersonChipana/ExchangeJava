package arquitectura.software.ExchangeJava.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestDto {
    private Date date;
    private QueryDto query;
    private BigDecimal result;
    private boolean success;

    public RequestDto() {
    }

    public RequestDto(Date date, QueryDto query, BigDecimal result, boolean success) {
        this.date = date;
        this.query = query;
        this.result = result;
        this.success = success;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public QueryDto getQuery() {
        return query;
    }

    public void setQuery(QueryDto query) {
        this.query = query;
    }

    public BigDecimal getResult() {
        return result;
    }

    public void setResult(BigDecimal result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "ResponseDto [date=" + date + ", query=" + query + ", result=" + result + ", success=" + success + "]";
    }


}
