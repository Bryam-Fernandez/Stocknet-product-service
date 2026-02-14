package com.example.Stocknet_product_service.service;

import com.example.Stocknet_product_service.model.Producto;
import java.util.List;

public interface ProductoService {
    List<Producto> listarTodos();
    List<Producto> listarActivos();
    Producto obtenerPorId(Long id);

    Producto crear(Producto producto);
    Producto actualizar(Long id, Producto producto);

    void eliminar(Long id);              // físico
    Producto cambiarEstado(Long id, Boolean estado); // lógico
}
