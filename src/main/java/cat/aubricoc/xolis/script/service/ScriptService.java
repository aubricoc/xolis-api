package cat.aubricoc.xolis.script.service;

import cat.aubricoc.xolis.security.service.AuthService;
import cat.aubricoc.xolis.wishes.dao.WishDao;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ScriptService {

    private final WishDao wishDao;
    private final AuthService authService;

    @Inject
    public ScriptService(WishDao wishDao, AuthService authService) {
        this.wishDao = wishDao;
        this.authService = authService;
    }

    public void run() {
        wishDao.search().stream()
                .filter(wish -> wish.getUserId() == null)
                .forEach(wish -> {
                    wish.setUserId(authService.getUserId());
                    wishDao.update(wish);
                });
    }
}
