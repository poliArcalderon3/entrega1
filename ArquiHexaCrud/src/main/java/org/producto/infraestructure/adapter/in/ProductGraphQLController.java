package org.producto.infraestructure.adapter.in;

import org.producto.domain.model.Producto;
import org.producto.domain.port.in.ProductoUseCase;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ProductGraphQLController {

    private final ProductoUseCase productoUseCase;

    public ProductGraphQLController(ProductoUseCase productoUseCase) {
        this.productoUseCase = productoUseCase;
    }
    public record ProductoInput(
            String nombre,
            String descripcion,
            Double precio,
            Integer stock
    ) {}

    @QueryMapping
    public List<Producto> productos() {
        return productoUseCase.obtenerTodos();
    }

    @QueryMapping
    public Producto productoPorId(@Argument Long id) {
        return productoUseCase.obtenerPorId(id);
    }

    @MutationMapping
    public Producto crearProducto(@Argument ProductoInput input) {

        Producto producto = new Producto(
                null,
                input.nombre(),
                input.descripcion(),
                input.precio()
        );

        return productoUseCase.crear(producto);
    }

}
