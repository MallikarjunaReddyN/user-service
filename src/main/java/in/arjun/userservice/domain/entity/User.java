package in.arjun.userservice.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
    private String id;
	private String name;
	@Column(unique = true)
	private String email;
	private String phone;
}
