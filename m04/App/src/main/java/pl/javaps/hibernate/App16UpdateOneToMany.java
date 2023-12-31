package pl.javaps.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.javaps.hibernate.entity.Attribute;
import pl.javaps.hibernate.entity.Product;
import pl.javaps.hibernate.entity.Review;
import pl.javaps.hibernate.entity.ReviewDto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App16UpdateOneToMany
{
    private static Logger logger = LogManager.getLogger(App16UpdateOneToMany.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main( String[] args )
    {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        List<ReviewDto> reviewDtos = getUpdatedReviews();

        Product product = em.find(Product.class, 3L);
        for (Review review : product.getReviews()) {
            for (ReviewDto reviewDto : reviewDtos) {
                if(review.getId().equals(reviewDto.getId())){
                    review.setContent(reviewDto.getContent());
                    review.setRating(reviewDto.getRating());
                }
            }
        }

        em.getTransaction().commit();
        em.close();

    }

    private static List<ReviewDto> getUpdatedReviews(){
        List<ReviewDto> list = new ArrayList<>();
        list.add(new ReviewDto(13L,"Treść opini 3!!!",10));
        list.add(new ReviewDto(14L,"Treść opini 4!!!",10));
        list.add(new ReviewDto(15L,"Treść opini 5!!!",10));
        return list;
    }
}
