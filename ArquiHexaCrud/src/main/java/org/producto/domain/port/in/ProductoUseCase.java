package org.producto.domain.port.in;

import org.producto.domain.model.Producto;

import java.util.List;

public interface ProductoUseCase {
    Producto crear(Producto producto);

    Producto obtenerPorId(Long id);

    List<Producto> obtenerTodos();

    Producto actualizar(Long id, Producto producto);

    void eliminar(Long id);
}
