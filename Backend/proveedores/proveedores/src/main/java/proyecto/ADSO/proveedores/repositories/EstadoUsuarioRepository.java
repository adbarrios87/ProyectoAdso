package proyecto.ADSO.proveedores.repositories;

import proyecto.ADSO.proveedores.entites.EstadoUsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoUsuarioRepository extends
        JpaRepository<EstadoUsuarioEntity, Integer>,
        JpaSpecificationExecutor<EstadoUsuarioEntity> {
}
