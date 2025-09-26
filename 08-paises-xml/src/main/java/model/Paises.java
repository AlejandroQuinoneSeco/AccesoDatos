package model;

import jakarta.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "paises")
@XmlAccessorType(XmlAccessType.FIELD)
public class Paises {

    @XmlElement(name = "pais")
    private List<Pais> paises;

    public List<Pais> getPaises() { return paises; }
    public void setPaises(List<Pais> paises) { this.paises = paises; }
}
