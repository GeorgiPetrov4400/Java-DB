package softuni.exam.models.dtos.sellerDtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "sellers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SellerImportWrapperDTO {

    @XmlElement(name = "seller")
    private List<SellerImportDTO> sellers;

    public List<SellerImportDTO> getSellers() {
        return sellers;
    }

    public void setSellers(List<SellerImportDTO> sellers) {
        this.sellers = sellers;
    }
}
