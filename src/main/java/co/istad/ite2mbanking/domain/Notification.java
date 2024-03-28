package co.istad.ite2mbanking.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 100)
    private String content;
    private LocalDateTime transactedAt;
    @ManyToOne
    private User senderId;
    @ManyToOne
    private User receiverId;
    @ManyToOne
    private Transaction transactionId;
}
