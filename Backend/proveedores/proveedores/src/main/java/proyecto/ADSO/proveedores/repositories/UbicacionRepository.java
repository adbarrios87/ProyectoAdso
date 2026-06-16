package proyecto.ADSO.proveedores.repositories;

import proyecto.ADSO.proveedores.entites.UbicacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UbicacionRepository extends
        JpaRepository<UbicacionEntity, Integer>,
        JpaSpecificationExecutor<UbicacionEntity> {
    java.util.Optional<UbicacionEntity> findByIdProveedor(Integer idProveedor);

}

