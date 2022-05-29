package pl.home.artpicture.matned;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "achievement")
@XmlAccessorType(XmlAccessType.FIELD)
public class Achievement implements Serializable {
    private static final long serialVersionUID = 1L;

    private String title;
    private String description;

    public Achievement() {
        super();
    }

    public Achievement(String title, String description) {
        super();
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return title + ": " + description;
    }
}
