package softuni.exam.models.dto.forecastDtos;

import softuni.exam.models.enums.DaysOfWeek;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "forecast")
@XmlAccessorType(XmlAccessType.FIELD)
public class ForecastImportDTO {

    @Enumerated(EnumType.STRING)
    @XmlElement(name = "day_of_week")
    @NotNull
    private DaysOfWeek dayOfWeek;

    @XmlElement(name = "max_temperature")
    @DecimalMin(value = "-20")
    @DecimalMax(value = "60")
    private Double maxTemperature;

    @XmlElement(name = "min_temperature")
    @DecimalMin(value = "-50")
    @DecimalMax(value = "40")
    private Double minTemperature;

    @XmlElement
    private String sunrise;

    @XmlElement
    private String sunset;

    @XmlElement
    private Long city;

    public DaysOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DaysOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(Double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public Double getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(Double minTemperature) {
        this.minTemperature = minTemperature;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public Long getCity() {
        return city;
    }

    public void setCity(Long city) {
        this.city = city;
    }
}
