package by.sam_solutions.grigorieva.olga.backend.converter.to.entity;

import by.sam_solutions.grigorieva.olga.backend.dto.UserDto;
import by.sam_solutions.grigorieva.olga.backend.entity.User;
import org.springframework.core.convert.converter.Converter;

public class UserToEntityConverter implements Converter<UserDto, User> {

    @Override
    public User convert(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setWildBerriesKeys(userDto.getWildBerriesKeys());
        user.setUsername(userDto.getUsername());
        user.setNameCompany(userDto.getNameCompany());
        user.setIsBlocked(userDto.getIsBlocked());
        user.setIsSubscribed(userDto.getIsSubscribed());
        return user;
    }
}
