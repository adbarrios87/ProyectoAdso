package proyecto.ADSO.proveedores.repositories;

import proyecto.ADSO.proveedores.entites.SociosProveedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SociosProveedorRepository extends
        JpaRepository<SociosProveedorEntity, Integer>,
        JpaSpecificationExecutor<SociosProveedorEntity> {
    java.util.List<SociosProveedorEntity> findByIdProveedor(Integer idProveedor);
    void deleteByIdProveedor(Integer idProveedor);

}

