package by.sam_solutions.grigorieva.olga.backend.converter.to.entity;

import by.sam_solutions.grigorieva.olga.backend.dto.RoleDto;
import by.sam_solutions.grigorieva.olga.backend.entity.Role;
import org.springframework.core.convert.converter.Converter;

public class RoleToEntityConverter implements Converter<RoleDto, Role> {

    @Override
    public Role convert(RoleDto roleDto) {
        Role role = new Role();
        role.setId(roleDto.getId());
        role.setRoleName(roleDto.getRoleName());
        return role;
    }
}
