package cat.aubricoc.xolis.script.service;

import cat.aubricoc.xolis.users.dao.UserDao;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Date;

@Singleton
public class ScriptService {

    private final UserDao userDao;

    @Inject
    public ScriptService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void run() {
        userDao.search().stream()
                .filter(user -> user.getCreated() == null)
                .forEach(user -> {
                    user.setCreated(new Date());
                    userDao.update(user);
                });
    }
}
