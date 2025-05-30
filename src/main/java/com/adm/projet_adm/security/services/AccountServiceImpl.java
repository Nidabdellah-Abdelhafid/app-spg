package com.adm.projet_adm.security.services;

import com.adm.projet_adm.security.entities.AppRole;
import com.adm.projet_adm.security.entities.AppUser;
import com.adm.projet_adm.security.repositories.AppRoleRepository;
import com.adm.projet_adm.security.repositories.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;

    public AccountServiceImpl(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AppUser addUser(AppUser appUser) {
        String password = appUser.getPassword();
        appUser.setPassword(passwordEncoder.encode(password));
        return appUserRepository.save(appUser);
    }

    @Override
    public AppRole addRole(AppRole appRole) {
        return appRoleRepository.save(appRole);
    }

    @Override
    public void addRoleToUser(String roleName, String email) {
        AppUser appUser = appUserRepository.findByEmail(email);
        AppRole appRole = appRoleRepository.findByRoleName(roleName);
        
        boolean hasRole = appUser.getAppRoles().stream()
                .anyMatch(role -> role.getRoleName().equals(roleName));
                
        if (!hasRole) {
            appUser.getAppRoles().add(appRole);
        }
    }

    @Transactional
    public AppUser getUserbyEmail(String email) {
        AppUser appUser = appUserRepository.findByEmail(email);
        appUser.getAppRoles().size();
        return appUser;
    }

    @Override
    public List<AppUser> getUsers() {
        return appUserRepository.findAll();
    }

    @Override
    public AppUser updateUser(AppUser updatedUser) {
        try {
            AppUser existingUser = appUserRepository.findById(updatedUser.getId());
            if (existingUser != null) {
                // Update only allowed fields
            existingUser.setFullname(updatedUser.getFullname());
            existingUser.setTelephone(updatedUser.getTelephone());
            existingUser.setCreatedAt(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            .format(new java.util.Date()));

            existingUser.setPays(updatedUser.getPays());
            if (updatedUser.getUserPhoto() != null) {
                existingUser.setUserPhoto(updatedUser.getUserPhoto());
            }
            
            return appUserRepository.save(existingUser);
            }
            return null;
            
            
        } catch (RuntimeException e) {
            throw new RuntimeException("Error updating user: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error while updating user");
        }
    }

    @Override
    @Transactional
    public void deleteUserProfile(String email) {
        try {
            AppUser user = appUserRepository.findByEmail(email);
            if (user != null) {
                // Clear messages first
                user.getSentMessages().forEach(message -> {
                    message.setSender(null);
                    message.setReceiver(null);
                });
                
                user.getReceivedMessages().forEach(message -> {
                    message.setSender(null);
                    message.setReceiver(null);
                });
                
                // Clear other relationships
                user.getAppRoles().clear();
                user.getNotifications().clear();
                user.getOffres_favs().forEach(offre -> {
                    offre.getAppUsers().remove(user);
                });
                user.getOffres_favs().clear();
                
                // Clear reservations
                user.getReservations().forEach(reservation -> {
                    reservation.setAppUser(null);
                    reservation.setOffre_reservations(null);
                });
                user.getReservations().clear();
                
                // Flush changes before deletion
                appUserRepository.flush();
                
                // Finally delete the user
                appUserRepository.delete(user);
            } else {
                throw new RuntimeException("User not found");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error deleting user profile: " + e.getMessage());
        }
    }
}
