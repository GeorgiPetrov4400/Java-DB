package com.example.football.models.dto.statDtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "stats")
@XmlAccessorType(XmlAccessType.FIELD)
public class StatImportWrapperDTO {

    @XmlElement(name = "stat")
    private List<StatImportDTO> stats;


    public List<StatImportDTO> getStats() {
        return stats;
    }
}
