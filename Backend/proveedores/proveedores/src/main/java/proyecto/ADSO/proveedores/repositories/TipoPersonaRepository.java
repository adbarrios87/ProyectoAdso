package proyecto.ADSO.proveedores.repositories;

import proyecto.ADSO.proveedores.entites.TipoPersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoPersonaRepository extends
        JpaRepository<TipoPersonaEntity, Integer>,
        JpaSpecificationExecutor<TipoPersonaEntity> {
}
