package com.javarush.quest.khlopin.questlima.services;

import com.javarush.quest.khlopin.questlima.entity.Repository;
import com.javarush.quest.khlopin.questlima.entity.Role;
import com.javarush.quest.khlopin.questlima.entity.User;
import com.javarush.quest.khlopin.questlima.excpetions.QuestException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class UserRepository implements Repository<User> {

    private final HashMap<Long, User> userMap = new HashMap<>();

    public static final AtomicLong id = new AtomicLong(0); // TODO По окончанию разработки убрать роль админа 1234

    public UserRepository() {

        create("Mihail", "recd123", Role.ADMIN);
        create("Emma", "saf423", Role.USER);
        create("Valik", "fdsgh432", Role.GUEST);
        create("1234", "1234", Role.ADMIN);
    }

    @Override
    public void create(String login, String password, Role role) {
        long key = id.incrementAndGet();
        Optional<User> user = find(login);
        if (user.isEmpty()) {
            userMap.put(key, new User(key, login, password, role));
        } else {
            throw new QuestException("Пользователь с таким именем уже создан");
        }
    }


    @Override
    public User get(long id) {
        return userMap.get(id);
    }

    @Override
    public void delete(long id) {
        userMap.remove(id);
    }

    @Override
    public Collection<User> getAll() {
        return userMap.values();
    }

    @Override
    public void update(long idOfOlderUser, User user) {
        delete(idOfOlderUser);
        userMap.put(idOfOlderUser, user);
    }

    @Override
    public Optional<User> find(String login) {
        for (Map.Entry<Long, User> longUserEntry : userMap.entrySet()) {
            User user = longUserEntry.getValue();
            String userName = user.getUserName();
            if (userName.equals(login)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }
}
