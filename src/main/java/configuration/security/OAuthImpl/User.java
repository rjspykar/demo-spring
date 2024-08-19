package configuration.security.OAuthImpl;


    public class User {

        private Long id;

        private String email;

        private String name;

        // Add other relevant fields
        private String profilePictureUrl;

        // Default constructor
        public User() {}

        // Constructor for creating a new User
        public User(String email, String name) {
            this.email = email;
            this.name = name;
        }

        // Getters and Setters

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getProfilePictureUrl() {
            return profilePictureUrl;
        }

        public void setProfilePictureUrl(String profilePictureUrl) {
            this.profilePictureUrl = profilePictureUrl;
        }
    }
