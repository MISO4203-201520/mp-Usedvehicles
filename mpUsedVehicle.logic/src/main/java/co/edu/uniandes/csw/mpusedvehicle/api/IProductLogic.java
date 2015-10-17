package co.edu.uniandes.csw.mpusedvehicle.api;

import co.edu.uniandes.csw.mpusedvehicle.dtos.ProductDTO;
import java.util.List;

public interface IProductLogic {
    public int countProducts();
    public List<ProductDTO> getProducts(Integer page, Integer maxRecords);
    public ProductDTO getProduct(Long id);
    public ProductDTO createProduct(ProductDTO dto);
    public ProductDTO updateProduct(ProductDTO dto);
    public void deleteProduct(Long id);
    public List<ProductDTO> findByName(String name);
    public List<ProductDTO> getByVehicleName(String name);
    public ProductDTO getCheaperProductByProvider (String nameProvider);
    public ProductDTO getCheaperProductByVehicle (String nameVehicle);
    public List<ProductDTO> getVehiclesName();
    public List<ProductDTO> getVehiclesBrand();
    public List<ProductDTO> getVehiclesCapacity();
    public List<ProductDTO> getVehiclesColor();
    public List<ProductDTO> getVehiclesModel();
    public List<ProductDTO> getVehiclesPlate();
    public List<ProductDTO> getVehiclesLocation();
    public List<ProductDTO> getProductsByAdvancedSearch(String brand, String model, Integer capacity, Integer price);
}
