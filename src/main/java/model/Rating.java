package model;

import java.util.EnumMap;

public enum Rating {

    SAFE,
    QUESTIONABLE,
    EXPLICIT;

   static EnumMap<Rating, String> ratingStringMap = new EnumMap<>(Rating.class);

   static {
       ratingStringMap.put(SAFE, "Safe");
       ratingStringMap.put(QUESTIONABLE, "Questionable");
       ratingStringMap.put(EXPLICIT, "Explicit");
   }

   public String toString() {
       return ratingStringMap.get(this);
   }

   public static Rating toRating(String ratingString) {
       return switch (ratingString) {
           case ("s") -> Rating.SAFE;
           case ("e") -> Rating.EXPLICIT;
           case ("q") -> Rating.QUESTIONABLE;
           default -> null;
       };
   }


}
