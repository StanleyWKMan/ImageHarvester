package dao;

import java.util.List;

public interface DanbooruGalleryDAO {

    void insertNewGallery(DanbooruGalleryDTO dto);

    void insertNewGalleries(List<DanbooruGalleryDTO> dtos);

    List<DanbooruGalleryDTO> getAllGalleries();


}
