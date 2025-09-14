package de.marcelhenning.infra.persistence.mapper;

import de.marcelhenning.domain.model.User;
import de.marcelhenning.infra.persistence.entity.UserEntity;

public class UserEntityMapper {
    public static UserEntity toEntity(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.id = user.getId();
        userEntity.firstname = user.getFirstname();
        userEntity.lastname = user.getLastname();
        userEntity.email = user.getEmail();
        userEntity.birthday = user.getBirthday();
        userEntity.passwordHash = user.getPasswordHash();
        userEntity.passwordSalt = user.getPasswordSalt();
        userEntity.createdAt = user.getCreatedAt();
        userEntity.updatedAt = user.getUpdatedAt();
        return userEntity;
    }
    public static User toDomain(UserEntity userEntity) {
        User user = new User(userEntity.id);
        user.setFirstname(userEntity.firstname);
        user.setLastname(userEntity.lastname);
        user.setEmail(userEntity.email);
        user.setBirthday(userEntity.birthday);
        user.setPasswordHash(userEntity.passwordHash);
        user.setPasswordSalt(userEntity.passwordSalt);
        user.setCreatedAt(userEntity.createdAt);
        user.setUpdatedAt(userEntity.updatedAt);
        return user;
    }
}
