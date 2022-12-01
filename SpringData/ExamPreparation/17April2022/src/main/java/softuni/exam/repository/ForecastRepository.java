package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Forecast;
import softuni.exam.models.enums.DaysOfWeek;

import java.util.List;
import java.util.Optional;

@Repository
public interface ForecastRepository extends JpaRepository<Forecast, Long> {

    Optional<Forecast> findByCity_IdAndDayOfWeek(Long id, DaysOfWeek dayOfWeek);

//    Optional<Forecast> findForecastByDayOfWeekAndCityId(DaysOfWeek day, Long id);

    List<Forecast> findForecastsByDayOfWeekAndCityPopulationLessThanOrderByMaxTemperatureDescIdAsc
            (DaysOfWeek daysOfWeek, int population);
}
