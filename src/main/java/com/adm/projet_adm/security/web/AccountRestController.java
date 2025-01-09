package com.adm.projet_adm.security.web;

import com.adm.projet_adm.security.entities.AppRole;
import com.adm.projet_adm.security.entities.AppUser;
import com.adm.projet_adm.security.services.AccountService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class AccountRestController {
    private AccountService accountService;

    public AccountRestController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping(path = "/users")
    @PostAuthorize("hasAuthority('USER')")
    public List<AppUser> appUsers() {
        return accountService.getUsers();
    }

    @RequestMapping(value = "/users/createUser")
    public AppUser createUser(
            @RequestParam("fullname") String fullname,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestPart("userPhoto") MultipartFile userPhoto,
            @RequestParam("telephone") String telephone,
            @RequestParam("pays") String pays
    ) {

        AppUser appUser = new AppUser();
        appUser.setFullname(fullname);
        appUser.setEmail(email);
        appUser.setPassword(password);
        appUser.setTelephone(telephone);
        appUser.setPays(pays);

        try {
            // Save or process the file
            byte[] userPhotoBytes = userPhoto.getBytes();
            appUser.setUserPhoto(userPhotoBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return accountService.addUser(appUser);
    }

    @PostMapping(path = "/roles")
    @PostAuthorize("hasAuthority('ADMIN')")
    public AppRole saveRole(@RequestBody AppRole appRole) {
        return accountService.addRole(appRole);
    }

    @PostMapping(path = "/addRoleToUser")
    public void addRoleToUser(@RequestBody RoleUserForme roleUserForme) {
        accountService.addRoleToUser("USER", roleUserForme.getEmail());
    }

    @GetMapping(path = "/refreshToken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authToken = request.getHeader("Authorization");
        if (authToken != null && authToken.startsWith("Bearer ")) {
            try {
                String refreshToken = authToken.substring(7);
                Algorithm algorithm = Algorithm.HMAC256("Atlas99Voyages25");
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refreshToken);
                String username = decodedJWT.getSubject();
                AppUser appUser = accountService.getUserbyEmail(username);

                String jwtAccesstoken = JWT.create().withSubject(appUser.getEmail())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
                        .withIssuer(request.getRequestURI().toString())
                        .withClaim("roles", appUser.getAppRoles().stream().map(ga -> ga.getRoleName()).collect(Collectors.toList()))
                        .sign(algorithm);

                Map<String, Object> idToken = new HashMap<>();
                idToken.put("access_token", jwtAccesstoken);
                idToken.put("refresh_token", refreshToken);
                idToken.put("email", appUser.getEmail());
                response.setContentType("application/json");
                new ObjectMapper().writeValue(response.getOutputStream(), idToken);
            }catch (Exception e) {
                throw  e;
            }
        }
        else {
            throw new RuntimeException("Refresh Token Required");
        }

    }

    @GetMapping(path = "/profile")
    @PostAuthorize("hasAnyAuthority('ADMIN','USER')")
    public AppUser profile(Principal principal) {
        return accountService.getUserbyEmail(principal.getName());
    }

}


class RoleUserForme {
    private String roleName;
    private String email;

    // Getters and Setters
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


