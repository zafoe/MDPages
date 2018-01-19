package ovh.kocproz.markpages.data.model;

import javax.persistence.*;

/**
 * @author KocproZ
 * Created 2018-01-19 at 12:29
 */
@Entity(name = "FavouritePages")
@Table(name = "favouritePages")
public class FavouritePagesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UserModel user;

    @ManyToOne
    private PageModel page;

    public Long getId() {
        return id;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public PageModel getPage() {
        return page;
    }

    public void setPage(PageModel page) {
        this.page = page;
    }
}
