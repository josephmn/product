package api.crud.product.controller;

import api.crud.product.DTO.productDTO;
import api.crud.product.entity.product;
import api.crud.product.service.productService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/product")
public class productController {

    @Autowired
    productService productService;

    @PostMapping("/registrar")
    public Map<String, Object> crearProducto(@RequestBody productDTO productdto) {
        return productService.crearProducto(productdto.getNombre(), productdto.getPrecio());
    }

    @PostMapping("/insert")
    public List<product> insertProduct(@RequestBody productDTO productdto) {
        return productService.insertProduct(productdto);
    }
}
