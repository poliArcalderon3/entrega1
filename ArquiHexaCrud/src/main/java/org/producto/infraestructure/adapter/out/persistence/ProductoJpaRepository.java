package org.producto.infraestructure.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoJpaRepository  extends JpaRepository<ProductoEntity, Long> {
}
