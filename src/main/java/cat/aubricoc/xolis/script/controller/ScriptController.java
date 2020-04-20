package cat.aubricoc.xolis.script.controller;

import cat.aubricoc.xolis.script.service.ScriptService;
import cat.aubricoc.xolis.security.Role;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;

import javax.inject.Inject;

@Controller("/script")
public class ScriptController {

    private final ScriptService scriptService;

    @Inject
    public ScriptController(ScriptService scriptService) {
        this.scriptService = scriptService;
    }

    @Post
    @Secured(Role.ADMIN)
    public void run() {
        scriptService.run();
    }
}
