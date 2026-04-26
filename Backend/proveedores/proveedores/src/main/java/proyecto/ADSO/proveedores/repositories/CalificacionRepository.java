package proyecto.ADSO.proveedores.repositories;

import proyecto.ADSO.proveedores.entites.CalificacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CalificacionRepository extends
        JpaRepository<CalificacionEntity, Integer>,
        JpaSpecificationExecutor<CalificacionEntity> {
}
