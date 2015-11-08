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
    public List<ProductDTO> getProductsByAdvancedSearch(String brand, String model, Integer capacity, Integer price, String color, String plate, String location);
    /**
     * Metodo que actualiza la calificacion de un producto
     * @param id. Identificador del producto calificado.
     * @param rating. Nueva calificacion a agregar al promedio.
     * @return reporna el producto con la nueva calificacion promedio.
     */
    public ProductDTO updateRating(Long id, Integer rating);
    
    /**
     * Metodo que verifica si un cliente determinado puede calificar un producto. Un cliente solo puede calificar si ha comprado el producto.
     * @param idProduct. Identificador del producto que se desea calificar.
     * @param idClient. Identificador del cliente que desea calificar el producto.
     * @return booleano. retorna true si el cliente puede calificar el producto y falso en caso contrario.
     */
    public Boolean canRateProduct(Long idProduct, Long idClient);
}
