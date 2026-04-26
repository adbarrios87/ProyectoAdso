package proyecto.ADSO.proveedores.repositories;

import proyecto.ADSO.proveedores.entites.HistorialUsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface HistorialUsuarioRepository extends
        JpaRepository<HistorialUsuarioEntity, Integer>,
        JpaSpecificationExecutor<HistorialUsuarioEntity> {
}
