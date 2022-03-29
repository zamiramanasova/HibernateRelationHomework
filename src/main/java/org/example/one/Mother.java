package org.example.one;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "mother")
@Data
@NoArgsConstructor //добавляет конструктор без аргументов
@AllArgsConstructor //добавляет конструктор со всеми параметрами
@Getter //добавляет геттеры для всех параметров класса
@Setter //добавляет сеттеры для всех параметров класса
public class Mother {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String lastName;
    private int age;

}
