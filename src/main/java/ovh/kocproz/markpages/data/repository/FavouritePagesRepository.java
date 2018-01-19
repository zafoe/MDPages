package ovh.kocproz.markpages.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ovh.kocproz.markpages.data.model.FavouritePagesModel;
import ovh.kocproz.markpages.data.model.PageModel;
import ovh.kocproz.markpages.data.model.UserModel;

public interface FavouritePagesRepository extends JpaRepository<FavouritePagesModel, Long> {

    void deleteByUserAndPage(UserModel user, PageModel page);

}
