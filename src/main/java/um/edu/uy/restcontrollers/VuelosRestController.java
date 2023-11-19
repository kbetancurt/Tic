package um.edu.uy.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import um.edu.uy.business.VueloMgr;
import um.edu.uy.business.entities.Vuelo;

import java.util.List;

@RestController
@RequestMapping("/rest/api/vuelos")
public class VuelosRestController {

    @Autowired
    private VueloMgr vueloMgr;
    @GetMapping("/getall/{idaerolinea}")
    public List<Vuelo> obtenerVuelosAerolinea(@PathVariable("idaerolinea") long idaerolinea)
    {
        return vueloMgr.obtenerVuelosAerolinea(idaerolinea);
    }

}
