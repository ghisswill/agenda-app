package fr.ghisswill.agenda.security;

public class JwtServiceTest {

//    private JwtService jwtService;
//
//    private final String secret = "une_clé_très_secrète_de_32_caractères_minimum!!";
//    private final String jwtExpiration = "86400000";
//
//    @BeforeEach
//    void setUp() throws Exception {
//        jwtService = new JwtService();
//
//        // Injection manuelle des valeurs @Value
//        Field secretField = JwtService.class.getDeclaredField("jwtSecret");
//        secretField.setAccessible(true);
//        secretField.set(jwtService, secret);
//
//        Field expirationField = JwtService.class.getDeclaredField("jwtExpiration");
//        expirationField.setAccessible(true);
//        expirationField.set(jwtService, 1000 * 60 * 60); // 1 heure
//    }
//
//    @Test
//    void generate_token_shouldReturnValidToken() {
//        User user = getUser();
//
//        String token = jwtService.generateToken(user);
//
//        assertNotNull(token);
//        assertTrue(token.length() > 20);
//    }
//
//    @Test
//    void extractUsername_shouldReturnCorrectEmail() {
//        User user = getUser();
//        String token = jwtService.generateToken(user);
//
//        String extracted = jwtService.extractUsername(token);
//
//        assertEquals("test@test.fr", extracted);
//    }
//
//    @Test
//    void isTokenValid_shouldReturnTrueForCorrectUser() {
//        User user = getUser();
//        String token = jwtService.generateToken(user);
//
//        assertTrue(jwtService.isTokenValid(token, user));
//    }
//
//    @Test
//    void isTokenValid_shouldReturnFalseForWrongUser() {
//        User user = getUser();
//        String token = jwtService.generateToken(user);
//        User otherUser = User.builder()
//                .email("hacker@example.com")
//                .password("pass")
//                .role(Role.USER)
//                .build();
//
//        assertFalse(jwtService.isTokenValid(token, otherUser));
//    }
//
//    private static User getUser() {
//        User user = User.builder()
//                .email("test@test.fr")
//                .password("pass")
//                .role(Role.USER).build();
//        return user;
//    }
}
