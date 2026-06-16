package proyecto.ADSO.proveedores.repositories;

import proyecto.ADSO.proveedores.entites.ProveedorContactoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorContactoRepository extends
        JpaRepository<ProveedorContactoEntity, Integer>,
        JpaSpecificationExecutor<ProveedorContactoEntity> {
    java.util.List<ProveedorContactoEntity> findByIdProveedor(Integer idProveedor);
    void deleteByIdProveedor(Integer idProveedor);

}

