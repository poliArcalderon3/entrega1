package org.producto.infraestructure.adapter.out;

import org.producto.domain.model.Producto;
import org.producto.domain.port.out.ProductoRepositoryPort;
import org.producto.infraestructure.adapter.out.persistence.ProductoEntity;
import org.producto.infraestructure.adapter.out.persistence.ProductoJpaRepository;

import java.util.List;
import java.util.Optional;

public class ProductoRepositoryAdapter   implements ProductoRepositoryPort {
    private final ProductoJpaRepository repository;

    public ProductoRepositoryAdapter(ProductoJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Producto guardar(Producto producto) {
        ProductoEntity entity = ProductoEntity.builder()
                .id(producto.getId())
                .nombre(producto.getNombre())
                .descripcion(producto.getDescripcion())
                .precio(producto.getPrecio())
                .build();

        ProductoEntity guardado = repository.save(entity);

        return convertirADominio(guardado);
    }

    @Override
    public Optional<Producto> buscarPorId(Long id) {
        return repository.findById(id)
                .map(this::convertirADominio);
    }

    @Override
    public List<Producto> buscarTodos() {
        return repository.findAll()
                .stream()
                .map(this::convertirADominio)
                .toList();
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    private Producto convertirADominio(ProductoEntity entity) {

        return Producto.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .precio(entity.getPrecio())
                .build();
    }
}
