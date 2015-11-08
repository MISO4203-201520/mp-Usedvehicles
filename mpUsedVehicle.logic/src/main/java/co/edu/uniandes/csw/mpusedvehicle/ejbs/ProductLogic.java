package co.edu.uniandes.csw.mpusedvehicle.ejbs;

import co.edu.uniandes.csw.mpusedvehicle.api.IProductLogic;
import co.edu.uniandes.csw.mpusedvehicle.converters.ProductConverter;
import co.edu.uniandes.csw.mpusedvehicle.dtos.ProductDTO;
import co.edu.uniandes.csw.mpusedvehicle.entities.ProductEntity;
import co.edu.uniandes.csw.mpusedvehicle.persistence.ProductPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @generated
 */
@Stateless
public class ProductLogic implements IProductLogic {

    @Inject private ProductPersistence persistence;

    /**
     * @generated
     */
    public int countProducts() {
        return persistence.count();
    }

    /**
     * @generated
     */
    public List<ProductDTO> getProducts(Integer page, Integer maxRecords) {
        return ProductConverter.listEntity2DTO(persistence.findAll(page, maxRecords));
    }

    /**
     * @generated
     */
    public ProductDTO getProduct(Long id) {
        return ProductConverter.fullEntity2DTO(persistence.find(id));
    }

    /**
     * @generated
     */
    public ProductDTO createProduct(ProductDTO dto) {
        ProductEntity entity = ProductConverter.fullDTO2Entity(dto);
        persistence.create(entity);
        return ProductConverter.fullEntity2DTO(entity);
    }

    /**
     * @generated
     */
    public ProductDTO updateProduct(ProductDTO dto) {
        ProductEntity entity = persistence.update(ProductConverter.fullDTO2Entity(dto));
        return ProductConverter.fullEntity2DTO(entity);
    }

    /**
     * @generated
     */
    public void deleteProduct(Long id) {
        persistence.delete(id);
    }

    /**
     * @generated
     */
    public List<ProductDTO> findByName(String name) {
        return ProductConverter.listEntity2DTO(persistence.findByName(name));
    }
    
     public List<ProductDTO> getByVehicleName(String name){
        return ProductConverter.listEntity2DTO(persistence.getByVehicleName(name));
    }
     
     public ProductDTO getCheaperProductByProvider (String nameProvider){
         return ProductConverter.fullEntity2DTO(persistence.getCheaperProductByProvider(nameProvider));
     }
     
     public ProductDTO getCheaperProductByVehicle (String nameVehicle){
         return ProductConverter.fullEntity2DTO(persistence.getCheaperProductByVehicle(nameVehicle));
     }

    public List<ProductDTO> getProductsByAdvancedSearch(String brand, String model, Integer capacity, Integer price, String color, String plate, String location) {
        return ProductConverter.listEntity2DTO(persistence.getProductsByAdvancedSearch(brand, model, capacity, price, color, plate, location));
    }
    
    public List<ProductDTO> getVehiclesName() {
        return ProductConverter.listString2DTO(persistence.getVehiclesName());
    }
    
    public List<ProductDTO> getVehiclesBrand() {
        return ProductConverter.listString2DTO(persistence.getVehiclesBrand());
    }
    
    public List<ProductDTO> getVehiclesCapacity() {
        return ProductConverter.listString2DTO(persistence.getVehiclesCapacity());
    }
        
    public List<ProductDTO> getVehiclesColor() {
        return ProductConverter.listString2DTO(persistence.getVehiclesColor());
    }
        
    public List<ProductDTO> getVehiclesModel() {
        return ProductConverter.listString2DTO(persistence.getVehiclesModel());
    }
        
    public List<ProductDTO> getVehiclesPlate() {
        return ProductConverter.listString2DTO(persistence.getVehiclesPlate());
    }
        
    public List<ProductDTO> getVehiclesLocation() {
        return ProductConverter.listString2DTO(persistence.getVehiclesLocation());
    }    
     
    /**
     * Metodo que actualiza la calificacion de un producto
     * @param id. Identificador del producto calificado.
     * @param rating. Nueva calificacion a agregar al promedio.
     * @return reporna el producto con la nueva calificacion promedio.
     */
    public ProductDTO updateRating(Long id, Integer rating){
        ProductEntity product = persistence.find(id);
        Float newRating;
        if(product.getAmmountVotes()!= null || product.getRating()!= null)
        {
            newRating= product.getRating() * product.getAmmountVotes() + rating;
            newRating = newRating/(product.getAmmountVotes()+1);
            product.setAmmountVotes(product.getAmmountVotes()+1);
        }
        else{
            newRating = rating.floatValue();
            product.setAmmountVotes(1);
        }
        product.setRating(newRating);
        return ProductConverter.fullEntity2DTO(persistence.update(product));
    } 
    /**
     * Metodo que verifica si un cliente determinado puede calificar un producto. Un cliente solo puede calificar si ha comprado el producto.
     * @param idProduct. Identificador del producto que se desea calificar.
     * @param idClient. Identificador del cliente que desea calificar el producto.
     * @return booleano. retorna true si el cliente puede calificar el producto y falso en caso contrario.
     */
    public Boolean canRateProduct(Long idProduct, Long idClient){
        return persistence.findProductPrurchasedByClient(idProduct,idClient);
        
    }
       
}
