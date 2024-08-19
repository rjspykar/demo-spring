package configuration.security.OAuthImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
    public class CustomOAuth2UserService extends DefaultOAuth2UserService {

        @Autowired
        private UserRepository userRepository;  // Assume you have a UserRepository to save user data

        @Override
        public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
            OAuth2User oAuth2User = super.loadUser(userRequest);

            // Extract user info
            String email = oAuth2User.getAttribute("email");
            String name = oAuth2User.getAttribute("name");

            // Save or update the user in the database
            User user = userRepository.findByEmail(email)
                    .orElse(new User(email, name));
            userRepository.save(user);

            return oAuth2User;
        }
    }

