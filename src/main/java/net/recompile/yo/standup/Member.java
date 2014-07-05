package net.recompile.yo.standup;

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
@Table(name = "members")
@NamedQuery(name = "Member.countByName", query = "SELECT COUNT(m) FROM Member m WHERE m.name LIKE :name")
public class Member implements Serializable {
    @Id
    @SequenceGenerator(name = "members_id_seq", sequenceName = "members_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "members_id_seq")
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
