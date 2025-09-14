package de.marcelhenning.infra.persistence;

import de.marcelhenning.domain.model.User;
import de.marcelhenning.app.ports.UserRepository;
import de.marcelhenning.infra.persistence.entity.UserEntity;
import de.marcelhenning.infra.persistence.mapper.UserEntityMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class JpaUserRepository implements UserRepository {
    @PersistenceContext EntityManager em;

    public void save(User user) { em.persist(UserEntityMapper.toEntity(user)); }
    public User update(User user) { return UserEntityMapper.toDomain(em.merge(UserEntityMapper.toEntity(user))); }
    public Optional<User> findById(String id) { return Optional.ofNullable(em.find(UserEntity.class, id)).map(UserEntityMapper::toDomain); }
    public Optional<User> findByEmail(String email) {
        List<UserEntity> list = em.createQuery("select u from UserEntity u where u.email = :e", UserEntity.class)
                .setParameter("e", email).getResultList();
        return list.stream().findFirst().map(UserEntityMapper::toDomain);
    }
    public List<User> findAll(int page, int size) {
        return em.createQuery("select u from UserEntity u order by u.createdAt desc", UserEntity.class)
                .setFirstResult(page * size).setMaxResults(size).getResultList()
                .stream().map(UserEntityMapper::toDomain).collect(Collectors.toList());
    }
}
