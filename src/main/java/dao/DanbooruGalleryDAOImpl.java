package dao;

import com.google.gson.Gson;
import com.mongodb.client.FindIterable;
import org.bson.Document;
import service.impl.DanbooruHarvestServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class DanbooruGalleryDAOImpl extends DatabaseHelper implements DanbooruGalleryDAO{

    private final String collectionName = "Danbooru";

    @Override
    public void insertNewGallery(DanbooruGalleryDTO dto) {
        Document document = new Document();
        document.put("id", dto.getId());
        document.put("rating", dto.getRating());
        document.put("image url", dto.getImageUrl());
        document.put("image width", dto.getWidth());
        document.put("image height", dto.getHeight());
        document.put("score", dto.getScore());
        document.put("upload datetime", dto.getUploadDateTime());
        document.put("favourite", dto.getFavourite());
        document.put("uploader id", dto.getUploaderId());
        document.put("source url", dto.getSourceUrl());
        document.put("tags", dto.getTags());

        if (this.findDocument("id", dto.getId(), this.collectionName) != null) {
            System.out.println("existed");
        } else
            this.insertUpdateDocument(document, this.collectionName);
    }

    public void insertNewGalleries(List<DanbooruGalleryDTO> dtos) {
        dtos.forEach(this::insertNewGallery);
    }

    @Override
    public List<DanbooruGalleryDTO> getAllGalleries() {

        DanbooruHarvestServiceImpl service = new DanbooruHarvestServiceImpl();

        FindIterable<Document> documents = this.getAllDocumentFromCollection(this.collectionName);
        List<DanbooruGalleryDTO> dtos = new ArrayList<>();
        for (Document document : documents) {
            Gson gson = new Gson();
            DanbooruGalleryDTO dto = gson.fromJson(document.toJson(), DanbooruGalleryDTO.class);
            System.out.println(service.getGalleryFromDTO(dto).toString());
            dtos.add(dto);
        }

        System.out.println("Size: " + dtos.size());


        return null;
    }
}
