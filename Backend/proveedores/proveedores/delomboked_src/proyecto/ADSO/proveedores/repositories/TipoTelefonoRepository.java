package proyecto.ADSO.proveedores.repositories;

import proyecto.ADSO.proveedores.entites.TipoTelefonoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoTelefonoRepository extends
        JpaRepository<TipoTelefonoEntity, Integer>,
        JpaSpecificationExecutor<TipoTelefonoEntity> {
}
