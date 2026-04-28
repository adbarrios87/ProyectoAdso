package proyecto.ADSO.proveedores.repositories;

import proyecto.ADSO.proveedores.entites.EstadoProveedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoProveedorRepository extends
        JpaRepository<EstadoProveedorEntity, Integer>,
        JpaSpecificationExecutor<EstadoProveedorEntity> {
}
