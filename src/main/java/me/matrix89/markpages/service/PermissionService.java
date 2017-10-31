package me.matrix89.markpages.service;

import me.matrix89.markpages.data.model.PageMaintainerModel;
import me.matrix89.markpages.data.model.PageModel;
import me.matrix89.markpages.data.model.UserModel;
import me.matrix89.markpages.data.repository.PageMaintainerRepository;
import me.matrix89.markpages.data.repository.UserRepository;
import org.springframework.stereotype.Service;

/**
 * @author Hubertus
 * Created 27.10.2017
 */
@Service
public class PermissionService {

    private PageMaintainerRepository maintainerRepository;
    private UserRepository userRepository;

    public PermissionService(PageMaintainerRepository maintainerRepository, UserRepository userRepository) {
        this.maintainerRepository = maintainerRepository;
        this.userRepository = userRepository;
    }

    public boolean canEdit(UserModel user, PageModel page) {
        if (user.getRole().equals("ROLE_ADMIN")) return true;

        PageMaintainerModel maintainerModel = maintainerRepository.findFirstByPageAndUser(page, user);
        if (maintainerModel == null) return false;
        return maintainerModel.getRole() == PageMaintainerModel.Role.OWNER
                || maintainerModel.getRole() == PageMaintainerModel.Role.MAINTAINER;
    }

    public PageMaintainerModel getRole(String pageStringId, String user) {
        return maintainerRepository.findFirstByPage_StringIdAndUser(pageStringId, userRepository.getByUsername(user));
    }
}