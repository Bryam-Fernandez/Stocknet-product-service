package com.example.Stocknet_product_service.service;

import com.example.Stocknet_product_service.model.Producto;
import com.example.Stocknet_product_service.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository repo;

    public ProductoServiceImpl(ProductoRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Producto> listarTodos() {
        return repo.findAll();
    }
    
    @Override
    public Producto guardar(Producto producto) {
        return repo.save(producto);
    }


    @Override
    public List<Producto> listarActivos() {
        return repo.findByEstadoTrue();
    }

    @Override
    public Producto obtenerPorId(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
    }

    @Override
    public Producto crear(Producto producto) {
        producto.setId(null);
        if (producto.getEstado() == null) producto.setEstado(true); // por defecto activo
        return repo.save(producto);
    }

    @Override
    public Producto actualizar(Long id, Producto producto) {
        Producto db = obtenerPorId(id);

        db.setNombre(producto.getNombre());
        db.setDescripcion(producto.getDescripcion());
        db.setPrecio(producto.getPrecio());
        db.setStock(producto.getStock());
        if (producto.getEstado() != null) db.setEstado(producto.getEstado());

        return repo.save(db);
    }

    @Override
    public void eliminar(Long id) {
        Producto db = obtenerPorId(id);
        repo.delete(db);
    }

    @Override
    public Producto cambiarEstado(Long id, Boolean estado) {
        Producto db = obtenerPorId(id);
        db.setEstado(estado);
        return repo.save(db);
    }
}
