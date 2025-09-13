package api.crud.product.service;

import api.crud.product.DTO.productDTO;
import api.crud.product.entity.product;
import api.crud.product.repository.productRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.List;
import java.util.Map;

@Service
public class productService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    private productRepository productRepository;
    public productService(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // Metodo usando jdbc
    public Map<String, Object> crearProducto(String nombre, double precio) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("insert_product")
                .declareParameters(
                        new SqlParameter("p_nombre", Types.VARCHAR),
                        new SqlParameter("p_precio", Types.DOUBLE),
                        new SqlOutParameter("p_cursor", Types.REF_CURSOR),
                        new SqlOutParameter("p_codigo", Types.INTEGER),
                        new SqlOutParameter("p_mensaje", Types.VARCHAR));

        Map<String, Object> inParams = Map.of("p_nombre", nombre, "p_precio", precio);
        return jdbcCall.execute(inParams);
    }

    // Metodo usando JPA
    public List<product> insertProduct (productDTO productdto){
        product p = new product();

        p.setNombre(productdto.getNombre());
        p.setPrecio(productdto.getPrecio());

        productRepository.save(p);
        return productRepository.findAll();
    };
}
