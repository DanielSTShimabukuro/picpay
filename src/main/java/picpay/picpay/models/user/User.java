package picpay.picpay.models.user;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import picpay.picpay.dtos.user.UserRequestDTO;

@Entity(name = "users")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @NotBlank(message = "CPF is required.")
  @Column(nullable = false, unique =  true)
  @CPF(message = "CPF invalid.")
  private String cpf;

  @NotBlank(message = "Email is required.")
  @Column(nullable = false, unique = true)
  @Email(message = "Email invalid.")
  private String email;

  @NotBlank(message = "First name is required.")
  @Column(nullable = false)
  private String firstName;

  @NotBlank(message = "Last name is required.")
  @Column(nullable = false)
  private String lastName;

  @Column(nullable = false)
  private String password;

  @NotNull(message = "Balance is requried")
  @Column(nullable = false)
  private BigDecimal balance;

  @NotNull(message = "Type is required.")
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private UserType type;

  public User(UserRequestDTO data) {
    this.cpf = data.cpf();
    this.email = data.email();
    this.firstName = data.firstName();
    this.lastName = data.lastName();
    this.password = data.password();
    this.balance = data.balance();
    this.type = data.type();
  }
}
