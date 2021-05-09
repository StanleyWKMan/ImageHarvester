package service;

import dao.DanbooruGalleryDTO;
import model.gallery.DanbooruGallery;
import org.jsoup.nodes.Element;

import java.util.List;
import java.util.Map;

public interface DanbooruHarvestService extends HarvestService {

    List<DanbooruGallery> getGalleriesFromSearchResult(String url);

    List<String> getSearchResultUrlsFromQueries(String url);

    DanbooruGallery buildGalleryFromElement(Element element);

    DanbooruGallery getGalleryFromDTO(DanbooruGalleryDTO dto);

    DanbooruGalleryDTO getDTOFromGallery(DanbooruGallery gallery);

    List<DanbooruGalleryDTO> getDTOsFromGalleries(List<DanbooruGallery> galleries);

    String buildQueryUrl(Map<String, String> urlComponents);


}
