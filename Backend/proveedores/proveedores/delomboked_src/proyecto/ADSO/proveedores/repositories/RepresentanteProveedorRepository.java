package proyecto.ADSO.proveedores.repositories;

import proyecto.ADSO.proveedores.entites.RepresentanteProveedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RepresentanteProveedorRepository extends
        JpaRepository<RepresentanteProveedorEntity, Integer>,
        JpaSpecificationExecutor<RepresentanteProveedorEntity> {
}
