package co.edu.uniandes.csw.mpusedvehicle.persistence;

import co.edu.uniandes.csw.mpusedvehicle.entities.CommentEntity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;

/**
 * @generated
 */
@Stateless
public class CommentPersistence extends CrudPersistence<CommentEntity> {

    /**
     * @generated
     */
    public CommentPersistence() {
        this.entityClass = CommentEntity.class;
    }
    
    public List<CommentEntity> getByProduct(Long productId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("product_id", productId);
        return executeListNamedQuery("Comment.getByProduct", params);
    }
}
