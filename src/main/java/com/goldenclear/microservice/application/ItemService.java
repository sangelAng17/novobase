    package com.goldenclear.microservice.application;


    import com.goldenclear.microservice.domain.model.Item;
    import com.goldenclear.microservice.domain.port.ItemRepository;
    import lombok.AllArgsConstructor;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.util.List;

    @AllArgsConstructor
    @Service
    public class ItemService {

        @Autowired
        ItemRepository itemRepository;

        public List<Item> findItemsWithAverageRatingLowerThan(Double rating) {

            if (rating == null) {
                throw new IllegalArgumentException("el valor Rating no puede ser null");
            }

            if (rating < 0) {
                throw new IllegalArgumentException("Rating no puede ser igual o menor que 0");
            }

            return itemRepository.findItemsWithAverageRatingLowerThan(rating);
        }
        public List<String> getTitles(Long rating) {

            if (rating == null) {
                throw new IllegalArgumentException("el valor Rating no puede ser null");
            }

            if (rating < 0) {
                throw new IllegalArgumentException("Rating no puede ser igual o menor que 0");
            }

            return itemRepository.findDistinctByReviewsCantidad(rating)
                    .stream()
                    .map(Item::getTitulo)
                    .toList();
        }

        public List<String> getTitles(Double rating) {

            if (rating == null) {
                throw new IllegalArgumentException("el valor Rating no puede ser null");
            }

            if (rating < 0) {
                throw new IllegalArgumentException("Rating no puede ser igual o menor que 0");
            }

            Long ratingLong = rating.longValue();

            return itemRepository.findDistinctByReviewsCantidad(ratingLong)
                    .stream()
                    .map(Item::getTitulo)
                    .toList();
        }



}
