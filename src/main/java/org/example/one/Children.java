package org.example.one;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "children")
@Data
@NoArgsConstructor //добавляет конструктор без аргументов
@AllArgsConstructor //добавляет конструктор со всеми параметрами
@Getter //добавляет геттеры для всех параметров класса
@Setter //добавляет сеттеры для всех параметров класса
@Builder
public class Children {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String lastName;
    private int age;
    private int brotherSister;

    @ManyToOne(cascade = CascadeType.ALL)//здесь стоит по умолчанию связь Eager , при Lazy показывает ошибку и не выводит только айди матери.
    @JoinColumn(name = "mother_id")
    private Mother mother;
}
