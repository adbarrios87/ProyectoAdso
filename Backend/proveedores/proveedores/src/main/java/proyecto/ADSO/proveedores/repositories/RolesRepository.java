package proyecto.ADSO.proveedores.repositories;

import proyecto.ADSO.proveedores.entites.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<RolesEntity, Integer> {
}
