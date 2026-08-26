package org.producto.application.service;

import org.producto.domain.model.Producto;
import org.producto.domain.port.in.ProductoUseCase;
import org.producto.domain.port.out.ProductoRepositoryPort;

import java.util.List;

public class ProductoService  implements ProductoUseCase {
    private final ProductoRepositoryPort repositoryPort;

    public ProductoService(ProductoRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public Producto crear(Producto producto) {
        return repositoryPort.guardar(producto);
    }

    @Override
    public Producto obtenerPorId(Long id) {
        return repositoryPort.buscarPorId(id)
                .orElseThrow(() ->
                        new RuntimeException("Producto no encontrado"));
    }

    @Override
    public List<Producto> obtenerTodos() {
        return repositoryPort.buscarTodos();
    }

    @Override
    public Producto actualizar(Long id, Producto producto) {
        Producto productoExistente = obtenerPorId(id);

        productoExistente.setNombre(producto.getNombre());
        productoExistente.setDescripcion(producto.getDescripcion());
        productoExistente.setPrecio(producto.getPrecio());

        return repositoryPort.guardar(productoExistente);
    }

    @Override
    public void eliminar(Long id) {
        obtenerPorId(id);
        repositoryPort.eliminar(id);
    }
}
