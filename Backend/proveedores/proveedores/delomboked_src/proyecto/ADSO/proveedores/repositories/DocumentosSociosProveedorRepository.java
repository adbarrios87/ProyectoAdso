package proyecto.ADSO.proveedores.repositories;

import proyecto.ADSO.proveedores.entites.DocumentosSociosProveedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentosSociosProveedorRepository extends
        JpaRepository<DocumentosSociosProveedorEntity, Integer>,
        JpaSpecificationExecutor<DocumentosSociosProveedorEntity> {
}
