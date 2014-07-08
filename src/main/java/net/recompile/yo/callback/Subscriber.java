package net.recompile.yo.callback;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "subscribers")
@NamedQuery(name = "Subscriber.countByName", query = "SELECT COUNT(s) FROM Subscriber s WHERE s.name LIKE :name")
public class Subscriber implements Serializable {
    @Id
    @SequenceGenerator(name = "subscribers_id_seq", sequenceName = "subscribers_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subscribers_id_seq")
    private Long id;

    @NotNull
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
