package proyecto.ADSO.proveedores.repositories;

import proyecto.ADSO.proveedores.entites.RepresentanteLegalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RepresentanteLegalRepository extends
        JpaRepository<RepresentanteLegalEntity, Integer>,
        JpaSpecificationExecutor<RepresentanteLegalEntity> {
}
