package proyecto.ADSO.proveedores.repositories;

import proyecto.ADSO.proveedores.entites.TipoIdentificacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoIdentificacionRepository extends
        JpaRepository<TipoIdentificacionEntity, Integer>,
        JpaSpecificationExecutor<TipoIdentificacionEntity> {
}
