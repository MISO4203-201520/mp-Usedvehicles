package co.edu.uniandes.csw.mpusedvehicle.api;

import co.edu.uniandes.csw.mpusedvehicle.dtos.CommentDTO;
import java.util.List;

public interface ICommentLogic {
    public int countComments();
    public List<CommentDTO> getComments(Integer page, Integer maxRecords);
    public CommentDTO getComment(Long id);
    public CommentDTO createComment(CommentDTO dto);
    public CommentDTO updateComment(CommentDTO dto);
    public void deleteComment(Long id);
    public List<CommentDTO> getByProduct(Long productId);
}
