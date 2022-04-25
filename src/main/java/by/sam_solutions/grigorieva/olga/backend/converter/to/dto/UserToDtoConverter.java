package by.sam_solutions.grigorieva.olga.backend.converter.to.dto;

import by.sam_solutions.grigorieva.olga.backend.dto.UserDto;
import by.sam_solutions.grigorieva.olga.backend.entity.User;
import org.springframework.core.convert.converter.Converter;

public class UserToDtoConverter implements Converter<User, UserDto> {

    @Override
    public UserDto convert(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setWildBerriesKeys(user.getWildBerriesKeys());
        userDto.setUsername(user.getUsername());
        userDto.setNameCompany(user.getNameCompany());
        userDto.setIsBlocked(user.getIsBlocked());
        userDto.setIsSubscribed(user.getIsSubscribed());
        return userDto;
    }
}
