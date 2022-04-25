package by.sam_solutions.grigorieva.olga.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "role")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role extends AbstractEntity implements GrantedAuthority {

    @Column(name = "role_name", nullable = false)
    private String roleName;

    public String getAuthority() {
        return roleName;
    }

}