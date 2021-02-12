package es.urjc.cloudapps.planes.data;

import es.urjc.cloudapps.planes.domain.Plane;
import es.urjc.cloudapps.planes.dto.PlaneRevisionDto;
import es.urjc.cloudapps.planes.dto.PlaneRevisionJsonDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlaneRepository extends CrudRepository<Plane, String> {

    @Query("select new es.urjc.cloudapps.planes.dto.PlaneRevisionDto(p.plate, r.id, r.mechanicInCharge.name, r.mechanicInCharge.surname)" +
            "from Plane p join Revision r on p = r.plane")
    List<PlaneRevisionDto> findAllWithRevisionMechanics();

    // @Query(value = "select new es.urjc.cloudapps.planes.dto.PlaneRevisionJsonDto(p.plate, FUNCTION('JSON_EXTRACT', p.data, '$.revisions')) from Plane p")
    @Query(value = "select new es.urjc.cloudapps.planes.dto.PlaneRevisionJsonDto(p.plate, p.data) from Plane p")
    List<PlaneRevisionJsonDto> findAllWithRevisionJson();

}
