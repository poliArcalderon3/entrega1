package org.producto.infraestructure.config;
import org.producto.application.service.ProductoService;
import org.producto.domain.port.in.ProductoUseCase;
import org.producto.domain.port.out.ProductoRepositoryPort;
import org.producto.infraestructure.adapter.out.ProductoRepositoryAdapter;
import org.producto.infraestructure.adapter.out.persistence.ProductoJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public ProductoRepositoryPort productoRepositoryPort(
            ProductoJpaRepository repository) {

        return new ProductoRepositoryAdapter(repository);
    }
    @Bean
    public ProductoUseCase productoUseCase(
            ProductoRepositoryPort repositoryPort) {

        return new ProductoService(repositoryPort);
    }

}
