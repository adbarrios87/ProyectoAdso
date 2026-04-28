package proyecto.ADSO.proveedores.repositories;

import proyecto.ADSO.proveedores.entites.TipoNotificacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoNotificacionRepository extends
        JpaRepository<TipoNotificacionEntity, Integer>,
        JpaSpecificationExecutor<TipoNotificacionEntity> {
}
