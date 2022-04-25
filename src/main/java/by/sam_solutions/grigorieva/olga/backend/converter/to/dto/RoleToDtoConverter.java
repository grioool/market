package by.sam_solutions.grigorieva.olga.backend.converter.to.dto;

import by.sam_solutions.grigorieva.olga.backend.dto.RoleDto;
import by.sam_solutions.grigorieva.olga.backend.entity.Role;
import org.springframework.core.convert.converter.Converter;

public class RoleToDtoConverter implements Converter<Role, RoleDto> {

    @Override
    public RoleDto convert(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setRoleName(role.getRoleName());
        return roleDto;
    }
}
