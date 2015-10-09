package co.edu.uniandes.csw.mpusedvehicle.api;

import co.edu.uniandes.csw.mpusedvehicle.dtos.ProviderDTO;
import java.util.List;

public interface IProviderLogic {
    public int countProviders();
    public List<ProviderDTO> getProviders(Integer page, Integer maxRecords);
    public ProviderDTO getProvider(Long id);
    public ProviderDTO createProvider(ProviderDTO dto);
    public ProviderDTO updateProvider(ProviderDTO dto);
    public void deleteProvider(Long id);
    public List<ProviderDTO> findByName(String name);
    public ProviderDTO getProviderByUserId(String userId);
    public ProviderDTO getProviderByModel (String model);
    public ProviderDTO getProviderByBrand (String brand);
    public ProviderDTO getProviderByCity (String city);
    public ProviderDTO getProviderByPriceRange (Integer lower, Integer upper);
}
