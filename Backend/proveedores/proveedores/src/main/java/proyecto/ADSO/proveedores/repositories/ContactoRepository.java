package proyecto.ADSO.proveedores.repositories;

import proyecto.ADSO.proveedores.entites.ContactoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactoRepository extends
        JpaRepository<ContactoEntity, Integer>,
        JpaSpecificationExecutor<ContactoEntity> {
}
