package proyecto.ADSO.proveedores.repositories;

import proyecto.ADSO.proveedores.entites.FirmaTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface FirmaTokenRepository extends JpaRepository<FirmaTokenEntity, Integer> {
    Optional<FirmaTokenEntity> findByToken(String token);
    Optional<FirmaTokenEntity> findByIdProveedorAndUtilizado(Integer idProveedor, Boolean utilizado);
}
