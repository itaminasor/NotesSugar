package pe.com.itaminasor.notessugar.model;

import com.orm.dsl.Table;

import java.util.Date;

@Table
public class Note {

    private Long id;

    private String title;
    private String content;
    private Date date;
    private Boolean favorite;
    private Boolean archive;
    private Long user;

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }



    public Note() {
    }

    public Note(Long id, String title, String content, Date date, Boolean favorite, Long user, Boolean archive) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.favorite = favorite;
        this.user = user;
        this.archive = archive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }


    public Boolean getArchive() {
        return archive;
    }

    public void setArchive(Boolean archive) {
        this.archive = archive;
    }



    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", favorite=" + favorite +
                '}';
    }
}

