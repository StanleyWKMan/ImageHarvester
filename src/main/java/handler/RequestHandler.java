package handler;

import controller.DanbooruController;
import controller.GelbooruController;
import controller.IllusionCardController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestHandler {

    DanbooruController danbooruController;

    GelbooruController gelbooruController;

    IllusionCardController illusionCardController;

    public void setDanbooruController(DanbooruController danbooruController) {
        this.danbooruController = danbooruController;
    }

    public void setGelbooruController(GelbooruController gelbooruController) {
        this.gelbooruController = gelbooruController;
    }

    public void setIllusionCardController(IllusionCardController illusionCardController) {
        this.illusionCardController = illusionCardController;
    }

    @GetMapping("/")
    public String hello() {
        return "Done";
    }

}
