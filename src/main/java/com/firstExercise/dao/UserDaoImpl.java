package com.firstExercise.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.firstExercise.models.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class UserDaoImpl implements UserDao {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional(readOnly = true)
	public List<User> getUsers() {
		String query = "from User";
		List<User> result = entityManager.createQuery(query).getResultList();

		return result;
	}

	@Override
	@Transactional
	public void deleteUserById(Long id) {
		User user = entityManager.find(User.class, id);
		entityManager.remove(user);
	}

	@Override
	@Transactional
	public void addUser(User user) {
		entityManager.persist(user);
	}

	@Override
	@Transactional
	public void updateUser(Long id, User user) {
		User existingUser = entityManager.find(User.class, id);

		if (existingUser != null) {
			existingUser.setName(user.getName());
			existingUser.setSurname(user.getSurname());
			existingUser.setEmail(user.getEmail());
			existingUser.setPhone(user.getPhone());
			existingUser.setPassword(user.getPassword());
		}
	}

	@Override
	@Transactional(readOnly = true)
	public User getUserById(Long id) {
		return entityManager.find(User.class, id);
	}

}
