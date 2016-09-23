package ejb.entities;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;


@Entity
@Builder
@NoArgsConstructor
@Table(name = "student")
@AllArgsConstructor(access = PRIVATE)
@NamedQuery(name = "Student.getList", query = "SELECT student FROM Student student")
public class Student {
    @Id @GeneratedValue(strategy = IDENTITY)
    private @Getter Long id;

    @Column(name = "firstName")
    private @Getter String firstName;

    @Column(name = "secondName")
    private @Getter String secondName;
}
